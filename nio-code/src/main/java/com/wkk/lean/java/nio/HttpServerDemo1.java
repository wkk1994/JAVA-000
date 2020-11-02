package com.wkk.lean.java.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 阻塞io模拟
 * @Author Wangkunkun
 * @Date 2020/10/24 15:34
 */
public class HttpServerDemo1 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket accept = serverSocket.accept();
            service(accept);
        }
    }

    public static void service(Socket socket) {
        try {
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getLocalAddress());
            Thread.sleep(20);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Length: 9");
            printWriter.println("Content-Type: text/html; charset=utf-8");
            printWriter.println();
            printWriter.write("hello nio");
            printWriter.close();
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
