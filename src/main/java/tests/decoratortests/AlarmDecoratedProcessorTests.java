package tests.decoratortests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homeobjects.signalingstates.AlarmState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.DeactivatedState;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.Process;
import ru.sbt.mipt.oop.processors.decorator.AlarmDecoratedProcessor;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmDecoratedProcessorTests {
    private static final String CODE = "123";
    private SmartHome smartHome;
    private Process processor = new AlarmDecoratedProcessor(new DoorEventProcessor());
    private Door door;

    @BeforeEach
    void initialize() {
        door = new Door(false, "1");
        smartHome = new SmartHome();
        Room room = new Room(null,
                Arrays.asList(door),
                "kitchen");
        smartHome.addRoom(room);
    }

    @Test
    void callAlarmWhenDoorOpenTest(){
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");

        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            signaling.getActualState().activate(CODE);
        });
        processor.process(smartHome, sensorEvent);


        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            assertEquals(AlarmState.class, signaling.getActualState().getClass());
        });
    }

    @Test
    void activateThenDeactivateTest(){
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");

        processor.process(smartHome, sensorEvent);
        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = (Signaling) object;
            signaling.getActualState().activate(CODE);
            signaling.getActualState().deactivate(CODE);
        });

        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            assertEquals(DeactivatedState.class, signaling.getActualState().getClass());
        });
    }
}
