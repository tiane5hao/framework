package com.zhengyun.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 听风 on 2018/1/7.
 */
public class ProtocolMain {

    public void bind(int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){

            try {
                Socket socket = serverSocket.accept();
                System.out.println("socket-------------------");
                new Thread(new SocketHandler(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProtocolMain protocolMain = new ProtocolMain();
        protocolMain.bind(8888);
    }

    class SocketHandler implements Runnable{

        Socket socket;

        public SocketHandler(Socket socket){
            this.socket = socket;
        }

        public void run() {
            BufferedReader bufferedReader = null;
            BufferedOutputStream  pw = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("............................");
                Protocol.decode(bufferedReader);
                pw = new BufferedOutputStream (socket.getOutputStream());
                StringBuffer requestHeader = new StringBuffer();
                requestHeader
                        .append("http/1.1 200 ok\n")
                        .append("\n\n")
                        .append("this is server" );
                String res = requestHeader.toString();
                pw.write(res.getBytes());
                pw.flush();
                System.out.println(Thread.currentThread().getName()+"处理完成");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    pw.close();
                    bufferedReader.close();
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
    }
}
