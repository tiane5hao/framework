package com.zhengyun.rpc.provider;

import com.alibaba.fastjson.JSON;
import com.zhengyun.rpc.IStudentService;
import com.zhengyun.rpc.RegisterInfo;
import com.zhengyun.rpc.RpcParam;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 听风 on 2017/12/29.
 */
public class Provider {

    public static Map<String, Object> beanMap = new HashMap<String, Object>();

    private static int port = 1111;

    public void register(String interfaceName){
        Socket socket = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try{
            socket = new Socket("localhost", 5555);
            out = new DataOutputStream(socket.getOutputStream());
            RegisterInfo registerInfo = new RegisterInfo();
            registerInfo.setId(interfaceName);
            registerInfo.setMsg(socket.getInetAddress().getHostAddress()+"_" + port);
            String str = JSON.toJSONString(registerInfo);
            byte[] msg = str.getBytes();
            out.writeInt(msg.length);
            out.writeInt(2);
            out.write(msg);
            out.flush();
            in = new DataInputStream(socket.getInputStream());
            int len = in.readInt();
            int type = in.readInt();
            byte[] b = new byte[len];
            in.read(b);
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

    }

    public void initBean(){
        IStudentService studentService = new StudentService();
        String nameId = IStudentService.class.getCanonicalName();
        beanMap.putIfAbsent(nameId, studentService);
    }

    public void start(){
        initBean();
        for(String name : beanMap.keySet()){
            register(name);
        }

        runServer();
    }

    private void runServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        }catch (Exception e){

        }

        while (true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }catch (Exception e){

            }

        }

    }

    public static void main(String[] args) {
        new Provider().start();
    }

    class ServerHandler implements Runnable{

        private Socket socket;

        public ServerHandler(Socket socket){
            this.socket = socket;
        }

        public void run() {
            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            try {
                in = new ObjectInputStream(socket.getInputStream());
                RpcParam param = (RpcParam) in.readObject();
                Object res = handleReq(param);
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                    in.close();
                    out.close();
                }catch (Exception e){

                }

            }
        }

        private Object handleReq(RpcParam rpcParam) {
            Object obj = beanMap.get(rpcParam.getFaceName());
            try {
                Method method = obj.getClass().getDeclaredMethod(rpcParam.getMethod(), rpcParam.getParameterTypes());
                Object result = method.invoke(obj, rpcParam.getArgs());
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }
    }
}
