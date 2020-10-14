package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homes.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements Process{
    @Override
    public void process(SmartHome smartHome, SensorEvent sensorEvent) {
        if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF) {
            // событие от источника света
            smartHome.execute(eachLight -> {
                boolean isLightOn;
                if (eachLight.getClass() != Light.class) return;
                Light light = (Light) eachLight;
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    isLightOn = sensorEvent.getType() == LIGHT_ON;
                    light.setOn(isLightOn);
                    System.out.print("Light " + light.getId());

                    if (isLightOn) {
                        System.out.println(" was turned on.");
                    } else {
                        System.out.println(" was turned off.");
                    }
                }
            });
        }
    }
}
