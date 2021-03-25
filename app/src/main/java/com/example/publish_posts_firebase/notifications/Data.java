package com.example.publish_posts_firebase.notifications;

public class Data {
    private String notificationType;


    public Data() {

    }

    public Data(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}


