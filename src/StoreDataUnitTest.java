

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.testng.annotations.Test;

public class StoreDataUnitTest {
	
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
	
	void deleteFile() {
		Path path = FileSystems.getDefault().getPath("D:\\ahmed\\selenium\\TestNGTutorial\\data.txt");
		 try {
	            Files.delete(path);
	     } catch (NoSuchFileException x) {
	            System.err.format("%s: no such" + " file or directory%n", path);
	     } catch (IOException x) {
	            System.err.println(x);
	     }
	}
	
	void insertFile() {
		File myObj = new File("data.txt"); 
	}
	
 
//	@Test(priority = 0)
//	void storeInDeletedFile() throws IOException {
//		Menu start = new Menu();
//		start.initialize();
//		deleteFile();
//	}
	
	@Test
	void store0Customers() throws IOException {
		Menu start = new Menu();

		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		start.setRooms(rooms);
		start.storeData();
		
		readData();
		
		for(int i = 0 ; i < data.length ; i++) {
			if(data[i] == null)
				break;
			assertEquals(data[i], "e");
		}
	}
	
	@Test
	void store1Customer() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		rooms[5].setName("Test1");
		start.setRooms(rooms);
		start.storeData();
		
		readData();
		
		for(int i = 0 ; i < data.length ; i++) {
			if(i != 5)
				assertEquals(data[i], "e");
			else
				assertEquals(data[i], "Test1");
		}
	}
	
	@Test
	void store5Customers() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
			rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		rooms[1].setName("Test1");
		rooms[2].setName("Test2");
		rooms[3].setName("Test3");
		rooms[4].setName("Test4");
		rooms[5].setName("Test5");
		start.setRooms(rooms);
		start.storeData();
		readData();
		for(int i = 0 ; i < data.length ; i++) {
			if(i == 1)
				assertEquals(data[i], "Test1");
			else if(i == 2)
				assertEquals(data[i], "Test2");
			else if(i == 3)
				assertEquals(data[i], "Test3");
			else if(i == 4)
				assertEquals(data[i], "Test4");
			else if(i == 5)
				assertEquals(data[i], "Test5");
			else
				assertEquals(data[i], "e");
		}
	}
	@Test
	void storeAllCustomers() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		rooms[0].setName("Test0");
		rooms[1].setName("Test1");
		rooms[2].setName("Test2");
		rooms[3].setName("Test3");
		rooms[4].setName("Test4");
		rooms[5].setName("Test5");
		rooms[6].setName("Test6");
		rooms[7].setName("Test7");
		rooms[8].setName("Test8");
		rooms[9].setName("Test9");
		start.setRooms(rooms);
		start.storeData();
		
		readData();
		
		for(int i = 0 ; i < data.length ; i++) {
			if(i == 0)
				assertEquals(data[i], "Test0");
			else if(i == 1)
				assertEquals(data[i], "Test1");
			else if(i == 2)
				assertEquals(data[i], "Test2");
			else if(i == 3)
				assertEquals(data[i], "Test3");
			else if(i == 4)
				assertEquals(data[i], "Test4");
			else if(i == 5)
				assertEquals(data[i], "Test5");
			else if(i == 6)
				assertEquals(data[i], "Test6");
			else if(i == 7)
				assertEquals(data[i], "Test7");
			else if(i == 8)
				assertEquals(data[i], "Test8");
			else if(i == 9)
				assertEquals(data[i], "Test9");
			else
				assertEquals(data[i], "e");
		}
	}
}
