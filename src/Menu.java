import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);

    Room[] myHotel = new Room[10];

    static String choice;
    static String roomName;
    static int roomNum = 1;
    static String answer;

    Queue queueObj = new Queue(); //Creating an object of the Queue class

    public Room[] getRooms() {
        return myHotel;
    }

    public void setRooms(Room[] rooms) {
        myHotel = rooms;
    }
    public void menu() {
        System.out.println("======================================================");
        System.out.println("*            Hotel Management System                 *");
        System.out.println("======================================================");
        System.out.println("* V. View all the rooms                              *");
        System.out.println("* A. Add customer to room                            *");
        System.out.println("* E. Display Empty rooms                             *");
        System.out.println("* D. Delete customer from room                       *");
        System.out.println("* F. Find room from customer name                    *");
        System.out.println("* S. Store program array data into a text file       *");
        System.out.println("* L. Load program data back from the file            *");
        System.out.println("* O. View rooms Ordered alphabetically by name       *");
        System.out.println("* 3. Display the names of the first 3 customers      *");
        System.out.println("* Q. Quit Program                                    *");
        System.out.println("======================================================");
        System.out.println("");
        System.out.println("Choose one of the options from above. (E.g: Type 'V' to view all the rooms)");
        do {
            System.out.println();
            System.out.print("Choice : ");
            choice = input.next();
            String selection = choice.toLowerCase(); //This will convert the input value to lowercase. this will help avoid case sensitive issues.
            switch (selection) {
                case "v":
                    String[] myList = viewRooms();
                    for (int x = 0; x < 10; x++) {
                        //This will display the room number and the current owner's name
                        if (!myList[x].equals("e")) {
                            System.out.println("Room No. " + x + " occupied by " + myList[x]);
                            //This will display the rooms which are currently Empty
                        } else {
                            System.out.println("Room No. " + x + " is empty");
                        }
                    }
                    menu();
                    break;
                case "a":
                    System.out.println("Enter room number (1-10)");
                    int room_num = input.nextInt();
                    System.out.println("Enter person name");
                    String room_name = input.next();
                    addCustomer(room_num, room_name);
                    break;
                case "e":
                    displayEmptyRooms();
                    break;
                case "d":
                    System.out.println("please enter the Room's number which you want to vacate");
                    String roomNum = input.next();
                    deleteCustomer(roomNum);
                    menu();
                    break;
                case "f":
                    String find_rooms = input.next();
                    findRoom(find_rooms);
                    break;
                case "s":
                    storeData();
                    menu();
                    break;
                case "l":
                    retrieveData();
                    menu();
                    break;
                case "o":
                    alphabeticalOrder();
                    break;
                case "3":
                    queueObj.displayQueue();
                    menu();
                    break;
                case "q":
                    System.out.println("Thanks");
                    break;
                default:
                    System.out.println("Invalid input! Please Enter one of these letters: V,A,E,D,F,S,L,O,Q");
            }
        }
        while (!(choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("e") || choice.equalsIgnoreCase("d") ||
                choice.equalsIgnoreCase("f") || choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("l") || choice.equalsIgnoreCase("o") ||
                choice.equals("3") || choice.equalsIgnoreCase("q"))); //condition to only let valid range of inputs through.
    }

    public void initialize() {
        for (int x = 0; x < 10; x++) {
            myHotel[x] = new Room(); //Creating 10 Room Objects
        }
    }

    public String[] viewRooms() {
        String[] myList = new String[10];
        for (int x = 0; x < 10; x++) {
            //This will display the room number and the current owner's name
            if (!myHotel[x].getName().equals("e")) {
//                System.out.println("Room No. " + x + " occupied by " + myHotel[x].getName());
                myList[x] = myHotel[x].getName();
                //This will display the rooms which are currently Empty
            } else {
//                System.out.println("Room No. " + x + " is empty");
                myList[x] = "empty";
            }
        }
        return myList;
//        menu();
    }

    public void addCustomer(int room_number, String room_name) {
        boolean invalidRoomNumber; //Declaration of a boolean variable.
        try {
            roomNum = room_number;
            //checks whether the room is already occupied or not
            if (!myHotel[roomNum].getName().equals("e")) {
                invalidRoomNumber = true;
                System.out.println("This room is occupied by: Mr. " + myHotel[roomNum].getName());
                System.out.println("");
                //checks whether the input is within the proper range
            } else if (roomNum >= 0 && roomNum < 10) {
                invalidRoomNumber = false;
                //Error message to be displayed
            } else {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
            }
            //if the input is out of the range of the hotel array this will catch it
        } catch (IndexOutOfBoundsException e) {
            invalidRoomNumber = true;
            System.out.println("Invalid input! Please Enter a value between 1-10");
            System.out.println("");
            //to deal with exceptions regarding null values
        } catch (NullPointerException e) {
            invalidRoomNumber = true;
            System.out.println("Invalid input! Please Enter a value between 1-10");
            System.out.println("");
            //to deal with inputs other than integers
        } catch (InputMismatchException e) {
            invalidRoomNumber = true;
            System.out.println("Invalid input! Please Enter a value between 1-10");
            System.out.println("");
            input.next();
        }


        System.out.println("Enter the name of the customer :");
        //Getting the customer's name
        roomName = room_name;
        //Setting the customer name
        myHotel[roomNum].setName(roomName);
        queueObj.addToQueue(roomName);
        // menu();
    }

    public void displayEmptyRooms() {
        //this method will display all the empty rooms
        for (int x = 0; x < 10; x++) {
            if (myHotel[x].getName().equals("e")) {
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println("");
        // menu();
    }

    public int deleteCustomer(String customerNumber) {
        if (!customerNumber.matches("[0-9]+")) {
            System.out.println("Invalid input! Please Enter a value between 1-10");
            return 0;
        }
        roomNum = Integer.parseInt(customerNumber);
        if (roomNum < 0 || roomNum > 9) {
            System.out.println("Invalid room number. Please enter a value between 0-9");
            return 1;
        }
        //if the hotel room is not empty then this will delete the customer from that room
        if (!(myHotel[roomNum].getName().equals("e"))) {

            myHotel[roomNum].setName("e");
            System.out.println("Room " + roomNum + " has successfully been vacated");

            System.out.println("");
            return 3;
            //if the room is already empty then this message will be displayed
        } else {

            System.out.println("Room " + roomNum + " is already Empty");
            System.out.println("");
            return 4;
        }
    }

    public Boolean findRoom(String find_room) {
        System.out.println("Please enter the name of the customer");
        boolean found = false;
        String find = find_room;


        for (int n = 0; n < 10; n++) {
            //used equalsIgnoreCase to avoid case sensitive issues while searching for a customer
            //this method will find the room's number which is currently being occupied by the mentioned customer
            if (myHotel[n].getName().equalsIgnoreCase(find)) {
                return true;
            }
        }
        return false;
    }

    public void storeData() {
        try {
            //saving Data and overwriting

            String[] storage = new String[10];

            for (int y = 0; y < 10; y++) {
                if(myHotel[y] == null)
                    break;
                storage[y] = myHotel[y].getName();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", false)); //used false to overwrite the file instead of appending
            for (int x = 0; x < storage.length; x++) {
                String file;
                file = storage[x];
                // writes Empty room if it find "e" on the array
                if (file.equals("e")) {
                    bw.write("Empty Room " + x);

                    // writes the name of the customer from the array
                } else {
                    bw.write(file);
                }
                bw.newLine(); //Line Seperator
                bw.flush(); //Flushes the stream.

            }

            //will catch this exception if the Text file is not found
        } catch (IOException e) {
            System.err.println("File not found!");
        }

        //message to show the user that the array data has been saved to a Text file successfully
        System.out.println("Data successfully saved!");
        System.out.println("");
        //menu();
    }

    public void retrieveData() {

        try {
            //reading Data from the Text File
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

            for (int x = 0; x < 10; x++) {
                String read = reader.readLine(); //Reads String value stored in the Text File
                if(read == null)
                    break;
                if (read.equals("Empty Room " + x)) {
                    read = "e";
                }
                //Stored the data in the Hotel Array
                myHotel[x].setName(read);
            }

            //will catch this exception if the Text file is not found
        } catch (IOException e) {
            System.err.println("File not found!");
        }
        //Displays this message if the file is found and successfully loaded the data back from it
        System.out.println("File successfully loaded");
        System.out.println("");
        //menu();
    }

    public void alphabeticalOrder() {

        int index = 0;

        String[] copy = new String[10];
        String[] names = new String[10];

        for (int y = 0; y < 10; y++) {
            copy[y] = myHotel[y].getName();
        }

        //copy hotel array data to names array
        for (int x = 0; x < 10; x++) {
            names[x] = myHotel[x].getName().toLowerCase();//used this to avoid case sensitive issues.
        }

        //Flag Bubble Sort
        boolean flag = true;
        for (int i = 1; i < names.length - 1; i++) {
            flag = false;
            for (int j = i + 1; j < names.length; j++) {
                if (names[j].compareTo(names[i]) < 0) {
                    String temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }

        for (int x = 0; x < 10; x++) {
            myHotel[x].setName(names[x]);
        }

        //This will add the list of names in ascending order in our new array
        for (int x = 1; x < names.length; x++) {
            if (!(names[x].equals("e"))) {

                for (int i = 1; i < copy.length; i++) {
                    if (copy[i].toLowerCase().equals(names[x])) {
                        index = i;
                    }
                }
                System.out.println("Mr. " + names[x] + " is staying in room No. " + index);
            }
        }

    }

}

