package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homes.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class SignalingEventProcessor implements Process{
    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == ALARM_ACTIVATE || sensorEvent.getType() == ALARM_DEACTIVATE) {
            boolean alarmActivate = sensorEvent.getType() == ALARM_ACTIVATE;
            // событие от сигнализации
            smartHome.execute(s -> {
                if (s.getClass() != Signaling.class) return;
                Signaling signaling = (Signaling) s;

                if (alarmActivate) {
                    signaling.activate(sensorEvent.getCode());
                } else {
                    signaling.deactivate(sensorEvent.getCode());
                }
            });
        }
    }
}
