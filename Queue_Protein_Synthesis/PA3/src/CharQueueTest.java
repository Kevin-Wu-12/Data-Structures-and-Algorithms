import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CharQueueTest {

    @Test
    public void testChar() {
        CharQueue c1 = new CharQueue();
        CharQueue c2 = new CharQueue(10);
        assertTrue(c1.isEmpty());
        assertEquals(c1.size(), 0);
        assertTrue(c2.isEmpty());
        assertEquals(c2.size(), 0);
        c1.enqueue('f');
        assertEquals(c1.peek(), 'f');
        c1.dequeue();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            c1.dequeue();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            c1.peek();
        });

    }

    @Test
    public void testQueue() {
        CharQueue c1 = new CharQueue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CharQueue(0);
        });
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        assertFalse(c1.isEmpty());
        c1.enqueue('a');
        c1.enqueue('a');
        assertEquals(c1.size(), 7);
        c1.clear();
        assertEquals(c1.size(), 0);
        c1.enqueue('a');
        assertEquals(c1.dequeue(), 'a');
        assertEquals(c1.size(), 0);



    }

    @Test
    public void testAll() {
        CharQueue c1 = new CharQueue();
        c1.enqueue('a');
        c1.enqueue('b');
        c1.enqueue('c');
        assertEquals(c1.dequeue(), 'a');
        assertEquals(c1.size(),2);
        assertEquals(c1.peek(),'b');
        assertFalse(c1.isEmpty());
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        c1.enqueue('c');
        assertEquals(c1.size(),12 );


    }
}