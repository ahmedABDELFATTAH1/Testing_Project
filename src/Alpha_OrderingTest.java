
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Alpha_OrderingTest {

    Menu start = null; //Creating an object of the Menu class
    @BeforeMethod
    void initializeHotel()
    {
        System.out.println("before_method");
        start =new Menu();
        start.initialize(); //Call initialize method
        //start.menu(); //Call menu method
    }

    void fillRooms(int num_rooms,ArrayList<String> room_name_list)
    {
        for(int i=0;i<room_name_list.size();i++)
        {
            start.addCustomer(i,room_name_list.get(i));
        }
    }
    int check_size(Room[] rooms)
    {
        int count =0;
        for(int i=0;i<rooms.length;i++)
        {
          if(!rooms[i].getName().equals("e"))
          {
              count++;
          }

        }
    return count;
    }
    Boolean check_order()
    {
        for(int i=1;i<start.myHotel.length;i++)
        {
               if(start.myHotel[i].getName().compareTo(start.myHotel[i-1].getName())>0)
               {
                   return false;
               }
        }
        return true;
    }

    @Test
    void test_rooms4()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Cal");
        names_list.add("Bal");
        names_list.add("ahmed");
        fillRooms(4,names_list);
        start.alphabeticalOrder();
        Boolean result = check_order();
        Assert.assertTrue(result);
    }

    @Test
    void test_sort_full_rooms()
    {
        ArrayList<String> names_list=new ArrayList<>();
        names_list.add("Dal");
        names_list.add("Cal");
        names_list.add("Bal");
        names_list.add("ahmed");
        names_list.add("Dal");
        names_list.add("Cal");
        names_list.add("Bal");
        names_list.add("ahmed");
        names_list.add("Dal");
        names_list.add("Cal");

        fillRooms(10,names_list);
        start.alphabeticalOrder();
        Boolean result = check_order();
        Assert.assertTrue(result);
    }



    @Test
    void test_sort_full_rooms_same_name()
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
        start.alphabeticalOrder();
        Boolean result = check_order();
        Assert.assertTrue(result);
    }

}
