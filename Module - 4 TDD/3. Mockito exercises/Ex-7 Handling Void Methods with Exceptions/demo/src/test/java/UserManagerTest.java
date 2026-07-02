package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UserManagerTest {

    @Test
    void testVoidMethodThrowsException() {

        NotificationService mockService =
                Mockito.mock(NotificationService.class);

        doThrow(new RuntimeException("Notification Failed"))
                .when(mockService)
                .sendNotification(Mockito.anyString());

        UserManager manager =
                new UserManager(mockService);

        assertThrows(
                RuntimeException.class,
                () -> manager.registerUser());

        verify(mockService)
                .sendNotification("Welcome User");

    }

}