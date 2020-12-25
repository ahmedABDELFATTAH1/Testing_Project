import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Room_Test {
    private Room room;
    @BeforeClass
    void initializeRoomTest(){
        System.out.println("initializeRoomTest started ....");
        room = new Room();
        if(!room.getName().equals("e")){
            Assert.fail("error: room constructor didn't initialize room.mainName to  \"e\" ");
        }

        System.out.println("initializeRoomTest done");
    }
    @Test
    void setGetNameTest(){
        System.out.println("setGetNameTest started ....");
        room.setName("test");
        if(!room.getName().equals("test")){
            Assert.fail("error: room name didn't change after calling room.setName(\"test\") ");
        }

        System.out.println("setGetNameTest done");
    }
}
