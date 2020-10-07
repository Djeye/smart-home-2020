package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.DoorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.Application.readSmartHomeFromFile;

public class DoorEventProcessorTests {

    SmartHome smartHome;
    {
        try {
            smartHome = readSmartHomeFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doorEventOpenDoorCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        boolean isSuccess = DoorEventProcessor.doDoorActions(smartHome, event);;
        assertTrue(isSuccess);
    }

    @Test
    public void doorEventOpenClosedDoorCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "2");

        boolean isSuccess = DoorEventProcessor.doDoorActions(smartHome, event);;
        assertTrue(isSuccess);
    }

    @Test
    public void doorEventCloseClosedDoorCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");

        boolean isSuccess = DoorEventProcessor.doDoorActions(smartHome, event);;
        assertFalse(isSuccess);
    }
}
