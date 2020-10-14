package ru.sbt.mipt.oop.homeobjects.signalingstates;

import ru.sbt.mipt.oop.homeobjects.Signaling;

public class ActivatedState implements State {
    private final Signaling signaling;

    public ActivatedState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void deactivate(String code) {
        signaling.changeState(new DeactivatedState(signaling));
        signaling.deactivate(code);
    }

    @Override
    public void activate(String code) {}

    @Override
    public void alarm() {
        signaling.changeState(new AlarmState());
        signaling.callAlarm();
    }
}
