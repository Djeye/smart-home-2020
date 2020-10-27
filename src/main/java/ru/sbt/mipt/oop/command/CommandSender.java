package ru.sbt.mipt.oop.command;

public class CommandSender {
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    public void sendAlarmMessage(){
        System.out.println("SMS от номера 900: ТРЕВОГА!!!");
    }
}
