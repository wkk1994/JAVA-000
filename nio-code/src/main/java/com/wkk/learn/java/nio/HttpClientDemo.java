package com.wkk.learn.java.nio;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description 发送http请求
 * @Author Wangkunkun
 * @Date 2020/10/28 21:03
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        getHelloByClient();
        getHelloByHttpClient();
    }

    /**
     * 发送get请求
     */
    public static void getHelloByClient() {
        try {
            URL url = new URL("http://47.98.190.67:8802");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15 * 1000);
            httpURLConnection.setReadTimeout(15 * 1000);
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode != 200) {
                String errorStr = "";
                InputStream errorStream = httpURLConnection.getErrorStream();
                if(errorStream != null) {
                    byte[] bytes = new byte[errorStream.available()];
                    if(bytes.length > 0) {
                        errorStream.read(bytes);
                        errorStr = new String(bytes, "UTF-8");
                    }
                }
                System.out.println(responseCode + " " + httpURLConnection.getResponseMessage() + " " + errorStr);
            }else {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    byte[] bytes = new byte[inputStream.available()];
                    if(bytes.length > 0) {
                        inputStream.read(bytes);
                        System.out.println( new String(bytes, "UTF-8"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getHelloByHttpClient() {
        HttpGet request = new HttpGet("http://47.98.190.67:8802");
        CloseableHttpClient httpClient = HttpClientBuilder
                .create().setDefaultCookieStore(new BasicCookieStore()).build();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } else {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
