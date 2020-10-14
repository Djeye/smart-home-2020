package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.homeobjects.Signaling;
import ru.sbt.mipt.oop.homeobjects.signalingstates.ActivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.DeactivatedState;
import ru.sbt.mipt.oop.homeobjects.signalingstates.State;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.processors.SignalingEventProcessor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignalingEventProcessorTests {
    private SmartHome smartHome;
    private SignalingEventProcessor signalingEventProcessor;
    boolean isStatesEquals;

    @BeforeEach
    void initialize() {
        smartHome = new SmartHome();
        signalingEventProcessor = new SignalingEventProcessor();
    }


    @Test
    void signalingChangeStateToActiveTest() {
        SensorEventType sensorEventType = SensorEventType.ALARM_ACTIVATE;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");

        signalingEventProcessor.process(smartHome, sensorEvent);
        smartHome.execute(sign -> {
            if (sign.getClass() != Signaling.class) return;
            Signaling signaling = (Signaling) sign;
            isStatesEquals = ActivatedState.class.equals(signaling.getActualState().getClass());
        });

        assertTrue(isStatesEquals);
    }

    @Test
    void signalingChangeStateToDeactiveTest() {
        SensorEventType sensorEventType = SensorEventType.ALARM_DEACTIVATE;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");

        signalingEventProcessor.process(smartHome, sensorEvent);
        smartHome.execute(sign -> {
            if (sign.getClass() != Signaling.class) return;
            Signaling signaling = (Signaling) sign;
            isStatesEquals = DeactivatedState.class.equals(signaling.getActualState().getClass());
        });

        assertTrue(isStatesEquals);
    }

    @Test
    void doNotCallAlarmWithCorrectCodeTest() {
        SensorEventType sensorEventType = SensorEventType.ALARM_ACTIVATE;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");
        String code = "123";
        sensorEvent.setCode(code);

        signalingEventProcessor.process(smartHome, sensorEvent);
        smartHome.execute(sign -> {
            if (sign.getClass() != Signaling.class) return;
            Signaling signaling = (Signaling) sign;
            State state = signaling.getActualState();
            state.deactivate(code);

            isStatesEquals = DeactivatedState.class.equals(signaling.getActualState().getClass());
        });

        assertTrue(isStatesEquals);
    }

    @Test
    void callAlarmWithWrongCodeTest() {
        SensorEventType sensorEventType = SensorEventType.ALARM_ACTIVATE;
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, "1");
        sensorEvent.setCode("123");

        signalingEventProcessor.process(smartHome, sensorEvent);
        smartHome.execute(sign -> {
            if (sign.getClass() != Signaling.class) return;
            Signaling signaling = (Signaling) sign;
            State state = signaling.getActualState();
            state.deactivate("password");

            isStatesEquals = DeactivatedState.class.equals(signaling.getActualState().getClass());
        });

        assertFalse(isStatesEquals);
    }
}
