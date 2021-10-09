package com.pankov.roadtosenior;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class AbstractClientTest {

    public abstract Server getServer();

    public abstract Client getClient();

    ByteArrayInputStream testStream = new ByteArrayInputStream(("Hello" + "\n").getBytes());

    @Test
    @DisplayName("Get message from InputStream")
    public void testGetMessage() throws IOException {
        Client client = getClient();
        String actualMessage = client.getMessage(testStream);

        assertEquals("Hello", actualMessage);
    }

    @Test
    @DisplayName("Get echo response from server")
    public void testGetEchoResponseFromServer() throws IOException {
        Client client = getClient();
        String response = client.communicateWithServer();

        assertEquals("Echo Hello", response);
    }

}
