package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homes.SmartHome;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DoorEventProcessorTests {
    private SmartHome smartHome;
    private DoorEventProcessor doorEventProcessor;

    @BeforeEach
    void initialize() {
        smartHome = new SmartHome();
        doorEventProcessor = new DoorEventProcessor();
    }

    @Test
    void closedDoor_OpenTest() {
        Door door = new Door(false, "1");
        Room kitchen = new Room(Arrays.asList(new Light("1", false)),
                Arrays.asList(door),
                "kitchen");
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        smartHome.addRoom(kitchen);

        doorEventProcessor.process(smartHome, event);

        assertTrue(door.isOpen());
    }

    @Test
    void openDoor_ClosedTest() {
        Door door = new Door(true, "1");
        Room kitchen = new Room(Arrays.asList(new Light("1", false)),
                Arrays.asList(door),
                "kitchen");
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        smartHome.addRoom(kitchen);

        doorEventProcessor.process(smartHome, event);

        assertFalse(door.isOpen());
    }
}
