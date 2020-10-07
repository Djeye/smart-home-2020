package ru.sbt.mipt.oop;

public class HallDoorEventProcessor {

    public static boolean turnOffLightWhenMainDoorClosed(SmartHome smartHome) {
        boolean isSuccess = false;
        if (smartHome == null) return isSuccess;
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                Application.sendCommand(command);
                isSuccess = true;
            }
        }

        return isSuccess;
    }
}
