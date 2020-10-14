package ru.sbt.mipt.oop.homeobjects.signalingstates;

import ru.sbt.mipt.oop.homeobjects.Signaling;

public class DeactivatedState implements State {
    private final Signaling signaling;

    public DeactivatedState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void activate(String code) {
        signaling.changeState(new ActivatedState(signaling));
        signaling.activate(code);
    }

    @Override
    public void alarm() {
        signaling.changeState(new AlarmState());
        signaling.callAlarm();
    }
}
