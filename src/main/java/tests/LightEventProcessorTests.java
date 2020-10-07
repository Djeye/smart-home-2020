package tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.Application.readSmartHomeFromFile;

public class LightEventProcessorTests {

    SmartHome smartHome;
    {
        try {
            smartHome = readSmartHomeFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void lightEventIsOnCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");

        boolean isSuccess = LightEventProcessor.doLightsAction(smartHome, event);;
        assertTrue(isSuccess);
    }

    @Test
    public void lightEventTurnOffCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "2");

        boolean isSuccess = LightEventProcessor.doLightsAction(smartHome, event);;
        assertFalse(isSuccess);
    }

    @Test
    public void lightEvenTryTurnOff_OffLightCheck(){
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "4");

        boolean isSuccess = LightEventProcessor.doLightsAction(smartHome, event);;
        assertFalse(isSuccess);
    }
}
