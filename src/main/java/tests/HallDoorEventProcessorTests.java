package tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.Application.readSmartHomeFromFile;

public class HallDoorEventProcessorTests {

    final String HALL_ID = "4";
    final String NOT_HALL_ID = "2";

    SmartHome smartHome;
    {
        try {
            smartHome = readSmartHomeFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notHallCheck(){
        SmartHome emptyHome = new SmartHome();

        boolean isSuccess = HallDoorEventProcessor.turnOffLightWhenMainDoorClosed(emptyHome);;

        assertFalse(isSuccess);
    }

    @Test
    public void turnOffAllLightInTheHomeCheck(){
        boolean isSuccess = HallDoorEventProcessor.turnOffLightWhenMainDoorClosed(smartHome);;

        assertTrue(isSuccess);
    }

}
