package ru.skfl.skflshop.entities;

public enum UserState {
    CONFIRMED("CONFIRMED"),
    NOT_CONFIRMED("NOT_CONFIRMED"),
    BANNED("BANNED"),
    DELETED("DELETED");

    private String state;

    UserState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
