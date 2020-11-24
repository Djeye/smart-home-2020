package ru.sbt.mipt.oop.remotecontrol;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.Map;

@Component
public class RemoteControlFactory {
    private final Map<String, Command> buttons;

    public RemoteControlFactory(Map<String, Command> buttons) {
        this.buttons = buttons;
    }

    public Command translateButton(String button) {
        return buttons.get(button);
    }

    public boolean contains(String button) {
        return buttons.containsKey(button);
    }
}
