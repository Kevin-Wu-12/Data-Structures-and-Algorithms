import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyPriorityQueueTest {

    private MyPriorityQueue<Character> priorityQueue;

    @BeforeEach
    public void setUp() {
        priorityQueue = new MyPriorityQueue<>(100);
    }

    @Test
    public void testgOffer() {
        String s = "dsc30PA1PA2PA3PA4PA5PA6";
        for (char c : s.toCharArray()) {
            priorityQueue.offer(c);
            priorityQueue.offer('\n');
        }
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }


    }
}