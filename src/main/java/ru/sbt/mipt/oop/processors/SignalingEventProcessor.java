package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homeobjects.signalingstates.State;
import ru.sbt.mipt.oop.homes.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class SignalingEventProcessor implements Process{
    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == ALARM_ACTIVATE || sensorEvent.getType() == ALARM_DEACTIVATE) {
            boolean alarmActivate = sensorEvent.getType() == ALARM_ACTIVATE;
            // событие от сигнализации
            smartHome.execute(signaling -> {
                if (signaling.getClass() != Signaling.class) return;
                State state = ((Signaling) signaling).getActualState();

                if (alarmActivate) {
                    state.activate(sensorEvent.getCode());
                } else {
                    state.deactivate(sensorEvent.getCode());
                }
            });
        }
    }
}
