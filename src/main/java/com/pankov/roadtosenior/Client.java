package com.pankov.roadtosenior;

import java.io.IOException;
import java.io.InputStream;

public interface Client {

    int PORT = 3000;
    String HOST = "localhost";
    InputStream INPUT_STREAM = System.in;

    String communicateWithServer() throws IOException;

    String getMessage(InputStream inputStream) throws IOException;
}
