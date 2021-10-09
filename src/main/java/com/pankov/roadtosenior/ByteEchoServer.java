package com.pankov.roadtosenior;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ByteEchoServer implements Server {

    public static void main(String[] args) throws IOException {
        Server server = new ByteEchoServer();
        server.start();
    }

    @Override
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedInputStream socketIn = new BufferedInputStream(socket.getInputStream());
                     BufferedOutputStream socketOut = new BufferedOutputStream(socket.getOutputStream())) {
                    int count;
                    byte[] buffer = new byte[3];

                    socketOut.write("Echo ".getBytes());

                    do {
                        count = socketIn.read(buffer);
                        socketOut.write(buffer, 0, count);
                    } while (socketIn.available() != 0);
                }
            }
        }
    }
}