import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    private MyQueue<Integer> queue;
    private MyQueue<Integer> a;
    private MyQueue<Integer> b;
    private MyQueue<Integer> c;
    private MyQueue<Double> d;
    private MyQueue<Double> e;
    private MyQueue<Character> c1;
    private MyQueue<Character> c2;


    @BeforeEach
    public void test() {
        queue = new MyQueue<>();
        a = new MyQueue<>();
        b = new MyQueue<>();
        a.enqueue(3);
        a.enqueue(4);
        a.enqueue(5);
        c = new MyQueue<>();
        d = new MyQueue<>();
        e = new MyQueue<>();
        c1 = new MyQueue<>();
        c2 = new MyQueue<>();
    }

    @Test
    public void string() {
        assertEquals(b.toString(), "[]");
        assertEquals(a.toString(), "[3 -> 4 -> 5]");
        assertEquals(queue.toString(), "[]");
        assertTrue(b.isEmpty());
        assertFalse(a.isEmpty());
        assertEquals(a.size(), 3);
        a.dequeue();
        a.dequeue();
        a.dequeue();
        assertEquals(b.size(), 0);
        assertEquals(a.toString(), "[]");
        assertTrue(a.isEmpty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            a.enqueue(null);
        });
    }

    @Test
    public void ssize() {
        assertEquals(b.size(), 0);
        assertEquals(b.peek(), null);
        assertEquals(a.size(), 3);
        assertEquals(a.peek(), 3);
        assertEquals(a.toString(), "[3 -> 4 -> 5]");
        a.dequeue();
        assertEquals(a.toString(), "[4 -> 5]");
        assertEquals(a.peek(), 4);
    }


    @Test
    public void testEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(queue.size(), 0);
        assertTrue(queue.isEmpty());
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.dequeue();
        assertEquals(queue.size(), 2);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    public void testPeek() {
        assertNull(queue.peek());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    public void testToString() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals("[1 -> 2]", queue.toString());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals("[1 -> 2 -> 1 -> 2]", queue.toString());
    }
    @Test
    void isEmpty() {
    assertTrue(c.isEmpty());
    assertTrue(d.isEmpty());
    e.enqueue(3.14);
    assertFalse(e.isEmpty());
    }
    @Test
    void enqueue() {
        c.enqueue(1);
        c.enqueue(-3);
        assertEquals(c.size(), 2);
        assertEquals(c.peek(), 1);
        assertEquals(c.dequeue(), 1);
        assertEquals(c.dequeue(), -3);
        d.enqueue(.1);
        d.enqueue(0.99);
        d.dequeue();
    }
    @Test
    public void testChar() {
        assertTrue(c1.isEmpty());
        assertEquals(c1.size(), 0);
        assertTrue(c2.isEmpty());
        assertEquals(c2.size(), 0);
        c1.enqueue('f');
        assertEquals(c1.peek(), 'f');
        c1.dequeue();

    }

    @Test
    public void testQueue() {
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        c1.enqueue('a');
        assertFalse(c1.isEmpty());
        c1.enqueue('a');
        c1.enqueue('a');
        assertEquals(c1.size(), 7);
        c1.enqueue('a');
        assertEquals(c1.dequeue(), 'a');
        assertEquals(c1.size(), 7);



    }

    @Test
    public void testAll() {
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

