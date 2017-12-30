package com.zhengyun.rpc.consumer;

import com.alibaba.fastjson.JSON;
import com.zhengyun.rpc.IStudentService;
import com.zhengyun.rpc.RegisterInfo;
import com.zhengyun.rpc.RpcParam;
import com.zhengyun.rpc.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by 听风 on 2017/12/28.
 */
public class MyComsumer {

     public RegisterInfo getRegisterInfo(String name){
         RegisterInfo registerInfo = null;
         Socket socket = null;
         DataOutputStream out = null;
         DataInputStream in = null;
         try{
             socket = new Socket("localhost", 5555);
             out = new DataOutputStream(socket.getOutputStream());
             byte[] sendMsg = name.getBytes();
             out.writeInt(sendMsg.length);
             out.writeInt(1);
             out.write(sendMsg);
             out.flush();


             in = new DataInputStream(socket.getInputStream());
             int len = in.readInt();
             int type = in.readInt();
             byte[] b = new byte[len];
             in.read(b);
             String res = new String(b);
             registerInfo = JSON.parseObject(res, RegisterInfo.class);
             System.out.println("type = " + type + ", return msg =" + new String(b));
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             try {
                 in.close();
                 out.close();
                 socket.close();
             }catch (Exception e){

             }
         }
         return registerInfo;
     }

     public String hostAddr(Class clazz){
         return getRegisterInfo(clazz.getCanonicalName()).getMsg();
     }


    public static void main(String[] args) {
         MyComsumer comsumer = new MyComsumer();
         IStudentService studentService = comsumer.getProxyObj(IStudentService.class);
         Student student = new Student();
         student.setAge(12);
         student.setUserName("tom");
         String name = studentService.print(student);
         System.out.println(name);
    }

    private <T>T getProxyObj(final Class<T> clazz) {
        String addr = hostAddr(clazz);
        String str[] = addr.split("_");
         final String host = str[0];
         final int port = Integer.parseInt(str[1]);

        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectInputStream in = null;
                ObjectOutputStream out = null;
                try {
                    socket= new Socket(host, port);
                    out = new ObjectOutputStream(socket.getOutputStream());
                    RpcParam rpcParam = new RpcParam();
                    rpcParam.setArgs(args);
                    rpcParam.setMethod(method.getName());
                    rpcParam.setParameterTypes(method.getParameterTypes());
                    rpcParam.setFaceName(clazz.getName());
                    out.writeObject(rpcParam);
                    out.flush();
                    in = new ObjectInputStream(socket.getInputStream());
                    Object result = in.readObject();
                    System.out.println("rpc相应结果" + result);
                    return (T)result;
                }catch (Exception e){

                }finally {
                    out.close();
                    in.close();
                    socket.close();
                }

                return null;
            }
        });
    }
}
