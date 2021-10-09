package com.pankov.roadtosenior;

import java.io.*;
import java.net.Socket;

public class ByteClient implements Client {

    private static final int DEFAULT_CAPACITY = 1024;
    private InputStream inputStream;
    private int bufferCapacity;

    public static void main(String[] args) throws IOException {
        Client client = new ByteClient();
        System.out.println(client.communicateWithServer());
    }

    public ByteClient(InputStream inputStream, int bufferCapacity) {
        this.inputStream = inputStream;
        this.bufferCapacity = bufferCapacity;
    }

    public ByteClient(InputStream inputStream) {
        this.inputStream = inputStream;
        this.bufferCapacity = DEFAULT_CAPACITY;
    }

    public ByteClient(int bufferCapacity) {
        this.inputStream = INPUT_STREAM;
        this.bufferCapacity = bufferCapacity;
    }

    public ByteClient() {
        this.inputStream = INPUT_STREAM;
        this.bufferCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public String getMessage(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder;
        try (BufferedInputStream console = new BufferedInputStream(inputStream)) {
            stringBuilder = new StringBuilder();
            char value;

            /*do {
                value = (char) console.read();
                if (value == '\n') {
                    break;
                }
                stringBuilder.append(value);
            } while (value != -1);*/
            while ((value = (char) console.read()) != -1 && value != '\n') {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String communicateWithServer() throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedInputStream socketIn = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream socketOut = new BufferedOutputStream(socket.getOutputStream())) {

            String input = getMessage(this.inputStream);
            socketOut.write(input.getBytes());
            socketOut.flush();

            int count;
            byte[] buffer = new byte[bufferCapacity];

            StringBuilder stringBuilder = new StringBuilder();
            do {
                count = socketIn.read(buffer);
                stringBuilder.append(new String(buffer, 0, count));
            } while (socketIn.available() != 0);

            return stringBuilder.toString();
        }
    }
}
