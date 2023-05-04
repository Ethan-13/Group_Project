package final_project;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name, int numRooms) {
        this.name = name;
        this.setRooms(new ArrayList<>());
        for (int i = 1; i <= numRooms; i++) {
            getRooms().add(new Room(i));
        }
    }

    public String getName() {
        return name;
    }

    public int getNumRooms() {
        return getRooms().size();
    }

    public Room getRoom(int roomNumber) {
        for (Room room : getRooms()) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
