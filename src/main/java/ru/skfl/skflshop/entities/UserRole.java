package ru.skfl.skflshop.entities;

public enum UserRole {
    USER("USER"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
