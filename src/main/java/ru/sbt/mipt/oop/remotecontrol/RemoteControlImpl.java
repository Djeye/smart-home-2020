package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;
import ru.sbt.mipt.rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RemoteControlImpl implements RemoteControl {
    private final String id;
    private final Map<String, Command> commands = new HashMap<>();

    public RemoteControlImpl(String id) {
        this.id = id;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commands.containsKey(buttonCode) && Objects.equals(rcId, id)) {
            commands.get(buttonCode).execute();
        }
    }

    public void setButton(String buttonCode, Command command) {
        commands.put(buttonCode, command);
    }

    public String buttonTranslate(String command) {
        String button = "";

        switch (command) {
            case "closeDoorInHallCommand":
                button = "A";
                break;
            case "turnOnLightInHallCommand":
                button = "B";
                break;
            case "turnOnLightsCommand":
                button = "C";
                break;
            case "turnOffLightsCommand":
                button = "D";
                break;
            case "alarmCommand":
                button = "1";
                break;
            case "signalingCommand":
                button = "2";
                break;
            default:
                button = null;
        }

        return button;
    }
}
