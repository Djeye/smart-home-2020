package ru.sbt.mipt.oop.executable;

import ru.sbt.mipt.oop.events.EventCycle;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.utils.CommandSender;
import ru.sbt.mipt.oop.utils.SensorEventChooserImpl;
import ru.sbt.mipt.oop.utils.SmartHomeReader;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        String filename = "smart-home-1.js";
        SmartHome smartHome = new SmartHomeReader(filename).readSmartHomeFromGson();

        EventCycle eventCycle = new EventCycle(new SensorEventChooserImpl(),
                new DoorEventProcessor(),
                new LightEventProcessor(),
                new HallDoorEventProcessor(new CommandSender()),
                smartHome);

        eventCycle.doCycle();
    }
}
