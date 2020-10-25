package ru.sbt.mipt.oop.homeobjects.signalingstates;

public class AlarmState implements SignalingState {
    @Override
    public void deactivate(String code) {
        System.out.println(" > ALARM ! ! !");
    }

    @Override
    public void activate(String code) {
        System.out.println(" > ALARM ! ! !");
    }

    @Override
    public void alarm() {
        System.out.println(" > ALARM ! ! !");
    }
}
