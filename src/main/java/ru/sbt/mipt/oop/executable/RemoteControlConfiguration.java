package ru.sbt.mipt.oop.executable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.remotecontrol.commands.Command;
import ru.sbt.mipt.rc.RemoteControl;
import ru.sbt.mipt.rc.RemoteControlRegistry;

import java.util.Collection;
import java.util.Map;

@Configuration
public class RemoteControlConfiguration {
    private String RemoteControlId = "0";

    @Bean
    public RemoteControlRegistry remoteControlRegistry(Collection<RemoteControl> remoteControls) {
        RemoteControlRegistry registry = new RemoteControlRegistry();

        remoteControls.forEach(o -> {
            registry.registerRemoteControl(o, RemoteControlId);
            RemoteControlId = String.valueOf(Integer.parseInt(RemoteControlId) + 1);
        });

        return registry;
    }

    @Bean
    public RemoteControlImpl smartHomeRegistry(Map<String, Command> commands) {
        RemoteControlImpl remoteControl = new RemoteControlImpl("0");

        commands.forEach((s, c) -> {
            remoteControl.setButton(remoteControl.buttonTranslate(s), c);
        });

        return remoteControl;
    }
}
