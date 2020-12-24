import org.testng.Assert;
import org.testng.annotations.*;


public class View_Rooms_Test {

    Menu menu = null; //Creating an object of the Menu class

    @Test
    void testCase1(){
//        first problem here that the Menu class doesn't have a constructor so if i just create an object
//        from this class it will make no initialization for myHotel array.
//        and this function must handel array = null
        System.out.println("This is test case # 1");
        menu =new Menu();
        menu.viewRooms();
    }

    @Test
    void testCase2(){
        System.out.println("This is test case # 2");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        String[] output = menu.viewRooms();
        for (int x = 0; x < 10; x++) {
            if(!output[x].equals("empty")){
                Assert.fail("one of the elements isn't equal to empty");
            }
        }
    }

    @Test
    void testCase3(){
        System.out.println("This is test case # 3");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[0].setName("AHMED");
        String[] output = menu.viewRooms();
        for (int x = 0; x < 10; x++) {
            if(x==0 && !output[x].equals("AHMED")){
                Assert.fail("element # 0 isn't equal to AHMED");
            }
            if(x>0 && !output[x].equals("empty")){
                Assert.fail("one of the elements isn't equal to empty");
            }
        }
    }

    @Test
    void testCase4(){
        System.out.println("This is test case # 4");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[9].setName("AHMED");
        String[] output = menu.viewRooms();
        for (int x = 0; x < 10; x++) {
            if(x==9 && !output[x].equals("AHMED")){
                Assert.fail("element # 9 isn't equal to AHMED");
            }
            if(x!=9 && !output[x].equals("empty")){
                Assert.fail("one of the elements isn't equal to empty");
            }
        }
    }

    @Test
    void testCase5(){
        System.out.println("This is test case # 5");
        menu =new Menu();
        menu.myHotel = new Room[10];
        for (int x = 0; x < 10; x++) {
            menu.myHotel[x] = new Room(); //Creating 10 Room Objects
        }
        menu.myHotel[9].setName("e");
        String[] output = menu.viewRooms();
        for (int x = 0; x < 10; x++) {
            if(x==9 && !output[x].equals("e")){
                Assert.fail("element # 9 isn't equal to e");
            }
            if(x!=9 && !output[x].equals("empty")){
                Assert.fail("one of the elements isn't equal to empty");
            }
        }
    }
}
