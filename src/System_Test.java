import org.testng.Assert;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class System_Test {
    Menu menu;
    // All functions in main:
    // add customer(D)                  display empty rooms(D)                  find rooms(D)
    // delete customer(D)               view rooms(D)                           initialize(D)
    // retrieve data                    alphabetical order                      store data

    @Test
    void systemTestCase1(){
        System.out.println("This is system test case #1");

        ByteArrayInputStream in = new ByteArrayInputStream("V A 5 Ali V A 6 Ahmed V D 5 v f Ahmed f Ali q".getBytes());
        System.setIn(in);

        menu = new Menu();
        menu.initialize();
        menu.menu();
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));
        menu.displayEmptyRooms();
        System.setOut(oldOut);
        String output = baos.toString();

        for (int x = 0; x < 10; x++) {
            if (menu.myHotel[x].getName().equals("e")) {
                Assert.assertTrue(output.contains("room " + x + " is empty"));
            }
        }
        menu.menu();
        menu.menu();
        String [] rooms = menu.viewRooms();
        for (int i=0; i<10; i++){
            if (i == 6){
                Assert.assertEquals(rooms[i], "Ahmed");
            }
            else{
                Assert.assertEquals(rooms[i], "empty");
            }
        }
        menu.menu();
        menu.menu();
        Assert.assertTrue(menu.findRoom("Ahmed"));
        Assert.assertFalse(menu.findRoom("Ali"));
    }

    @Test
    void systemTestCase2(){
        System.out.println("This is system test case #2");

        ByteArrayInputStream in = new ByteArrayInputStream("V A 1 Maged V A 2 Sameer V D 5 v f Ahmed f Ali q".getBytes());
        System.setIn(in);

        menu = new Menu();
        menu.initialize();
        menu.menu();
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));
        menu.displayEmptyRooms();
        System.setOut(oldOut);
        String output = baos.toString();

        for (int x = 0; x < 10; x++) {
            if (menu.myHotel[x].getName().equals("e")) {
                Assert.assertTrue(output.contains("room " + x + " is empty"));
            }
        }
        menu.menu();
        menu.menu();
        String [] rooms = menu.viewRooms();
        for (int i=0; i<10; i++){
            if (i == 1){
                Assert.assertEquals(rooms[i], "Maged");
            }
            else if(i == 2){
                Assert.assertEquals(rooms[i], "Sameer");
            }
            else{
                Assert.assertEquals(rooms[i], "empty");
            }
        }
        menu.menu();
        menu.menu();
        Assert.assertFalse(menu.findRoom("Ahmed"));
        Assert.assertFalse(menu.findRoom("Ali"));
    }

    @Test
    void systemTestCase3(){
        System.out.println("This is system test case #2");

        ByteArrayInputStream in = new ByteArrayInputStream("V A 1 Maged V A 1 Sameer V D 5 v f Ahmed f Ali q".getBytes());
        System.setIn(in);

        menu = new Menu();
        menu.initialize();
        menu.menu();
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));
        menu.displayEmptyRooms();
        System.setOut(oldOut);
        String output = baos.toString();

        for (int x = 0; x < 10; x++) {
            if (menu.myHotel[x].getName().equals("e")) {
                Assert.assertTrue(output.contains("room " + x + " is empty"));
            }
        }
        menu.menu();
        menu.menu();
        String [] rooms = menu.viewRooms();
        for (int i=0; i<10; i++){
            if (i == 1){
                Assert.assertEquals(rooms[i], "Maged");
            }
            else{
                Assert.assertEquals(rooms[i], "empty");
            }
        }
        menu.menu();
        menu.menu();
        Assert.assertFalse(menu.findRoom("Ahmed"));
        Assert.assertTrue(menu.findRoom("Maged"));
    }
}
