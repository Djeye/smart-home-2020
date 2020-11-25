package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.command.CommandSender;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HallDoorEventProcessorTests {
    private SmartHome smartHome;
    private HallDoorEventProcessor hallDoorEventProcessor;

    @BeforeEach
    void initialize() {
        smartHome = new SmartHome();
        hallDoorEventProcessor = new HallDoorEventProcessor(new CommandSender());
    }

    @Test
    void hallCloseDoor_TurnOffAllLightsTest() {
        Door door = new Door(true, "1");
        Light lightOne = new Light("1", true);
        Light lightTwo = new Light("2", false);
        Light lightThree = new Light("3", true);
        Room hall = new Room(Arrays.asList(lightOne, lightTwo, lightThree),
                Arrays.asList(door),
                "hall");
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        smartHome.addRoom(hall);

        hallDoorEventProcessor.process(smartHome, event);

        assertFalse(lightOne.isOn());
        assertFalse(lightTwo.isOn());
        assertFalse(lightThree.isOn());
    }

    @Test
    void hallOpenDoor_NotTurnOffAllLightsTest() {
        Door door = new Door(true, "1");
        Light lightOne = new Light("1", true);
        Light lightTwo = new Light("2", false);
        Light lightThree = new Light("3", true);
        Room hall = new Room(Arrays.asList(lightOne, lightTwo, lightThree),
                Arrays.asList(door),
                "hall");
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        smartHome.addRoom(hall);

        hallDoorEventProcessor.process(smartHome, event);

        assertTrue(lightOne.isOn());
        assertFalse(lightTwo.isOn());
        assertTrue(lightThree.isOn());
    }
}
