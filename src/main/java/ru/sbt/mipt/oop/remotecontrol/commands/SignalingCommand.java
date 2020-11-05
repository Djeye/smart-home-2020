package ru.sbt.mipt.oop.remotecontrol.commands;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homes.SmartHome;

@Component
public class SignalingCommand implements Command {
    private final SmartHome smartHome;
    private static final String DEFAULT_CODE = "0000";

    public SignalingCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object.getClass() == Signaling.class) {
                ((Signaling) object).activate(DEFAULT_CODE);
            }
        });
    }
}
