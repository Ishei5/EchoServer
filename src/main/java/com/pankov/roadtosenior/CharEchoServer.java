package com.pankov.roadtosenior;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CharEchoServer implements Server {
    public static void main(String[] args) throws IOException {
        Server server = new CharEchoServer();
        server.start();
    }

    @Override
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true)) {

                    String inputLine = socketReader.readLine();
                    socketWriter.println("Echo " + inputLine);
                }
            }
        }
    }
}
