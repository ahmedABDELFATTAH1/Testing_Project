import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Integration_Testing {
    // add customer **  -------               view rooms   -------                   initialize  --------
    // delete customer   ------               display empty rooms   -------                find rooms
    // retrieve data                    alphabetical order                  store data

    Menu menu = null; //Creating an object of the Menu class
    Queue queue;
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

    @Test
    void test1(){
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
        out = menu.deleteCustomer("5");
        Assert.assertEquals(out, 4);

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
    }
}
