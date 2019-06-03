package com.zero.three;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class HttpServlet {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();

        GetMethod getMethod = new GetMethod("http://www.ibm.com");

        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());

        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK){
                System.err.println("Method failed: "
                + getMethod.getStatusLine());
            }

            //读取内容
            byte[] responseBody = getMethod.getResponseBody();
            //处理内容
            System.out.println(new String(responseBody));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }finally {
            getMethod.releaseConnection();
        }
    }
}
