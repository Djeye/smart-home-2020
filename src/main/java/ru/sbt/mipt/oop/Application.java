package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = readSmartHomeFromFile();

        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                doLightsAction(smartHome, event);
            } else {
                doDoorActions(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    private static SmartHome readSmartHomeFromFile() throws IOException {
        // считываем состояние дома из файла
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return new Gson().fromJson(json, SmartHome.class);
    }

    private static void doLightsAction(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    boolean isLightOn = event.getType() == LIGHT_ON;
                    light.setOn(isLightOn);
                    System.out.print("Light " + light.getId() + " in room " + room.getName());
                    if (isLightOn) {
                        System.out.println(" was turned on.");
                    } else {
                        System.out.println(" was turned off.");
                    }
                }
            }
        }
    }

    private static void doDoorActions(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    boolean isDoorOpen = event.getType() == DOOR_OPEN;
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
    }

    private static void turnOffLightWhenMainDoorClosed(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
