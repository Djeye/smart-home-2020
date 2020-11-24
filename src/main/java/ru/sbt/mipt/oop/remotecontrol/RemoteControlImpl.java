package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.rc.RemoteControl;

import java.util.Objects;

public class RemoteControlImpl implements RemoteControl {
    private final String id;
    private final RemoteControlFactory factory;

    public RemoteControlImpl(String id, RemoteControlFactory factory) {
        this.id = id;
        this.factory = factory;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (Objects.equals(rcId, id) && factory.contains(buttonCode)) {
            factory.translateButton(buttonCode).execute();
        }
    }
}
