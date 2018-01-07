package com.zhengyun.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by 听风 on 2018/1/7.
 */
public class Protocol {

    public static MyHttpServletRequest decode(final BufferedReader bd){
        MyHttpServletRequest request = new MyHttpServletRequest();
        System.out.println(Thread.currentThread().getName() + "decode**************************");
        String s = null;
        try {
            while ((s = bd.readLine())!=null){
                System.out.println(Thread.currentThread().getName() + s);
                parse(s, request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    private static void parse(String s, MyHttpServletRequest request){
        if (s.startsWith("GET")) {
            String method = "Get";
            request.setMethod(method);

            int index = s.indexOf("HTTP");
           // System.out.println("index--->" + index);
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格
            // System.out.println("uri--->" + uri);
            request.setRequestURI(uri);

            String protocol = s.substring(index);
            // System.out.println("protocol---->" + protocol);
            request.setProtocol(protocol);
        } else if (s.startsWith("POST")) {
            String method = "POST";
            request.setMethod(method);

            int index = s.indexOf("HTTP");
            System.out.println("index--->" + index);
            String uri = s.substring(3 + 1, index - 1);// 用index-1可以去掉连接中的空格
            System.out.println("uri--->" + uri);
            request.setRequestURI(uri);

            String protocol = s.substring(index);
            //  System.out.println("protocol---->" + protocol);
            request.setProtocol(protocol);

        } else if (s.startsWith("Accept:")) {
            String accept = s.substring("Accept:".length() + 1);
            //  System.out.println("accept--->" + accept);
            request.setAccept(accept);

        } else if (s.startsWith("User-Agent:")) {
            String agent = s.substring("User-Agent:".length() + 1);
            //  System.out.println("agent--->" + agent);
            request.setAgent(agent);

        } else if (s.startsWith("Host:")) {
            String host = s.substring("Host:".length() + 1);
            //  System.out.println("host--->" + host);
            request.setHost(host);

        } else if (s.startsWith("Accept-Language:")) {
            String language = s.substring("Accept-Language:".length() + 1);
            //  System.out.println("language--->" + language);
            request.setLanguage(language);

        } else if (s.startsWith("Accept-Charset:")) {
            String charset = s.substring("Accept-Charset:".length() + 1);
            //  System.out.println("charset--->" + charset);
            request.setCharset(charset);
        } else if (s.startsWith("Accept-Encoding:")) {
            String encoding = s.substring("Accept-Encoding:".length() + 1);
            //  System.out.println("encoding--->" + encoding);
            request.setEncoding(encoding);

        } else if (s.startsWith("Connection:")) {
            String connection = s.substring("Connection:".length() + 1);
            //  System.out.println("connection--->" + connection);
            request.setConnection(connection);
        }
    }

    public void encode(OutputStream out){

    }
}
