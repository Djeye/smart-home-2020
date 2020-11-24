package ru.sbt.mipt.oop.executable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlFactory;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.remotecontrol.commands.Command;
import ru.sbt.mipt.rc.RemoteControl;
import ru.sbt.mipt.rc.RemoteControlRegistry;

import java.util.Collection;
import java.util.HashMap;
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
    public RemoteControlImpl remoteControlImpl(RemoteControlFactory factory) {
        return new RemoteControlImpl("0", factory);
    }

    @Bean
    public Map<String, Command> buttons(Map<String, Command> commands) {
        Map<String, String> translateFactory = new HashMap<>();
        Map<String, Command> buttonsFactory = new HashMap<>();

        translateFactory.put("closeDoorInHallCommand", "A");
        translateFactory.put("turnOnLightInHallCommand", "B");
        translateFactory.put("turnOnLightsCommand", "C");
        translateFactory.put("turnOffLightsCommand", "D");
        translateFactory.put("alarmCommand", "1");
        translateFactory.put("signalingCommand", "2");

        commands.forEach((K, V) -> buttonsFactory.put(translateFactory.get(K), V));

        return buttonsFactory;
    }
}
