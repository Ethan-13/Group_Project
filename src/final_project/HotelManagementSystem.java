package final_project;

import java.io.IOException;
import java.util.Scanner;
public class HotelManagementSystem {
    public static void start () {
        Hotel hotel = new Hotel("My Hotel", 10);
        Scanner scanner = new Scanner(System.in);

        //Check admin to enter the program
        String adminName;
        String password;
        checkAdmin admin = new checkAdmin();
        do {

            System.out.print("User Name: ");
            adminName = scanner.next();
            System.out.print("Enter Password: ");
            password = scanner.next();
            if(!adminName.equals(admin.admin1) && !adminName.equals(admin.admin2) && !adminName.equals(admin.admin3)) {
                admin.Userinputerror();
            }
            else if (!password.equals(admin.passcode)) {
                admin.errormessage();
            }
            else {
                System.out.println(admin.ToString() + adminName + "!");
            }
        }
        while((!adminName.equals(admin.admin1) && !adminName.equals(admin.admin2) && !adminName.equals(admin.admin3)) || !password.equals(admin.passcode));

        //Let the admin choose the program with switch case
        int choice;
        do {
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Room");
            System.out.println("5. Extras");
            System.out.println("6. View Room Status");
            System.out.println("7. Save Guests to file");
            System.out.println("8. View Guests History");
            System.out.println("9. Exit\n");
            System.out.print("Enter your choice: ");
            choice = 0;
            try {
                choice = Integer.parseInt(scanner.next());
            }
            catch(NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("\nEnter guest First name: ");
                    String name = scanner.next();
                    System.out.print("Enter guest emailaddress: ");
                    String address = scanner.next();
                    System.out.print("Enter guest phone: ");
                    String phone = scanner.next();
                    System.out.print("How many days do the guest want to stay? ");
                    int days = 0;
                    try {
                        days = Integer.parseInt(scanner.next());
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        continue;
                    }
                    Guest guest = new Guest(name, address, phone, days);
                    System.out.println("Room Number 1 - 4 is deluxe. Room Number 5 - 10 is Standard");
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    Room room = hotel.getRoom(roomNumber);
                    if (room != null) {
                        if (!room.isOccupied()) {
                            room.checkIn(guest);
                        } else {
                            System.out.println("Room " + roomNumber + " is already occupied.");
                        }
                    } else {
                        System.out.println("Room " + roomNumber + " does not exist.");
                    }
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    room = hotel.getRoom(roomNumber);
                    if (room != null) {
                        if (room.isOccupied()) {
                            room.calculate();
                            room.checkOut();
                        } else {
                            System.out.println("Room " + roomNumber + " is not occupied.");
                        }
                    } else {
                        System.out.println("Room " + roomNumber + " does not exist.");
                    }
                    break;
                case 3:
                    System.out.print("Enter guest name: ");
                    name = scanner.next();
                    System.out.print("Enter guest emailaddress: ");
                    address = scanner.next();
                    System.out.print("Enter guest phone: ");
                    phone = scanner.next();
                    System.out.print("How many days do the guest want to stay? ");
                    days = 0;
                    try {
                        days = Integer.parseInt(scanner.next());
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        continue;
                    }
                    guest = new Guest(name, address, phone, days);
                    System.out.println("Room Number 1 - 4 is deluxe. Room Number 5 - 10 is Standard");
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    room = hotel.getRoom(roomNumber);
                    if (room != null) {
                        if (!room.isOccupied()) {
                            room.bookroom(guest);
                        } else {
                            System.out.println("Room " + roomNumber + " is already occupied.");
                        }
                    } else {
                        System.out.println("Room " + roomNumber + " does not exist.");
                    }
                    break;

                case 4:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    room = hotel.getRoom(roomNumber);
                    if (room != null) {
                        if (room.isbooked()) {
                            room.cancelbooking();
                        } else {
                            System.out.println("Room " + roomNumber + " is not booked.");
                        }
                    } else {
                        System.out.println("Room " + roomNumber + " does not exist.");
                    }
                    break;

                case 5:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    room = hotel.getRoom(roomNumber);
                    if (room != null) {
                        if (room.isOccupied()) {
                            int pick;
                            do {
                                System.out.println("1. Give Room service ($25)");
                                System.out.println("2. Extrabeds ($50)");
                                System.out.println("3. Get pool access ($30)");
                                System.out.println("4. Back to main menu\n");
                                System.out.print("Enter your choice: ");
                                pick = 0;
                                try {
                                    pick = Integer.parseInt(scanner.next());
                                }
                                catch(NumberFormatException e) {
                                    System.out.println("Invalid input! Please enter a number.");
                                    continue;
                                }
                                switch (pick) {
                                    case 1:
                                        room.takeroomservice();
                                        break;
                                    case 2:
                                        room.takeextrabed();
                                        break;
                                    case 3:
                                        room.poolaccess();
                                        break;
                                    default:
                                        System.out.println("Choose 1-4 only");
                                }
                            }
                            while(pick != 4);
                        } else {
                            System.out.println("Room " + roomNumber + " is not occupied.");
                        }
                    } else {
                        System.out.println("Room " + roomNumber + " does not exist.");
                    }
                    break;


                case 6:
                    System.out.println("\nRoom\tOccupied Status\t\tGuest");
                    for (Room r : hotel.getRooms()) {
                        System.out.print(r.getRoomNumber() + "\t");
                        System.out.print(r.roomstatus() + "\t\t");
                        if (r.isOccupied() || r.isbooked()) {
                            System.out.print(r.getGuest().getName());
                        }
                        System.out.println();
                    }
                    System.out.println();
                    break;

                case 7:
                    try {
                        Room.saveGuestsToFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    try {
                        Room.loadGuestsFromFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 9:
                    System.out.println("Thank you for using " + hotel.getName() + "!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 9);

        scanner.close();


    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        start();
    }
}
