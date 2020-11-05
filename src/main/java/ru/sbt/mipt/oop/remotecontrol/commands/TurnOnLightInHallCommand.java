package ru.sbt.mipt.oop.remotecontrol.commands;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;

@Component
public class TurnOnLightInHallCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnLightInHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object.getClass() != Room.class) {
                return;
            }
            Room room = (Room) object;
            if ("Hall".equals(room.getName())) {
                room.execute(l -> {
                    if (l.getClass() != Light.class) {
                        return;
                    }
                    Light light = (Light) l;
                    light.setOn(true);
                });
            }
        });
    }
}
