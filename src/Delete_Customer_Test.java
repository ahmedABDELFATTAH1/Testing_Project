import org.testng.Assert;
import org.testng.annotations.Test;

public class Delete_Customer_Test {

    Menu menu = null; //Creating an object of the Menu class

    @Test
    void testCase1(){
        System.out.println("This is test case # 1");
        menu =new Menu();
        String input = "";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 0);
    }

    @Test
    void testCase2(){
        System.out.println("This is test case # 2");
        menu =new Menu();
        String input = "asd";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 0);
    }

    @Test
    void testCase3(){
        System.out.println("This is test case # 3");
        menu =new Menu();
        String input = "-1";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 1);
    }

    @Test
    void testCase4(){
        System.out.println("This is test case # 4");
        menu =new Menu();
        String input = "11";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 1);
    }

    @Test
    void testCase5(){
        System.out.println("This is test case # 5");
        menu =new Menu();
        String input = "1";
        int ouput = menu.deleteCustomer(input);
    }

    @Test
    void testCase6(){
        System.out.println("This is test case # 6");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        String input = "1";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 4);
    }

    @Test
    void testCase7(){
        System.out.println("This is test case # 7");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[0].setName("AHMED");
        String input = "0";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 3);
    }

    @Test
    void testCase8(){
        System.out.println("This is test case # 8");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[9].setName("AHMED");
        String input = "9";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 3);
    }

    @Test
    void testCase9(){
        System.out.println("This is test case # 9");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[5].setName("e");
        String input = "5";
        int ouput = menu.deleteCustomer(input);
        Assert.assertEquals(ouput, 3);
    }
}
