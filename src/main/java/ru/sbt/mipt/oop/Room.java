package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    private static int lightCounter = 0;
    private static int doorsCounter = 0;


    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public static Room roomBuilder(String lightsString, String doorString, String roomName)
    {
        List<Light> lightList = new ArrayList<>();
        List<Door> doorList = new ArrayList<>();

        for (int i = 0; i < lightsString.length(); i++) {
            lightCounter++;
            lightList.add(new Light(String.valueOf(i+lightCounter), charInterpreter(lightsString.charAt(i))));
        }

        for (int i = 0; i < doorString.length(); i++) {
            doorsCounter++;
            doorList.add(new Door(charInterpreter(doorString.charAt(i)), String.valueOf(i+doorsCounter)));
        }

        return new Room(lightList, doorList, roomName);
    }

    private static boolean charInterpreter(char symbol){
        return 't' == symbol;
    }
}
