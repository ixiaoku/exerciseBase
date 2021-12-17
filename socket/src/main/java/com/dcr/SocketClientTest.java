package com.dcr;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/15 18:01
 */
public class SocketClientTest {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter out = null;
        try {
            socket = new Socket("localhost", 9000);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            String msg = null;
            while (!"Bye".equals(msg = scanner.next())) {
                System.out.println("客户端发送消息:" + msg);
                out.write(msg);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("错误信息:" + e.getMessage());
        } finally {
            try {
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
