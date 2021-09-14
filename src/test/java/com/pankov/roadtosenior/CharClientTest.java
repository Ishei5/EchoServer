package com.pankov.roadtosenior;

import com.pankov.roadtosenior.roadtosenior.CharClient;
import com.pankov.roadtosenior.roadtosenior.CharEchoServer;
import com.pankov.roadtosenior.roadtosenior.Client;
import com.pankov.roadtosenior.roadtosenior.Server;

import java.io.ByteArrayInputStream;

public class CharClientTest extends AbstractClientTest {

    ByteArrayInputStream testStream = new ByteArrayInputStream(("Hello"+"\n").getBytes());

    @Override
    public Server getServer() {
        return new CharEchoServer();
    }

    @Override
    public Client getClient() {
        return new CharClient(testStream);
    }
}
