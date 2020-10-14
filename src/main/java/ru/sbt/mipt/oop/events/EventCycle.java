package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.processors.SignalingEventProcessor;

public class EventCycle {
    private final SensorEventChooser eventChooser;
    private final DoorEventProcessor doorEventProcessor;
    private final LightEventProcessor lightEventProcessor;
    private final HallDoorEventProcessor hallDoorEventProcessor;
    private final SignalingEventProcessor signalingEventProcessor;
    private final SmartHome smartHome;

    public EventCycle(SensorEventChooser eventChooser, DoorEventProcessor doorEventProcessor, LightEventProcessor lightEventProcessor,
                      HallDoorEventProcessor hallDoorEventProcessor, SignalingEventProcessor signalingEventProcessor, SmartHome smartHome) {
        this.eventChooser = eventChooser;
        this.doorEventProcessor = doorEventProcessor;
        this.lightEventProcessor = lightEventProcessor;
        this.hallDoorEventProcessor = hallDoorEventProcessor;
        this.signalingEventProcessor = signalingEventProcessor;
        this.smartHome = smartHome;
    }

    public void doCycle(){
        SensorEvent event = eventChooser.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            lightEventProcessor.process(smartHome, event);
            doorEventProcessor.process(smartHome, event);
            hallDoorEventProcessor.process(smartHome, event);
            event = eventChooser.getNextSensorEvent();
        }
    }
}
