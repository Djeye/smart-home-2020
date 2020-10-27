package ru.sbt.mipt.oop.adaptors;

import ru.sbt.mipt.api.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class LibraryApiTranslator  {
    public SensorEvent translateSensorEvent(CCSensorEvent event) {
        SensorEventType sensorEventType = null;

        switch (event.getEventType()) {
            case "LightIsOn":
                sensorEventType = SensorEventType.LIGHT_ON;
                break;
            case "LightIsOff":
                sensorEventType = SensorEventType.LIGHT_OFF;
                break;
            case "DoorIsOpen":
                sensorEventType = SensorEventType.DOOR_OPEN;
                break;
            case "DoorIsClosed":
                sensorEventType = SensorEventType.DOOR_CLOSED;
                break;
            case "DoorIsLocked":
                sensorEventType = SensorEventType.DOOR_LOCKED;
                break;
            case "DoorIsUnlocked":
                sensorEventType = SensorEventType.DOOR_UNLOCKED;
                break;
            default:
                return null;
        }

        return new SensorEvent(sensorEventType, event.getObjectId());
    }
}
