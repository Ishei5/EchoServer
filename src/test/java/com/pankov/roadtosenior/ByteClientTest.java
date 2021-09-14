package com.pankov.roadtosenior;

import com.pankov.roadtosenior.roadtosenior.ByteClient;
import com.pankov.roadtosenior.roadtosenior.ByteEchoServer;
import com.pankov.roadtosenior.roadtosenior.Client;
import com.pankov.roadtosenior.roadtosenior.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteClientTest extends AbstractClientTest{

    ByteArrayInputStream testStream = new ByteArrayInputStream(("Hello"+"\n").getBytes());

    @Override
    public Server getServer() {
        return new ByteEchoServer();
    }

    @Override
    public Client getClient() {
        return new ByteClient(testStream);
    }

    @Test
    @DisplayName("Get echo response from server using buffer less then string length")
    public void testGetEchoResponseFromServerUsingBufferLessThenStringLength() throws IOException {
        String message = "message with long test\n";
        Client client = new ByteClient(new ByteArrayInputStream(message.getBytes()), 3);

        String response = client.communicateWithServer();

        assertEquals("Echo message with long test", response);

    }
}
