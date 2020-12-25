import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class Integration_Testing {
    // add customer **  -------               view rooms   -------                   initialize  --------
    // delete customer   ------               display empty rooms   -------                find rooms --------
    // retrieve data                    alphabetical order                  store data

    Menu menu = null; //Creating an object of the Menu class
    Queue queue;
    String[] data = new String[10];
    void readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
        for (int x = 0; x < 10; x++) {
            String read = reader.readLine(); //Reads String value stored in the Text File
            if(read == null)
                break;
            if (read.equals("Empty Room " + x)) {
                read = "e";
            }
            //Stored the data in the Hotel Array
            data[x] = read;
        }
    }

    @BeforeClass
    void initializationTest(){
        System.out.println("This is initialization test");
        menu =new Menu();
        menu.initialize();
        for (int x = 0; x < 10; x++) {
            Assert.assertEquals(menu.myHotel[x].getName(),"e");
        }
        queue = menu.queueObj;
        for(int i =0;i<7;i++) {
            if (!queue.queueItems[0].equals("e")) {
                Assert.fail("error: queue constructor didn't initialize element #" + Integer.toString(i) + " to Empty");
            }
        }
    }

    @Test(priority = 0)
    void test_add_queue(){
        System.out.println("This is test case # 1");
        menu.addCustomer(0,"Waleed");
        menu.addCustomer(1,"Ali");
        menu.addCustomer(8,"Ta7aaaaaa");
        menu.addCustomer(9,"Ezzat");
        Assert.assertEquals(menu.myHotel[0].getName(),"Waleed");
        Assert.assertEquals(menu.myHotel[1].getName(),"Ali");
        Assert.assertEquals(menu.myHotel[8].getName(),"Ta7aaaaaa");
        Assert.assertEquals(menu.myHotel[9].getName(),"Ezzat");
        Assert.assertEquals(queue.queueItems[0],"Waleed");
        Assert.assertEquals(queue.queueItems[1],"Ali");
        Assert.assertEquals(queue.queueItems[2],"Ta7aaaaaa");
        Assert.assertEquals(queue.queueItems[3],"Ezzat");
    }
    @Test(priority = 1)
    void test_view_delete(){
        String[] output = menu.viewRooms();
        for (int x = 0; x < 10; x++) {
            if(x==0 && !output[x].equals("Waleed")){
                Assert.fail("element # 0 isn't equal to Waleed");
            }
            if(x==1 && !output[x].equals("Ali")){
                Assert.fail("element # 1 isn't equal to Ali");
            }
            if(x==8 && !output[x].equals("Ta7aaaaaa")){
                Assert.fail("element # 8 isn't equal to Ta7aaaaaa");
            }
            if(x==9 && !output[x].equals("Ezzat")){
                Assert.fail("element # 9 isn't equal to Ezzat");
            }
            if(x!=0&&x!=1&&x!=8&&x!=9&&!output[x].equals("empty")){
                Assert.fail("one of the elements isn't equal to empty");
            }
        }
        int out = menu.deleteCustomer("0");
        Assert.assertEquals(out, 3);
        Assert.assertEquals(queue.queueItems[0],"e");
        out = menu.deleteCustomer("5");
        Assert.assertEquals(out, 4);
        Assert.assertEquals(queue.queueItems[6],"e");
    }
    @Test(priority = 2)
    void test_display_find(){
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
        String output2 = baos.toString();

        for (int x = 0; x < 10; x++) {
            if (menu.myHotel[x].getName().equals("e")) {
                Assert.assertTrue(output2.contains("room " + x + " is empty"));
            }
        }
        String room_name = "dal";
        Boolean result = menu.findRoom(room_name);
        Assert.assertFalse(result);
    }

    @Test(priority = 3)
    void store_retrieve() throws IOException {
        menu.storeData();
        menu.retrieveData();
        Room[] r = menu.getRooms();
        for(int i = 0 ; i <r.length ; i ++) {
            if(i == 1)
                assertEquals(r[i].getName(), "Ali");
            else if(i == 8)
                assertEquals(r[i].getName(), "Ta7aaaaaa");
            else if(i == 9)
                assertEquals(r[i].getName(), "Ezzat");
            else
                assertEquals(r[i].getName(), "e");
        }
    }
}
