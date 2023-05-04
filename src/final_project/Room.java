package final_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomNumber;
    private boolean occupied;
    private Guest guest;
    private boolean Roomservice;
    private boolean booked;
    boolean extrabed;
    boolean pool;
    static List<String> guestHistory = new ArrayList<>();

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.occupied = false;
        this.booked = false;
        this.Roomservice = false;
        this.extrabed = false;
        this.pool = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isbooked() {
        return booked;
    }

    public Guest getGuest() {
        return guest;
    }

    public void bookroom(Guest guest) {
        this.guest = guest;
        this.booked = true;
        System.out.println("Guest " + guest.getName() + " has successfully booked a room.\n");
    }

    public void cancelbooking() {
        this.guest = null;
        this.booked = false;
        System.out.println("Room " + roomNumber + " booking has been canceled\n");
    }

    public void checkIn(Guest guest) {
        this.guest = guest;
        this.occupied = true;
        System.out.println("Guest " + guest.getName() + " has checked into room " + roomNumber + ".\n");
    }

    public void checkOut() {
        guestHistory.add(getGuest().getName() + "\t\t\t" + getGuest().getPhone());
        this.guest = null;
        this.occupied = false;

        System.out.println("Room " + roomNumber + " has been checked out.\n");
    }
    public void calculate() {
        double check = 0.0;
        if(roomNumber < 5) {
            check = 500.0 * getGuest().getdays();
        }
        else {
            check = 250.0 * getGuest().getdays();
        }
        if (Roomservice) {
            check += 25.0;
        }
        if (extrabed) {
            check += 50.0;
        }
        if (pool) {
            check += 30.0;
        }
        System.out.println("Total fees for the room: $" + check);
    }

    public void takeroomservice() {
        this.Roomservice = true;
    }

    public void takeextrabed() {
        this.extrabed = true;
    }

    public void poolaccess() {
        this.pool = true;
    }

    public String roomstatus() {
        if (isOccupied()) {
            return "Occupied";
        }
        else if (isbooked()) {
            return "booked  ";
        }
        return null;
    }

    public static void saveGuestsToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("guestsHistory.txt"))) {
            for (String g : guestHistory) {
                writer.write(g + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }

    public static void loadGuestsFromFile() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader("guestsHistory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }
}
