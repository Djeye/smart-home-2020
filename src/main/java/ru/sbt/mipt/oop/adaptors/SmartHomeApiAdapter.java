package ru.sbt.mipt.oop.adaptors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.api.events.CCSensorEvent;
import ru.sbt.mipt.api.events.EventHandler;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.Process;

@Component
public class SmartHomeApiAdapter implements EventHandler {
    private final LibraryApiTranslator translator = new LibraryApiTranslator();
    private final SmartHome smartHome;
    private final Process process;

    public SmartHomeApiAdapter(Process process, SmartHome smartHome) {
        this.process = process;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        process.process(smartHome, translator.translateSensorEvent(event));
    }
}
