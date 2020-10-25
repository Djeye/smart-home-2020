package ru.sbt.mipt.oop.homeobjects.signalingstates;

public interface SignalingState {
    void deactivate(String code);

    void activate(String code);

    void alarm();
}
