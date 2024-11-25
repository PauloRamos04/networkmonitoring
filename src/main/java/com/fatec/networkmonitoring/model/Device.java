package com.fatec.networkmonitoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Device {

    @Id
    private String ip;
    private String macAddress;
    private boolean online;

    // Construtor
    public Device() {}

    public Device(String ip, String macAddress, boolean online) {
        this.ip = ip;
        this.macAddress = macAddress;
        this.online = online;
    }

    // Getters and Setters
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
