package tests.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.Process;
import ru.sbt.mipt.oop.processors.decorator.SendMessageDecoratedProcessor;

import java.util.Arrays;

public class SendMessageDecoratedProcessorTests {
    private static final String CODE = "123";
    private SmartHome smartHome;
    private Process processor = new SendMessageDecoratedProcessor(new DoorEventProcessor());
    private SensorEvent event;
    private Door door;

    @BeforeEach
    void initialize() {
        door = new Door(false, "1");
        smartHome = new SmartHome();
        Room room = new Room(null,
                Arrays.asList(door),
                "kitchen");
        smartHome.addRoom(room);

        event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    @Test
    void sendMessageWhenActivateTest() {
        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            signaling.getActualState().activate(CODE);
        });

        processor.process(smartHome, event);
    }

    @Test
    void doNotSendMessageWhenActivateAndDeactivateTest() {
        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            signaling.getActualState().activate(CODE);
            signaling.getActualState().deactivate(CODE);
        });

        processor.process(smartHome, event);
    }

    @Test
    void sendMessageWhenActivateAndDeactivateWithWrongCodeTest() {
        smartHome.execute(object -> {
            if (object.getClass() != Signaling.class) return;

            Signaling signaling = ((Signaling) object);
            signaling.getActualState().activate(CODE);
            signaling.getActualState().deactivate(CODE + CODE);
        });

        processor.process(smartHome, event);
    }
}
