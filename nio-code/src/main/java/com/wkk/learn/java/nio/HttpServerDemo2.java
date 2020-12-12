package com.wkk.learn.java.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 非阻塞io模拟
 * @Author Wangkunkun
 * @Date 2020/10/24 15:34
 */
public class HttpServerDemo2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            Socket accept = serverSocket.accept();
            new Thread(() -> service(accept)).start();
        }
    }

    public static void service(Socket socket) {
        try {
            Thread.sleep(20);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
