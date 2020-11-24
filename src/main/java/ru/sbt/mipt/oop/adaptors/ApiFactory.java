package ru.sbt.mipt.oop.adaptors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.api.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.Map;

@Component
public class ApiFactory {

    private final Map<String, SensorEventType> eventTypeMap;

    public ApiFactory(Map<String, SensorEventType> eventTypeMap) {
        this.eventTypeMap = eventTypeMap;
    }

    public SensorEvent translateSensorEvent(CCSensorEvent event) {
        return new SensorEvent(eventTypeMap.get(event.getEventType()),
                event.getObjectId());
    }
}
