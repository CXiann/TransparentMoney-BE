package com.myproject.transparentmoney.user.model;

import jakarta.persistence.PrePersist;

public class UserEntityListener {

    @PrePersist
    public void prePersist(User user) {
        if (user.getSettings() == null) {
            UserSettings settings = new UserSettings();
            settings.setUser(user);
            settings.setUserId(user.getId()); // Important: link IDs
            // settings.setTimezone("UTC");
            // settings.setTheme("light");
            // settings.setPinEnabled(false);
            // settings.setBiometricEnabled(false);
            user.setSettings(settings);
        }
    }
}