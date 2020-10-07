package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor {

    public static boolean doLightsAction(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        boolean isLightOn = false;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    isLightOn = event.getType() == LIGHT_ON;
                    light.setOn(isLightOn);
                    System.out.print("Light " + light.getId() + " in room " + room.getName());
                    if (isLightOn) {
                        System.out.println(" was turned on.");
                    } else {
                        System.out.println(" was turned off.");
                    }
                }
            }
        }
        return isLightOn;
    }
}
