package ru.sbt.mipt.oop.remotecontrol.commands;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homes.SmartHome;

@Component
public class TurnOffLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOffLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object.getClass() != Light.class) {
                return;
            }
            Light light = (Light) object;
            light.setOn(false);
        });
    }
}
