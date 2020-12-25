

import static org.testng.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.testng.annotations.Test;

public class RetreiveDataUnitTest {
	
	void storeData(ArrayList<String> data) throws IOException {
		 BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", false)); //used false to overwrite the file instead of appending
         for (int x = 0; x < 10; x++) {
             String file;
             if(data.size() > x)
            	 file = data.get(x);
             else
            	 file = "e";
             // writes Empty room if it find "e" on the array
             if (file.equals("e")) {
                 bw.write("Empty Room " + x);

                 // writes the name of the customer from the array
             } else {
                 bw.write(file);
             }
             bw.newLine(); //Line Seperator
             bw.flush(); //Flushes the stream.

         }
         bw.close();
	}
	
	@Test
	void retreive0data() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		start.setRooms(rooms);
		ArrayList<String> data = new ArrayList<String>();
		storeData(data);
		start.retrieveData();
		Room[] r = start.getRooms();
		for(int i = 1 ; i <r.length ; i ++) {
			assertEquals(r[i].getName(), "e");
		}
	
	}
	
	//store data --> retreivedata --> get rooms and check
	@Test
	void retreive1data() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		start.setRooms(rooms);
		ArrayList<String> data = new ArrayList<String>();
		data.add("Test0");
		storeData(data);
		start.retrieveData();
		Room[] r = start.getRooms();
		for(int i = 0 ; i <r.length ; i ++) {
			if(i == 0)
				assertEquals(r[i].getName(), "Test0");
			else
				assertEquals(r[i].getName(), "e");
		}
	
	}
	
	@Test
	void retreive5data() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		start.setRooms(rooms);
		ArrayList<String> data = new ArrayList<String>();
		data.add("Test0");
		data.add("Test1");
		data.add("Test2");
		data.add("Test3");
		data.add("Test4");
		storeData(data);
		start.retrieveData();
		Room[] r = start.getRooms();
		for(int i = 0 ; i <rooms.length ; i ++) {
			if(i == 0) {
				assertEquals(r[i].getName(), "Test0");
			}
			else if(i == 1)
				assertEquals(r[i].getName(), "Test1");
			else if(i == 2)
				assertEquals(r[i].getName(), "Test2");
			else if(i == 3)
				assertEquals(r[i].getName(), "Test3");
			else if(i == 4)
				assertEquals(r[i].getName(), "Test4");
			else
				assertEquals(rooms[i].getName(), "e");
		}
	
	}
	
	@Test
	void retreivealldata() throws IOException {
		Menu start = new Menu();
		Room[] rooms = new Room[10];
		for (int x = 0; x < 10; x++) {
	            rooms[x] = new Room(); //Creating 10 Room Objects
	    }
		start.setRooms(rooms);
		ArrayList<String> data = new ArrayList<String>();
		data.add("Test0");
		data.add("Test1");
		data.add("Test2");
		data.add("Test3");
		data.add("Test4");
		data.add("Test5");
		data.add("Test6");
		data.add("Test7");
		data.add("Test8");
		data.add("Test9");
		storeData(data);
		start.retrieveData();
		Room[] r = start.getRooms();
		for(int i = 0 ; i <rooms.length ; i ++) {
			if(i == 0)
				assertEquals(r[i].getName(), "Test0");
			else if(i == 1)
				assertEquals(r[i].getName(), "Test1");
			else if(i == 2)
				assertEquals(r[i].getName(), "Test2");
			else if(i == 3)
				assertEquals(r[i].getName(), "Test3");
			else if(i == 4)
				assertEquals(r[i].getName(), "Test4");
			else if(i == 5)
				assertEquals(r[i].getName(), "Test5");
			else if(i == 6)
				assertEquals(r[i].getName(), "Test6");
			else if(i == 7)
				assertEquals(r[i].getName(), "Test7");
			else if(i == 8)
				assertEquals(r[i].getName(), "Test8");
			else if(i == 9)
				assertEquals(r[i].getName(), "Test9");

			else
				assertEquals(r[i].getName(), "e");
		}
	
	}

}
