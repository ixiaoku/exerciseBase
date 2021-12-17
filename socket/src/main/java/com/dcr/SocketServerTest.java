package com.dcr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/15 16:55
 */
public class SocketServerTest {

    public static void main(String[] args) {
        Socket server = null;
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("等待连接。。。");
            server = serverSocket.accept();
            System.out.println("连接成功。。。");
            handler(server);
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handler(Socket socketClient) {
        BufferedReader in = null;
        try {
            char[] bytes = new char[1024];
            int len = 0;
            String msg = null;
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            while ((len = in.read(bytes, 0, 1024)) != -1) {
                msg = new String(bytes, 0, len);
                System.out.println("服务端收到消息: " + msg);
                if("Bye".equals(msg)){
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
