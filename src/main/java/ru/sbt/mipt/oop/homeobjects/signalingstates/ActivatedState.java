package ru.sbt.mipt.oop.homeobjects.signalingstates;

import ru.sbt.mipt.oop.homeobjects.Signaling;

public class ActivatedState implements SignalingState {
    private final Signaling signaling;

    public ActivatedState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void deactivate(String code) {
        if (signaling.isCodeCorrect(code)){
            signaling.changeState(new DeactivatedState(signaling));
            signaling.setActivationCode(null);
            System.out.println(" > Signaling successfully deactivated");
        } else {
            alarm();
        }
    }

    @Override
    public void activate(String code) {
        System.out.println(" > Signaling already activated");
    }

    @Override
    public void alarm() {
        signaling.changeState(new AlarmState());
        System.out.println(" > ALARM ! ! !");
    }
}
