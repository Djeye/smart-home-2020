package ru.sbt.mipt.oop.utils;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.homes.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReader {
    private final String filename;

    public SmartHomeReader(String filename) {
        this.filename = filename;
    }

    public SmartHome readSmartHomeFromGson(){
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
