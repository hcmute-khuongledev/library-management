package com.library.interfaces;

import java.util.List;

public interface Notifiable {
    void sendNotification(String message);
    List<String> getNotificationHistory();

    default void sendOverdueNotification() {
        sendNotification("Ban co sach dang qua han. Vui long tra sach.");
    }
}
