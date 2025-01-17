import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Queue_Test {
    private Queue queue;

    @BeforeClass
    void initializeQueueTest(){
        System.out.println("Note: this tests assume that \"e\" is a special string and considered as Empty position ");
        System.out.println("initializeQueueTest started ....");
         queue = new Queue();
         for(int i =0;i<7;i++) {
             if (!queue.queueItems[0].equals("e")) {
                 Assert.fail("error: queue constructor didn't initialize element #" + Integer.toString(i) + " to Empty");
             }
         }
        System.out.println("initializeQueueTest done");
     }

     @Test(priority = 1)
    void addToQueueTest(){
        System.out.println("addToQueueTest started ....");

        queue.addToQueue("test_0");
        if(!queue.queueItems[queue.front].equals("test_0")){
            Assert.fail("error: in inserting element \"test_0\" at position 0 ");
        }

        if(queue.end != 1){
            Assert.fail("error: in incrementing queue.end after inserting element \"test_0\" at position 0 ");
        }
         System.out.println("addToQueueTest done");
     }

     @Test(priority = 2)
    void fillQueueTest(){
         System.out.println("fillQueueTest started ....");

         for(int i = 1;i<7;i++){
            queue.addToQueue("test_"+Integer.toString(i));
        }

         for(int i = 0;i<7;i++){
             Assert.assertEquals(queue.queueItems[i],"test_"+Integer.toString(i));
         }

         if(queue.end != 7){
             Assert.fail("error: in queue.end when filling the queue with 7 elements queue.end = "+Integer.toString(queue.end));
         }
         System.out.println("fillQueueTest done");
     }

    @Test(priority = 3)
    void addAfterTFillTest(){
        System.out.println("addAfterTFillTest started");

        queue.addToQueue("afterFillTest");
        if(queue.end != 7){
            Assert.fail("error: in queue.end after adding \"afterFillTest\" element");
        }

        if(!queue.queueItems[queue.end-1].equals("afterFillTest")){
            Assert.fail("error: in adding \"afterFillTest\" element at the last position");
        }
        System.out.println("addAfterTFillTest done");

    }

    //till now queue.queueItems = {test_1, test_2, test_3, test_4, test_5, test_6, afterFillTest}
    @Test(priority = 4)
    void takeFromQueueTest(){
        System.out.println("takeFromQueueTest started ....");

        int temp = queue.end;
        queue.takeFromQueue();
        if(!queue.queueItems[queue.front].equals("test_2")){
            Assert.fail("error: after take \"test_1\", queue.queueItems[0]="+queue.queueItems[queue.front] +" instead of \"test_2\"");
        }

        if(queue.end == temp-1){
            Assert.fail("error: after take \"test_1\", queue.end didn't decrement by one ");
        }


        if(queue.queueItems[6] == null){
            Assert.fail("error: after take \"test_0\", queue.queueItems[6] != \"e\" ");
        }
        System.out.println("takeFromQueueTest done");
    }

    //display method which displays the first 3 customers and also removes them instantly from the queue array
    //till now queue.queueItems = {test_2, test_3, test_4, test_5, test_6, afterFillTest}
    //so after 2 calls, queue should me empty and queue.end = 0 and queue.queueItem[queue.front] = "e"
    @Test(priority = 5)
    void displayQueueTest(){
        System.out.println("displayQueueTest started ....");
        System.out.println("calling queue.displayQueue() till the queue becomes empty ");

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);

        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Change System.out to point out to our stream
        System.setOut(new PrintStream(baos));

        queue.displayQueue();
        queue.displayQueue();

        System.setOut(oldOut);
        System.setIn(sysInBackup);

        // Our baos has the content from the print statement
        String output = baos.toString();

        Assert.assertTrue(output.contains("Mr. test_2 Removed from the queue."));
        Assert.assertTrue(output.contains("Mr. test_3 Removed from the queue."));
        Assert.assertTrue(output.contains("Mr. test_4 Removed from the queue."));
        Assert.assertTrue(output.contains("Mr. test_5 Removed from the queue."));
        Assert.assertTrue(output.contains("Mr. test_6 Removed from the queue."));
        Assert.assertTrue(output.contains("Mr. afterFillTest Removed from the queue."));

        if(queue.queueItems[queue.front] == null){
            Assert.fail("error: queue.queueItems[queue.front] != \"e\" ");
        }

        if(queue.end != 0){
            Assert.fail("error: queue.end = "+Integer.toString(queue.end)+", it should = 0");
        }

        System.out.println("calling queue.displayQueue() one more time ");
        queue.displayQueue();

        if(queue.end != 0){
            Assert.fail("error: queue.end = "+Integer.toString(queue.end)+", it must = 0 !!!");
        }
        System.out.println("displayQueueTest done ");
    }
}
