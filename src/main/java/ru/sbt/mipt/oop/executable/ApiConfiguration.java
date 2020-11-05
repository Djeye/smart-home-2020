package ru.sbt.mipt.oop.executable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.api.events.SensorEventsManager;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.processors.Process;
import ru.sbt.mipt.oop.processors.decorator.AlarmDecoratedProcessor;
import ru.sbt.mipt.oop.processors.decorator.SendMessageDecoratedProcessor;
import ru.sbt.mipt.oop.utils.SmartHomeReaderFromFile;
import ru.sbt.mipt.oop.adaptors.SmartHomeApiAdapter;

import java.util.Collection;

@Configuration
@ComponentScan({"ru.sbt.mipt.oop"})
public class ApiConfiguration {
    @Bean
    public SmartHome readSmartHome() {
        String filename = "smart-home-1.js";
        return new SmartHomeReaderFromFile(filename).readSmartHome();
    }

    @Bean
    public SensorEventsManager getSensorEventsManager(Collection<Process> processes, SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        processes.stream()
                .map(process -> new SmartHomeApiAdapter(process, smartHome))
                .forEach(sensorEventsManager::registerEventHandler);

        return sensorEventsManager;
    }

    @Bean
    public Process getDoorsProcessors() {
        return new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new DoorEventProcessor()));
    }

    @Bean
    public Process getLightsProcessors() {
        return new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new LightEventProcessor()));
    }

    @Bean
    public Process getHallDoorProcessors() {
        return new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new HallDoorEventProcessor(new CommandSender())));
    }
}
