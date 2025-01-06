import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    private DoublyLinkedList<Object> a;
    private DoublyLinkedList<Object> b;
    private DoublyLinkedList<Object> c;

    @BeforeEach
    public void testFortnite() {
        a = new  DoublyLinkedList<>();
        b =  new DoublyLinkedList<>();
        b.add("hello1");
        b.add("hello2");
        b.add("hello3");
        b.add(1,"no");
        b.add(1,"yes");
        b.add(1,"amazing");
        c =  new DoublyLinkedList<>();

    }
    @Test
    public void contr() {
        assertEquals(b.size(),6);
        assertEquals(c.size(),0);

    }


    @Test
    public void get() {
        assertEquals(b.get(1),"amazing");
        assertEquals(b.get(2),"yes");
        assertEquals(b.get(3),"no");
        assertEquals(b.get(4),"hello2");
        assertEquals(b.get(5),"hello3");
        assertEquals(b.get(0),"hello1");
        assertFalse(b.isEmpty());
        assertTrue(c.isEmpty());
        assertTrue(a.isEmpty());
        b.remove(1);
        b.remove(1);
        b.remove(1);
        assertEquals(b.toString(), "[(head) -> hello1 -> " +
                "hello2 -> hello3 -> (tail)]");
    }


    @Test
    public void contains() {
        assertTrue(b.contains("no"));
        assertFalse(b.contains("no3"));
        assertFalse(c.contains(1.2));

    }
    @Test
    public void String() {
        assertEquals(b.toString(), "[(head) -> hello1 -> amazing -> yes -> no -> " +
         "hello2 -> hello3 -> (tail)]");
        assertEquals(c.toString(), "[(head) -> (tail)]");

    }
    @Test
    public void clear() {
        assertEquals(b.size(), 6);
        assertEquals(c.size(), 0);
        b.clear();
        assertEquals(b.toString(), "[(head) -> (tail)]");
        c.add("f");
        assertEquals(c.size(), 1);
        assertEquals(c.toString(), "[(head) -> f -> (tail)]");
        c.clear();
        assertEquals(c.toString(), "[(head) -> (tail)]");

    }
    @Test
    public void set() {
        assertEquals(b.toString(), "[(head) -> hello1 -> amazing -> yes -> no -> " +
                "hello2 -> hello3 -> (tail)]");
        b.set(0,1);
        b.set(0,2);
        b.set(0,4);
        assertEquals(b.toString(), "[(head) -> 4 -> amazing -> yes -> no -> " +
                "hello2 -> hello3 -> (tail)]");
        b.set(1,1);
        b.set(2,2);
        b.set(3,4);
        assertEquals(b.toString(), "[(head) -> 4 -> 1 -> 2 -> 4 -> " +
                "hello2 -> hello3 -> (tail)]");
        assertEquals(b.size(), 6);

    }



    @Test
    public void testAdd() {
        a.add(2);
        a.add(4);
        assertEquals(a.size(),2);
        a.add(1,2.5);
        assertEquals(a.get(1),2.5);
        assertFalse(a.contains(8));
        a.add(3,5);
        assertEquals(a.get(3),5);
        assertEquals(a.size(),4);
    }

    @Test
    public void testMethods() {
        a.add(3);
        assertFalse(a.isEmpty());
        a.clear();
        assertTrue(a.isEmpty());
        a.add(4);
        assertTrue(a.contains(4));
        assertEquals(a.remove(0),4) ;
        a.add(3);
        assertEquals(a.toString(), "[(head) -> 3 -> (tail)]");
        a.add(4);
        assertEquals(a.toString(), "[(head) -> 3 -> 4 -> (tail)]");
        assertEquals(a.size(),2);
    }

    @Test
    public void testSet() {
        a.add(4);
        a.set(0,69);
        assertEquals(a.get(0),69);
        a.add(4);
        a.add(4);
        a.set(2,3);
        assertEquals(a.get(2),3);
        assertEquals(a.get(1),4);
        a.set(1,7);
        assertEquals(a.get(1),7);
        assertEquals(a.remove(1),7);
        assertEquals(a.toString(), "[(head) -> 69 -> 3 -> (tail)]");
        assertEquals(a.remove(1),3);
        assertFalse(a.isEmpty());
        assertEquals(a.toString(), "[(head) -> 69 -> (tail)]");
        assertTrue(a.contains(69));
    }
    @Test
    public void Add2() {
        a.add(0, 1);
        assertEquals(a.get(0),1);
        a.add(0, 2);
        assertEquals(a.get(0),2);
        a.add(0, 2);
        assertEquals(a.get(2),1);
        a.add(5);
        assertEquals(a.get(3),5);
        a.add(2, 343);
        assertEquals(a.get(1),2);
        assertEquals(a.get(2),343);
        assertEquals(a.get(3),1);
        assertEquals(a.get(4),5);
        Assertions.assertThrows(NullPointerException.class, () -> {
            a.set(1,null);
        });

    }
    @Test
    public void testException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            a.remove(0);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            a.get(-1);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            a.get(1);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            a.add(null);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            a.add(1123123123,2);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            a.add(0,null);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            a.set(4,3);
        });
    }
    @Test
    void slice() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.slice(1, 5, 2);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(4, list.get(1));
    }

    @Test
    void slice2() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.slice(0, 3, 1);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void except() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.slice(-1, 3, 1);
        });
    }
    @Test
    void except2() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.slice(1, -3, 1);
        });
    }
    @Test
    void except3() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.slice(1, 3, -1);
        });
    }
}