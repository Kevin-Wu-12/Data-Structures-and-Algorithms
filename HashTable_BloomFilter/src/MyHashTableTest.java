import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableTest {
    private MyHashTable hashTable;
    private MyHashTable hashTable2;

    @BeforeEach
    public void setUp() {
        hashTable = new MyHashTable();
        hashTable2 = new MyHashTable(8);

    }

    @Test
    public void testRehash() {
        MyHashTable s = new MyHashTable(8);
        for (int i = 0; i < 8; i++) {
            assertTrue(s.insert("test" + i));
        }
        assertTrue(s.insert("r"));
        assertEquals(8, s.capacity());
    }
    @Test
    public void construct() {
        hashTable = new MyHashTable(100);
        assertEquals(hashTable.capacity(), 100);
        hashTable2 = new MyHashTable(20);
        assertEquals(hashTable2.capacity(), 20);
    }

    @Test
    public void testEmpty() {
        String statsLog = hashTable.getStatsLog();
        assertTrue(statsLog.contains(""));
    }
    @Test
    void testRe() {
        for (int i = 0; i < 10; i++) {
            assertTrue(hashTable2.insert("test" + i));
        }
        assertEquals(hashTable2.capacity(), 16);
        assertEquals(hashTable2.getStatsLog(), "Before rehash # 1: " +
         "load factor 1.13, 1 collision(s).\n");
    }


    @Test
    void testRe2() {
        for (int i = 0; i < 10; i++) {
            assertTrue(hashTable2.insert("test" + i));
        }
        System.out.println(hashTable2.getStatsLog());
        assertEquals(hashTable2.capacity(), 16);
        assertEquals(hashTable2.getStatsLog(), "Before rehash # 1: " +
         "load factor 1.13, 1 collision(s).\n");
        hashTable2.printTable();
        for (int i = 10; i < 20; i++) {
            assertTrue(hashTable2.insert("test" + i));
        }
        assertEquals(hashTable2.size(), 20);
        assertEquals(hashTable2.capacity(), 32);
        assertEquals(hashTable2.getStatsLog(), "Before rehash # 1: load factor 1.13, " +
         "1 collision(s).\nBefore rehash # 2: load factor 1.06, 1 collision(s).\n");
    }
    @Test
    void testRe3() {
        for (int i = 0; i < 10; i++) {
            assertTrue(hashTable2.insert("test" + i));
        }
        assertEquals(hashTable2.capacity(), 16);

    }
    @Test
    public void testRe4() {
        MyHashTable s = new MyHashTable(8);
        for (int i = 0; i < 9; i++) {
            assertTrue(s.insert("test" + i));
        }
        assertTrue(s.insert("r"));
        assertEquals(16, s.capacity());
    }
    @Test
    public void testEy() {
        String statsLog = hashTable.getStatsLog();
        assertTrue(statsLog.isEmpty());
    }


    @Test
    public void testInsertCollisions() {
        assertTrue(hashTable.insert("apple"));
        assertTrue(hashTable.insert("banana"));
        assertTrue(hashTable.insert("orange"));
        assertTrue(hashTable.insert("grape"));
    }
    @Test
    public void testConstructor() {
        assertEquals(0, hashTable.size());
        assertEquals(19, hashTable.capacity());
    }

    @Test
    public void testConstructor2() {
        MyHashTable hashTableWithCapacity = new MyHashTable(23);
        assertEquals(0, hashTableWithCapacity.size());
        assertEquals(23, hashTableWithCapacity.capacity());
    }


    @Test
    public void testInsert() {
        assertTrue(hashTable.insert("apple"));
        assertTrue(hashTable.insert("banana"));
        assertFalse(hashTable.insert("apple"));
        assertEquals(2, hashTable.size());
    }


    @Test
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MyHashTable(5);
        });
    }

    @Test
    public void testException2() {
        assertThrows(NullPointerException.class, () -> {
            hashTable.insert(null);
        });
    }
    @Test
    public void testException3() {
        assertThrows(NullPointerException.class, () -> {
            hashTable.delete(null);
        });
    }
    @Test
    public void testException4() {
        assertThrows(NullPointerException.class, () -> {
            hashTable.lookup(null);
        });
    }

    @Test
    public void testDelete() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        assertTrue(hashTable.delete("apple"));
        assertFalse(hashTable.delete("apple"));
        assertFalse(hashTable.delete("cherry"));
        assertEquals(1, hashTable.size());
    }



    @Test
    public void testLookup() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        assertTrue(hashTable.lookup("apple"));
        assertFalse(hashTable.lookup("cherry"));
    }

    @Test
    public void testReturnAll() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("cherry");
        hashTable.printTable();
        String[] allElements = hashTable.returnAll();
        assertEquals(3, allElements.length);
        assertEquals("apple", allElements[0]);
        assertEquals("cherry", allElements[1]);

    }
    @Test
    public void testReturnAll2() {
        for (int i = 0; i < 10; i++) {
            assertTrue(hashTable2.insert("test" + i));
        }
        String[] allElements = hashTable2.returnAll();
        assertEquals(allElements.length, 10);

        assertEquals("test0", allElements[0]);
        assertEquals("test9", allElements[allElements.length - 1]);

    }
    @Test
    public void testReturnAll3() {
        String[] allElements = hashTable2.returnAll();
        assertEquals(allElements.length, 0);

    }

    @Test
    public void bn() {
        assertEquals(0, hashTable.size());
        hashTable.insert("apple");
        assertEquals(1, hashTable.size());
    }

    @Test
    public void nn() {
        assertEquals(19, hashTable.capacity());
        MyHashTable newHashTable = new MyHashTable(29);
        assertEquals(29, newHashTable.capacity());
    }
    @Test
    public void fghb() {
        for (int i = 0; i < 200; i++) {
            assertTrue(hashTable.insert("test" + i));
        }
        assertTrue(hashTable.capacity() > 19);
        assertTrue(hashTable.size() == 200);
    }
    @Test
    public void fgh() {
        assertTrue(hashTable.capacity() == 19);
        for (int i = 0; i < 50; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.capacity() > 19);
        assertTrue(hashTable.delete("test25"));
        assertFalse(hashTable.lookup("test25"));
    }
    @Test
    public void lk() {
        hashTable.insert("a");
        hashTable.delete("a");
        assertTrue(hashTable.insert("a"));
    }

    @Test
    public void k() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(hashTable.insert("test" + i));
        }
        assertEquals(1000, hashTable.size());
    }

}