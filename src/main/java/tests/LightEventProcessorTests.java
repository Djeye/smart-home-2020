package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.LightEventProcessor;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightEventProcessorTests {
    private SmartHome smartHome;
    private LightEventProcessor lightEventProcessor;

    @BeforeEach
    void initialize() {
        smartHome = new SmartHome();
        lightEventProcessor = new LightEventProcessor();
    }

    @Test
    void offLight_OnLightTest() {
        Light light = new Light("1", false);
        Room kitchen = new Room(Arrays.asList(light),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        smartHome.addRoom(kitchen);

        lightEventProcessor.process(smartHome, event);

        assertTrue(light.isOn());
    }

    @Test
    void onLight_OffLightTest() {
        Light light = new Light("1", true);
        Room kitchen = new Room(Arrays.asList(light),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        smartHome.addRoom(kitchen);

        lightEventProcessor.process(smartHome, event);

        assertFalse(light.isOn());
    }
}


