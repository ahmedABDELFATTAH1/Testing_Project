import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Add_Customers_Test {
    Menu menu;

    @BeforeMethod
    void initializeHotel()
    {
        System.out.println("before_method");
        System.out.println("Testing addCustomer function...");
        menu = new Menu();
        menu.initialize(); //Call initialize method
    }

    @Test
    void addCustomerTest1() {
        // Tests adding Customers till the hotel becomes full.

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q q q q q q q q q q".getBytes());
        System.setIn(in);

        ArrayList<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("Ahmed");
        names.add("Mohamed");
        names.add("Eslam");
        names.add("Aly");
        names.add("Khaled");
        names.add("Ezzat");
        names.add("Samy");
        names.add("Shady");
        names.add("Lami");

        for (int i=0; i<10; i++){
            menu.addCustomer(i, names.get(i));
        }

        System.setIn(sysInBackup);
        for (int i=0; i<10; i++) {
            Assert.assertEquals(menu.myHotel[i].getName(), names.get(i));
        }
    }

    @Test
    void addCustomerTest2() {
        // Tests adding Customers out of bound of possible hotel indices.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);


        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {menu.addCustomer(11, "Ali");});

        System.setIn(sysInBackup);
    }

    @Test
    void addCustomerTest3() {
        // Tests adding Customers to an occupied place.
        // Here it mistakenly added Ahmed in the same room as Ali.

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q q".getBytes());
        System.setIn(in);

        menu.addCustomer(5, "Ali");

        menu.addCustomer(5, "Ahmed");

        Assert.assertEquals(menu.myHotel[5].getName(), "Ali");

        System.setIn(sysInBackup);
    }
}
