package tests.remotecontrols;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbt.mipt.oop.executable.RemoteControlConfiguration;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.rc.RemoteControl;
import ru.sbt.mipt.oop.executable.ApiConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {ApiConfiguration.class, RemoteControlConfiguration.class})
public class RemoteControlTests {
    @Autowired
    private SmartHome smartHome;
    @Autowired
    private RemoteControl remoteControl;

    @Test
    @Order(1)
    void onButtonPressed_turnOnAllLight_whenPressedButtonC() {
        //when
        remoteControl.onButtonPressed("A", "0");
        //then
        smartHome.execute(o -> {
            if (o instanceof Light) {
                Light light = (Light) o;
                assertTrue(light.isOn());
            }
        });
    }
}
