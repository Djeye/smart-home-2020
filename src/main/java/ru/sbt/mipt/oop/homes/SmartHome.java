package ru.sbt.mipt.oop.homes;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homeobjects.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    private final Signaling signaling = new Signaling();

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        if (rooms != null) {
            for (Room room : rooms) {
                room.execute(action);
            }
        }
        signaling.execute(action);
    }
}
