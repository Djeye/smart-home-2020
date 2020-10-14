package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventChooser {
    SensorEvent getNextSensorEvent();
}
