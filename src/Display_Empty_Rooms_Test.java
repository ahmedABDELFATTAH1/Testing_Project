import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;

public class Display_Empty_Rooms_Test {
    Menu menu;
    @BeforeMethod
    void initializeHotel()
    {
        System.out.println("before_method");
        System.out.println("Testing displayEmptyRooms function...");
        menu = new Menu();
        menu.initialize(); //Call initialize method
    }
    @Test
    void displayEmptyRoomsTest()
    {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);

        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));

        menu.displayEmptyRooms();

        System.setOut(oldOut);
        System.setIn(sysInBackup);

        // Our baos has the content from the print statement
        String output = baos.toString();

        for (int x = 0; x < 10; x++) {
            if (menu.myHotel[x].getName().equals("e")) {
                Assert.assertTrue(output.contains("room " + x + " is empty"));
            }
        }

    }
}
