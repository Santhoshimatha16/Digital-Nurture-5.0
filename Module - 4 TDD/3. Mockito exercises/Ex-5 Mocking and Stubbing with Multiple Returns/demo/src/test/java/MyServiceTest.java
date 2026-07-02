package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MyServiceTest {

    @Test
    void testMultipleReturns() {

        ExternalApi mockApi =
                Mockito.mock(ExternalApi.class);

        when(mockApi.getData())
                .thenReturn("First Response")
                .thenReturn("Second Response")
                .thenReturn("Third Response");

        MyService service =
                new MyService(mockApi);

        assertEquals(
                "First Response",
                service.fetchData());

        assertEquals(
                "Second Response",
                service.fetchData());

        assertEquals(
                "Third Response",
                service.fetchData());

    }
}