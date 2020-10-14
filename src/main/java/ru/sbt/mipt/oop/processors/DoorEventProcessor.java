package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homes.SmartHome;


import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements Process {

    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
            // событие от двери
            smartHome.execute(eachDoor -> {
                boolean isDoorOpen;
                if (eachDoor.getClass() != Door.class) return;
                Door door = (Door) eachDoor;
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    isDoorOpen = sensorEvent.getType() == DOOR_OPEN;
                    door.setOpen(isDoorOpen);
                    System.out.print("Door " + door.getId());
                    if (isDoorOpen) {
                        System.out.println(" was opened.");
                    } else {
                        System.out.println(" was closed.");
                    }
                }
            });
        }
    }
}