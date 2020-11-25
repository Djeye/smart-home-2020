package ru.sbt.mipt.oop.adaptors;

import ru.sbt.mipt.api.events.CCSensorEvent;
import ru.sbt.mipt.api.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.Process;

public class SmartHomeApiAdapter implements EventHandler {
    private final ApiFactory factory;
    private final SmartHome smartHome;
    private final Process process;

    public SmartHomeApiAdapter(ApiFactory factory, SmartHome smartHome, Process process) {
        this.factory = factory;
        this.process = process;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = factory.translateSensorEvent(event);
        process.process(smartHome, sensorEvent);
    }
}
