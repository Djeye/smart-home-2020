package ru.sbt.mipt.oop.remotecontrol.commands;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;

@Component
public class CloseDoorInHallCommand implements Command {
    private final SmartHome smartHome;

    public CloseDoorInHallCommand(SmartHome smartHome) {
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
                room.execute(d -> {
                    if (d.getClass() != Door.class) {
                        return;
                    }
                    Door door = (Door) d;
                    door.setOpen(false);
                });
            }
        });
    }
}
