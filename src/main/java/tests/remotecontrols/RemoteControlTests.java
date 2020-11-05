package tests.remotecontrols;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbt.mipt.oop.executable.RemoteControlConfiguration;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homeobjects.signalingstates.ActivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.AlarmState;
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
    void pressButton_A_ToCloseHallDoorTest() {
        remoteControl.onButtonPressed("A", "0");

        smartHome.execute(o -> {
            if (o.getClass() != Room.class) {
                return;
            }

            Room room = (Room) o;

            if ("hall".equals(room.getName())) {
                o.execute(d -> {
                    if (d.getClass() == Door.class) {
                        Door door = (Door) d;
                        assertFalse(door.isOpen());
                    }
                });
            }
        });
    }

    @Test
    void pressButton_B_ToTurnOnLightsInHallTest() {
        remoteControl.onButtonPressed("B", "0");

        smartHome.execute(o -> {
            if (o.getClass() != Room.class) {
                return;
            }
            Room room = (Room) o;

            if ("hall".equals(room.getName())) {
                o.execute(l -> {
                    if (l.getClass() == Light.class) {
                        Light light = (Light) l;
                        assertTrue(light.isOn());
                    }
                });
            }
        });
    }

    @Test
    void pressButton_C_ToTurnOnLightsTest() {
        remoteControl.onButtonPressed("C", "0");

        smartHome.execute(o -> {
            if (o.getClass() == Light.class) {
                Light light = (Light) o;
                assertTrue(light.isOn());
            }
        });
    }

    @Test
    void pressButton_D_ToTurnOffLightsTest() {
        remoteControl.onButtonPressed("D", "0");

        smartHome.execute(o -> {
            if (o.getClass() == Light.class) {
                Light light = (Light) o;
                assertFalse(light.isOn());
            }
        });
    }



    @Test
    void pressButton_1_ToAlarmTest() {
        remoteControl.onButtonPressed("1", "0");

        smartHome.execute(o -> {
            if (o.getClass() == Signaling.class) {
                Signaling signaling = (Signaling) o;
                assertTrue(AlarmState.class.equals(signaling.getActualState().getClass()));
            }
        });
    }

    @Test
    @Order(1)
    void pressButton_2_ToSignalingTest() {
        remoteControl.onButtonPressed("2", "0");

        smartHome.execute(o -> {
            if (o.getClass() == Signaling.class) {
                Signaling signaling = (Signaling) o;
                assertTrue(ActivatedState.class.equals(signaling.getActualState().getClass()));
            }
        });
    }

}
