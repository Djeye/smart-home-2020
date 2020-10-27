package ru.sbt.mipt.api.events;

public interface EventHandler {

    void handleEvent(CCSensorEvent event);

}
