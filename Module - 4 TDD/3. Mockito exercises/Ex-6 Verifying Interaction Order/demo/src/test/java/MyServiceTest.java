package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MyServiceTest {

    @Test
    void testInteractionOrder() {

        ExternalApi mockApi =
                Mockito.mock(ExternalApi.class);

        MyService service =
                new MyService(mockApi);

        service.executeTask();

        InOrder inOrder =
                Mockito.inOrder(mockApi);

        inOrder.verify(mockApi).connect();

        inOrder.verify(mockApi).fetchData();

        inOrder.verify(mockApi).disconnect();

    }

}