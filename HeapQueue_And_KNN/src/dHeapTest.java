import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class dHeapTest {
    private dHeap<Integer> maxHeap;
    private dHeap<Integer> minHeap;
    private dHeap<Integer> minHeap2;


    @BeforeEach
    public void setUp() {
        maxHeap = new dHeap<>(3, 10, true);
        minHeap = new dHeap<>(3, 10, false);
        minHeap2 = new dHeap<>(4, 4, false);

    }

    @Test
    public void nullException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            maxHeap.add(null);
        });
    }

    @Test
    public void noSuch() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            maxHeap.remove();
        });
    }

    @Test
    public void noSuch2() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            maxHeap.element();
        });
    }

    @Test
    public void testAdd() {
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        assertEquals(3, maxHeap.size());
        assertEquals(15, maxHeap.element());
        maxHeap.add(20);
        assertEquals(4, maxHeap.size());
        assertEquals(20, maxHeap.element());
        maxHeap.add(1);
        assertEquals(5, maxHeap.size());
        assertEquals(20, maxHeap.element());
    }


    @Test
    public void testAdd2() {
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);

        assertEquals(3, minHeap.size());
        assertEquals(5, minHeap.element());

        minHeap.add(2);
        assertEquals(4, minHeap.size());
        assertEquals(2, minHeap.element());

        minHeap.add(1);
        assertEquals(5, minHeap.size());
        assertEquals(1, minHeap.element());
    }

    @Test
    public void testRemove() {
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        maxHeap.add(20);

        assertEquals(20, maxHeap.remove());
        assertEquals(15, maxHeap.element());
        assertEquals(15, maxHeap.remove());
        assertEquals(10, maxHeap.element());
        assertEquals(10, maxHeap.remove());
        assertEquals(5, maxHeap.element());
    }

    @Test
    public void testRemove2() {
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        minHeap.add(2);

        assertEquals(2, minHeap.remove());
        assertEquals(5, minHeap.element());
        assertEquals(5, minHeap.remove());
        assertEquals(10, minHeap.element());
        assertEquals(10, minHeap.remove());
        assertEquals(15, minHeap.element());
    }

    @Test
    public void testClear() {
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        assertEquals(3, maxHeap.size());
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        assertEquals(3, minHeap.size());
        minHeap.clear();
        assertEquals(0, minHeap.size());
    }

    @Test
    public void testElement() {
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);
        assertEquals(20, maxHeap.element());
        maxHeap.remove();
        assertEquals(10, maxHeap.element());
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(5);
        assertEquals(5, minHeap.element());
        minHeap.remove();
        assertEquals(10, minHeap.element());
    }

    @Test
    public void testSize() {
        assertEquals(0, maxHeap.size());
        maxHeap.add(10);
        assertEquals(1, maxHeap.size());
        maxHeap.add(20);
        assertEquals(2, maxHeap.size());
        maxHeap.remove();
        assertEquals(1, maxHeap.size());

        assertEquals(0, minHeap.size());
        minHeap.add(10);
        assertEquals(1, minHeap.size());
        minHeap.add(20);
        assertEquals(2, minHeap.size());
        minHeap.remove();
        assertEquals(1, minHeap.size());
    }

    @Test
    public void testBubble() {
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        assertEquals(20, maxHeap.element());

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(2);

        assertEquals(2, minHeap.element());
    }

    @Test
    public void testTrickle() {
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        maxHeap.add(15);
        maxHeap.remove();
        assertEquals(15, maxHeap.element());

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(1);
        minHeap.remove();

        assertEquals(2, minHeap.element());
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 10; i++) {
            maxHeap.add(i);
        }
        assertEquals(10, maxHeap.size());
        maxHeap.add(10);
        assertEquals(11, maxHeap.size());
        for (int i = 0; i < 10; i++) {
            minHeap.add(i);
        }
        assertEquals(10, minHeap.size());
        minHeap.add(10);
        assertEquals(11, minHeap.size());
    }
    @Test
    void testResize2() {
        for (int i = 1; i < 25; i++) {
            minHeap2.add(i);
        }
        assertEquals(24, minHeap2.size());
        assertEquals(1, minHeap2.element());
        minHeap2.remove();
        assertEquals(2, minHeap2.element());
    }
    @Test
    void testResize3() {
        for (int i = 25; i > 0; i--) {
            minHeap2.add(i);
        }
        assertEquals(25, minHeap2.size());
        assertEquals(1, minHeap2.element());
        minHeap2.remove();
        assertEquals(2, minHeap2.element());
    }

}