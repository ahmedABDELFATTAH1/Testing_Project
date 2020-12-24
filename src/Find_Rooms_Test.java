import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Find_Rooms_Test {

    Menu start = null; //Creating an object of the Menu class
    @BeforeMethod
    void initializeHotel()
    {
        System.out.println("before_method");
        start =new Menu();
        start.initialize(); //Call initialize method
        //start.menu(); //Call menu method
    }


    void fillRooms(int num_rooms, ArrayList<String> room_name_list)
    {
        for(int i=0;i<room_name_list.size();i++)
        {
            start.addCustomer(i,room_name_list.get(i));
        }
    }

    @Test
    void Test_Rooms1()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        fillRooms(10,names_list);
        String room_name = "Dal";
        Boolean result = start.findRoom(room_name);
        Assert.assertTrue(result);
    }



    @Test
    void Test_Rooms2()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        fillRooms(10,names_list);
        String room_name = "Bal";
        Boolean result = start.findRoom(room_name);
        Assert.assertTrue(result);
    }


    @Test
    void Test_Rooms3()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Bal");
        fillRooms(10,names_list);
        String room_name = "Bal";
        Boolean result = start.findRoom(room_name);
        Assert.assertTrue(result);
    }


    @Test
    void Test_Rooms4()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Bal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        names_list.add("Dal");
        fillRooms(10,names_list);
        String room_name = "Bal";
        Boolean result = start.findRoom(room_name);
        Assert.assertTrue(result);
    }
}
