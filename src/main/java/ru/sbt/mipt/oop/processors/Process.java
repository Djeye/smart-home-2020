package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homes.SmartHome;

public interface Process {
    public void process(SmartHome smartHome, SensorEvent sensorEvent);
}
