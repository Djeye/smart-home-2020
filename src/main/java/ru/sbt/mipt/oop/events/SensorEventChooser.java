package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventChooser {
    SensorEvent getNextSensorEvent();
}
