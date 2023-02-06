package com.example.authenticatingldap.dto;

public class UserLoginDto {
    private String adUniqueNumber;
    private String role;

    private String blocked;

    public String getAdUniqueNumber() {
        return adUniqueNumber;
    }

    public void setAdUniqueNumber(String adUniqueNumber) {
        this.adUniqueNumber = adUniqueNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }
}
