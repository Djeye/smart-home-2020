package ru.sbt.mipt.oop.homeobjects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;
import ru.sbt.mipt.oop.homeobjects.signalingstates.DeactivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.State;

public class Signaling implements Actionable {
    private State actualState;
    private String code;

    public Signaling() {
        actualState = new DeactivatedState(this);
    }

    public State getActualState() {
        return actualState;
    }

    public void activate(String code) {
        this.code = code;
        System.out.println("> Signaling was activating");
    }

    public void deactivate(String code) {
        if (this.code.equals(code)) {
            this.code = null;
            System.out.println("> Signaling was deactivating");
        } else {
            actualState.alarm();
        }
    }

    public void callAlarm() {
        System.out.println("IU IU IU");
        System.out.println("PU-U PU-U PU-U");
        System.out.println("DAP DAP DAP DAP");
        System.out.println("VIIIIIUUU VIIIIIUUU");
    }

    public void changeState(State newState) {
        actualState = newState;
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
    }
}
