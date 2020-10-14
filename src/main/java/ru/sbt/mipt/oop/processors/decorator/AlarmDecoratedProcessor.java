package ru.sbt.mipt.oop.processors.decorator;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homeobjects.signalingstates.ActivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.AlarmState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.State;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.Process;

public class AlarmDecoratedProcessor implements Process {
    private final Process processor;

    public AlarmDecoratedProcessor(Process processor) {
        this.processor = processor;
    }

    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        // вызываем исполнение тревоги при других типах ивентов != типам ивента тревоги
        if (sensorEvent.getType() != SensorEventType.ALARM_ACTIVATE && sensorEvent.getType() != SensorEventType.ALARM_DEACTIVATE) {
            smartHome.execute(object -> {
                if (object.getClass() != Signaling.class) return;

                Signaling signaling = (Signaling) object;
                State state = signaling.getActualState();
                if (state.getClass() == AlarmState.class) return;

                processor.process(smartHome, sensorEvent);
                if (state.getClass() == ActivatedState.class) {
                    state.alarm();
                }
            });
        }
    }
}
