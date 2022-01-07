package Day23;

import java.util.Arrays;
import java.util.List;

public class Burrow {
    private String hallway;
    private String[] rooms;

    public Burrow(String roomsOne, String roomsTwo) {
        this.hallway = "...........";
        this.rooms = new String[2];
        this.rooms[0] = roomsOne;
        this.rooms[1] = roomsTwo;
    }

    public Burrow(String hallway, String roomsOne, String roomsTwo) {
        this.hallway = hallway;
        this.rooms = new String[2];
        this.rooms[0] = roomsOne;
        this.rooms[1] = roomsTwo;
    }

    @Override
    public String toString() {
        String builder = "#############\n" +
                "#" +
                hallway +
                "#\n" +
                "#" +
                rooms[0] +
                "#\n" +
                rooms[1] +
                "\n" +
                "#############\n";
        return builder;
    }

    public String getHallway() {
        return hallway;
    }

    public String getRooms(int index) {
        return rooms[index];
    }

}
