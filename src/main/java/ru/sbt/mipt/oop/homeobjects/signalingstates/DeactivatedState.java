package ru.sbt.mipt.oop.homeobjects.signalingstates;

import ru.sbt.mipt.oop.homeobjects.Signaling;

public class DeactivatedState implements SignalingState {
    private final Signaling signaling;

    public DeactivatedState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void deactivate(String code) {
        System.out.println(" > Signaling already deactivated");
    }

    @Override
    public void activate(String code) {
        signaling.changeState(new ActivatedState(signaling));
        signaling.setActivationCode(code);
        System.out.println(" > Signaling successfully activated");
    }

    @Override
    public void alarm() {
        signaling.changeState(new AlarmState());
        System.out.println(" > ALARM ! ! !");
    }
}
