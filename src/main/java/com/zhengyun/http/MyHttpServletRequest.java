package com.zhengyun.http;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 听风 on 2018/1/7.
 */
public class MyHttpServletRequest  {

    private String method;// 请求方法
    private String protocol;// 协议版本
    private String requestURL;
    private String requestURI;//请求的URI地址 在HTTP请求的第一行的请求方法后面
    private String host;//请求的主机信息
    private String Connection;//Http请求连接状态信息 对应HTTP请求中的Connection
    private String agent;// 代理，用来标识代理的浏览器信息 ,对应HTTP请求中的User-Agent:
    private String language;//对应Accept-Language
    private String encoding;//请求的编码方式 对应HTTP请求中的Accept-Encoding
    private String charset;//请求的字符编码 对应HTTP请求中的Accept-Charset
    private String accept;// 对应HTTP请求中的Accept;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return Connection;
    }

    public void setConnection(String connection) {
        Connection = connection;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
