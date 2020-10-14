package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.utils.CommandSender;
import ru.sbt.mipt.oop.utils.CommandType;
import ru.sbt.mipt.oop.utils.SensorCommand;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements Process {
    private final CommandSender commandSender;

    public HallDoorEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == DOOR_CLOSED) {
            // событие от двери
            smartHome.execute(eachRoom -> {
                if (eachRoom.getClass() != Room.class) return;
                Room room = (Room) eachRoom;

                if (room.getName().equals("hall")) {
                    room.execute(eachDoor -> {
                        if (eachDoor.getClass() != Door.class) return;
                        Door door = (Door) eachDoor;

                        if (door.getId().equals(sensorEvent.getObjectId())) {
                            turnOffAllLights(smartHome);
                        }
                    });
                }
            });
        }
    }

    private void turnOffAllLights(SmartHome smartHome) {
        smartHome.execute(eachLight -> {
            if (eachLight.getClass() != Light.class) return;
            Light light = (Light) eachLight;
            light.setOn(false);

            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }
}
