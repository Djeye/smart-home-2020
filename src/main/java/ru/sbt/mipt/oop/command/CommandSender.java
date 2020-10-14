package ru.sbt.mipt.oop.command;

public class CommandSender {
    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
