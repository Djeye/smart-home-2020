package ru.sbt.mipt.oop.executable;

import ru.sbt.mipt.oop.events.EventCycle;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.events.SensorEventChooserImpl;
import ru.sbt.mipt.oop.processors.Process;
import ru.sbt.mipt.oop.processors.SignalingEventProcessor;
import ru.sbt.mipt.oop.processors.decorator.AlarmDecoratedProcessor;
import ru.sbt.mipt.oop.processors.decorator.SendMessageDecoratedProcessor;
import ru.sbt.mipt.oop.utils.SmartHomeReaderFromFile;

import java.util.ArrayList;
import java.util.List;

public class OldApplication {

    private static List<Process> initProcesses() {
        List<Process> processes = new ArrayList<>();

        processes.add(new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new DoorEventProcessor())));
        processes.add(new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new LightEventProcessor())));
        processes.add(new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new HallDoorEventProcessor(new CommandSender()))));
        processes.add(new SignalingEventProcessor());

        return processes;
    }

    public static void main(String... args) {
        String filename = "smart-home-1.js";
        SmartHome smartHome = new SmartHomeReaderFromFile(filename).readSmartHome();
        List<Process> processes = initProcesses();

        EventCycle eventCycle = new EventCycle(new SensorEventChooserImpl(), processes, smartHome);

        eventCycle.handle();
    }
}
