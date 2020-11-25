package ru.sbt.mipt.oop.executable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.api.events.SensorEventsManager;
import ru.sbt.mipt.oop.adaptors.ApiFactory;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.processors.decorator.AlarmDecoratedProcessor;
import ru.sbt.mipt.oop.processors.decorator.SendMessageDecoratedProcessor;
import ru.sbt.mipt.oop.utils.SmartHomeReaderFromFile;
import ru.sbt.mipt.oop.adaptors.SmartHomeApiAdapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan({"ru.sbt.mipt.oop"})
public class ApiConfiguration {
    @Bean
    public SmartHome smartHome() {
        String filename = "smart-home-1.js";
        return new SmartHomeReaderFromFile(filename).readSmartHome();
    }

    @Bean
    public Map<String, SensorEventType> eventTypeMap(){
        Map<String, SensorEventType> factory = new HashMap<>();

        factory.put("LightIsOn", SensorEventType.LIGHT_ON);
        factory.put("LightIsOff", SensorEventType.LIGHT_OFF);
        factory.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        factory.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        factory.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        factory.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);

        return factory;
    }

    @Bean
    public SensorEventsManager sensorEventsManager(Collection<SmartHomeApiAdapter> processes) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        processes.forEach(sensorEventsManager::registerEventHandler);

        return sensorEventsManager;
    }

    @Bean
    public SmartHomeApiAdapter doorProcessorAdapter(SmartHome smartHome, ApiFactory factory) {
        return new SmartHomeApiAdapter(factory, smartHome,
                new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new DoorEventProcessor())));
    }

    @Bean
    public SmartHomeApiAdapter lightProcessorAdapter(SmartHome smartHome, ApiFactory factory) {
        return new SmartHomeApiAdapter(factory, smartHome,
                new SendMessageDecoratedProcessor(new AlarmDecoratedProcessor(new LightEventProcessor())));
    }

    @Bean
    public SmartHomeApiAdapter hallDoorProcessorAdapter(SmartHome smartHome, ApiFactory factory) {
        return new SmartHomeApiAdapter(factory, smartHome,
                new SendMessageDecoratedProcessor(
                        new AlarmDecoratedProcessor(new HallDoorEventProcessor(new CommandSender()))));
    }
}
