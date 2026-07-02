package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class UserManagerTest {

    @Test
    void testVoidMethod() {

        NotificationService mockService =
                Mockito.mock(NotificationService.class);

        doNothing().when(mockService)
                .sendNotification(Mockito.anyString());

        UserManager manager =
                new UserManager(mockService);

        manager.registerUser();

        verify(mockService)
                .sendNotification(
                        "User Registered Successfully");
    }
}