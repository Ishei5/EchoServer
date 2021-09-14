package com.pankov.roadtosenior.roadtosenior;

import java.io.*;
import java.net.Socket;

public class CharClient implements Client {

    private InputStream inputStream;

    public static void main(String[] args) throws IOException {
        Client client = new CharClient();
        System.out.println(client.communicateWithServer());
    }

    public CharClient(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CharClient() {
        this.inputStream = INPUT_STREAM;
    }

    @Override
    public String getMessage(InputStream inputStream) throws IOException {
        String message;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            message = reader.readLine();
        }
        return message;
    }

    @Override
    public String communicateWithServer() throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true)) {

            String input = getMessage(inputStream);
            socketWriter.println(input);

            return socketReader.readLine();
        }
    }
}
