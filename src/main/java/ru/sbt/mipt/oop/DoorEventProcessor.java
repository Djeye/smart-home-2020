package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.HallDoorEventProcessor.turnOffLightWhenMainDoorClosed;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor {

    public static boolean doDoorActions(SmartHome smartHome, SensorEvent event) {
        boolean isDoorOpen = false;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    isDoorOpen = event.getType() == DOOR_OPEN;
                    door.setOpen(isDoorOpen);
                    System.out.print("Door " + door.getId() + " in room " + room.getName());
                    if (isDoorOpen) {
                        System.out.println(" was opened.");
                    } else {
                        System.out.println(" was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            turnOffLightWhenMainDoorClosed(smartHome);
                        }
                    }
                }
            }
        }
        return isDoorOpen;
    }
}
