package com.zhengyun.rpc.registercenter;

import com.alibaba.fastjson.JSON;
import com.zhengyun.rpc.RegisterInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 听风 on 2017/12/28.
 */
public class RegisterCenter {

    Map<String, RegisterInfo> store = new ConcurrentHashMap<String, RegisterInfo>();

    public void bind(int port)throws Exception{
        ServerSocket serverSocket = new ServerSocket(5555);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new SocketHandler(socket)).start();
        }

    }

    public static void main(String[] args) throws Exception{
        RegisterCenter registerCenter = new RegisterCenter();
        registerCenter.bind(8888);
    }

    class SocketHandler implements Runnable{

        private Socket socket;

        public SocketHandler(Socket socket){
            this.socket = socket;
        }

        public void run() {
            DataInputStream in = null;
            DataOutputStream out = null;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                int len = in.readInt();
                System.out.println("消息的总长度" + len);
                int flag = in.readInt();
                System.out.println("接收消息类型"+flag);
                byte[] data = new byte[len];
                in.readFully(data);
                String msg = new String(data);
                System.out.println("接收到的消息" + msg);
                switch (flag){
                    //读取消息
                    case 1:
                        RegisterInfo obj = store.get(msg);
                        String str = JSON.toJSONString(obj);
                        out.writeInt(str.length());
                        out.writeInt(flag);
                        out.write(str.getBytes());
                        out.flush();
                        break;
                    //保存消息
                    case 2:
                        RegisterInfo info = JSON.parseObject(msg, RegisterInfo.class);
                        store.putIfAbsent(info.getId(), info);
                        String result = "success";
                        byte[] res = result.getBytes();
                        out.writeInt(res.length);
                        out.writeInt(flag);
                        out.write(res);
                        out.flush();
                        break;
                }

            }catch (Exception e){

            }finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                }catch (Exception e){

                }

            }

        }


    }
}
