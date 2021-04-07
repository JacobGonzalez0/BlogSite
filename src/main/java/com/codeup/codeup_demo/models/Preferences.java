package com.codeup.codeup_demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "preferences")
public class Preferences {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nofifications", nullable = false)
    private boolean notifications;

    private String theme;

    public Preferences() {
    }

    public Preferences(Long id, boolean notifications, String theme) {
        this.id = id;
        this.notifications = notifications;
        this.theme = theme;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNotifications() {
        return this.notifications;
    }

    public boolean getNotifications() {
        return this.notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
