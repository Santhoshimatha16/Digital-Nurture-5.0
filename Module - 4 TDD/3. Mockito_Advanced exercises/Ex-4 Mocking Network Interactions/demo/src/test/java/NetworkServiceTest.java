package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NetworkServiceTest {

    @Test
    void testServiceWithMockNetworkClient() {

        NetworkClient mockNetworkClient =
                mock(NetworkClient.class);

        when(mockNetworkClient.connect())
                .thenReturn("Mock Connection");

        NetworkService networkService =
                new NetworkService(mockNetworkClient);

        String result =
                networkService.connectToServer();

        assertEquals(
                "Connected to Mock Connection",
                result);

        verify(mockNetworkClient)
                .connect();

    }

}