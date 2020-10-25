package ru.sbt.mipt.oop.homeobjects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;
import ru.sbt.mipt.oop.homeobjects.signalingstates.DeactivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.SignalingState;

public class Signaling implements Actionable {
    private SignalingState actualState;
    private String code;

    public Signaling() {
        actualState = new DeactivatedState(this);
    }

    public SignalingState getActualState() {
        return actualState;
    }

    public boolean isCodeCorrect(String code){
        return this.code.equals(code);
    }

    public void setActivationCode(String code){
        this.code = code;
    }

    public void changeState(SignalingState newState) {
        actualState = newState;
    }

    public void activate(String code) {
        actualState.activate(code);
    }

    public void deactivate(String code) {
        actualState.deactivate(code);
    }

    public void callAlarm() {
        actualState.alarm();
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
    }
}
