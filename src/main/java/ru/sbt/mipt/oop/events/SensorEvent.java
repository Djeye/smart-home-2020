package ru.sbt.mipt.oop.events;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private String code;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public void setCode(String code) {
        if (type == SensorEventType.ALARM_ACTIVATE || type == SensorEventType.ALARM_DEACTIVATE) {
            this.code = code;
        }
    }

    public String getCode() {
        return code;
    }
}
