package com.pankov.roadtosenior;

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
