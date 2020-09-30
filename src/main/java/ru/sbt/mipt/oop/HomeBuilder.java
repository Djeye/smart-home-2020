package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

public class HomeBuilder {

    public static Collection<Room> buildHomeFromRooms() {
        Room kitchen = Room.roomBuilder("ft", "f", "kitchen");

        Room bathroom = Room.roomBuilder("t", "f", "bathroom");

        Room bedroom = Room.roomBuilder("fff", "t", "bedroom");

        Room hall = Room.roomBuilder("fff", "f", "hall");

        return Arrays.asList(kitchen, bathroom, bedroom, hall);
    }

    private static String printStatusOfBuildingHome(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        return jsonString;
    }

    private static void writeSmartHomeToFile(String jsonString) throws IOException {
        Path path = Paths.get("output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    public static void main(String[] args) throws IOException {
        SmartHome smartHome = new SmartHome(buildHomeFromRooms());
        String jsonString = printStatusOfBuildingHome(smartHome);
        writeSmartHomeToFile(jsonString);
    }
}
