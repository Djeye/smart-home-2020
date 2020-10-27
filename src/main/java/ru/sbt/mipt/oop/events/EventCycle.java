package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.Process;

import java.util.List;

public class EventCycle implements EventHandler {
    private final SensorEventChooser eventChooser;
    private final List<Process> processes;
    private final SmartHome smartHome;

    public EventCycle(SensorEventChooser eventChooser, List<Process> processes, SmartHome smartHome) {
        this.eventChooser = eventChooser;
        this.processes = processes;
        this.smartHome = smartHome;
    }

    @Override
    public void handle() {
        SensorEvent event = eventChooser.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);

            for (Process proc : processes) {
                proc.process(smartHome, event);
            }

            event = eventChooser.getNextSensorEvent();
        }
    }
}
