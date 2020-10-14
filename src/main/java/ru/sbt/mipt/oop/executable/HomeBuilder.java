package ru.sbt.mipt.oop.executable;

import ru.sbt.mipt.oop.homeobjects.Door;
import ru.sbt.mipt.oop.homeobjects.Light;
import ru.sbt.mipt.oop.homeobjects.Room;
import ru.sbt.mipt.oop.homes.SmartHome;
import ru.sbt.mipt.oop.utils.SmartHomeWriter;

import java.util.Arrays;

public class HomeBuilder {
    public SmartHome buildHome() {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

    public static void main(String[] args) {
        String filename = "output.js";
        HomeBuilder homeBuilder = new HomeBuilder();
        SmartHome smartHome = homeBuilder.buildHome();
        SmartHomeWriter smartHomeWriter = new SmartHomeWriter(filename);
        smartHomeWriter.writeSmartHomeToGson(smartHome);
    }
}
