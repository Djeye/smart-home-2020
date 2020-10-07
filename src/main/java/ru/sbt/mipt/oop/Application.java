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
                LightEventProcessor.doLightsAction(smartHome, event);
            } else {
                DoorEventProcessor.doDoorActions(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    public static SmartHome readSmartHomeFromFile() throws IOException {
        // считываем состояние дома из файла
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return new Gson().fromJson(json, SmartHome.class);
    }

    public static void sendCommand(SensorCommand command) {
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
