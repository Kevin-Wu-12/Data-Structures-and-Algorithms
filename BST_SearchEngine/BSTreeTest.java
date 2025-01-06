import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {
    private BSTree t;
    private BSTree tt;
    private BSTree ttt;
    private LinkedList l;
    private Iterator<Integer> i;
    private Iterator<Integer> ii;

    @BeforeEach
    public void setup() {
        t = new BSTree();
        l = new LinkedList<>();
        tt = new BSTree();
        ttt = new BSTree();
        ttt.insert(3);
        tt.insert(15);
        tt.insert(10);
        tt.insert(20);
        tt.insert(25);
        i = tt.iterator();
        ii = ttt.iterator();

    }
    @Test
    public void sdfgssdf() {
        assertTrue(ii.hasNext());
        assertEquals(ii.next(),3);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            ii.next();
        });
    }

    @Test
    public void sdfgs() {
        Iterator<Integer> iter = t.iterator();
        assertFalse(iter.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    void yu() {
        assertFalse(t.findKey(10));
    }
    @Test
    void dfg() {
        assertEquals(t.findHeight(),-1);
    }
    @Test
    public void sdffs() {
        assertTrue(i.hasNext());
    }

    @Test
    public void sdffssdf() {
        assertTrue(i.hasNext());
        assertEquals(i.next(),10);
        assertEquals(i.next(),15);
        assertEquals(i.next(),20);
        assertTrue(i.hasNext());
        assertEquals(i.next(),25);
        assertFalse(i.hasNext());
    }


    @Test
    public void sdfs() {
        assertTrue(ii.hasNext());
        assertEquals(ii.next(), 3);
        assertFalse(ii.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            ii.next();
        });
    }


    @Test
    void singd() {
        t.insert(10);
        Iterator<Integer> it = t.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertFalse(it.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            it.next();
        });
    }
    @Test
    void ghd() {
        t.insert(10);
        t.insertData(10, 1);
        t.insertData(10, 2);
        t.insertData(10, 3);
        LinkedList<Integer> dataList = t.findDataList(10);
        assertEquals(3, dataList.size());
        assertTrue(dataList.contains(1));
        assertTrue(dataList.contains(2));
        assertTrue(dataList.contains(3));
    }

    @Test
    void dcdee() {
        t.insert(10);
        t.insert(20);
        t.insert(30);
        Iterator<Integer> it = t.iterator();
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
    }
    @Test
    void tefe() {
        t.insert(30);
        t.insert(20);
        t.insert(10);
        Iterator<Integer> it = t.iterator();
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
    }
    @Test
    void rgrgtefe() {
        t.insert(30);
        t.insert(20);
        t.insert(10);
        t.insert(100);
        Iterator<Integer> it = t.iterator();
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertEquals(100, it.next());
    }

    @Test
    void wefd() {
        t.insert(20);
        t.insert(10);
        t.insert(30);
        Iterator<Integer> it = t.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
    }
    @Test
    void asdfds() {
        t.insert(30);
        t.insert(20);
        t.insert(40);
        assertTrue(t.findKey(30));
        assertTrue(t.findKey(20));
        assertTrue(t.findKey(40));
        assertFalse(t.findKey(10));
    }

    @Test
    void xdfy() {
        assertEquals(tt.findDataList(15).size(),0);
    }
    @Test
    void xsdfdfy() {
        tt.insertData(25,2);
        tt.insertData(25,2);
        tt.insertData(25,2);
        assertEquals(tt.findDataList(15).size(),0);
        assertEquals(tt.findDataList(25).size(),3);
    }

    @Test
    public void a() {
        assertEquals(t.getRoot(), null);
        assertEquals(t.getSize(), 0);
        t.insert(1);
        assertEquals(t.getSize(), 1);
        assertEquals(t.getRoot().getKey(), 1);
    }
    @Test
    void b() {
        assertNull(t.getRoot());
        assertEquals(0, t.getSize());
        assertEquals(-1, t.findHeight());
    }
    @Test
    void d() {
        assertTrue(t.insert(10));
        assertEquals(1, t.getSize());
        assertNotNull(t.getRoot());
        assertEquals(10, t.getRoot().getKey());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            t.insertData(13,1);
        });
    }
    @Test
    void fd() {
        assertTrue(t.insert(10));
        assertEquals(1, t.getSize());
        assertEquals(10, t.getRoot().getKey());
        t.insertData(10,1);
        l = t.findDataList(10);
        assertEquals(l.size(), 1);
    }

    @Test
    void f() {
        t.insert(10);
        assertFalse(t.insert(10));
        assertEquals(1, t.getSize());
    }



    @Test
    void ff() {
        assertThrows(NullPointerException.class, () -> t.insert(null));
    }
    @Test
    void gf() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            t.insert(null);
        });
    }
    @Test
    void gff() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            t.findKey(null);
        });

    }
    @Test
    void gvff() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            t.insertData(1, null);
        });

    }
    @Test
    void gggvff() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            t.insertData(null, 1);
        });

    }
    @Test
    void gggvsdfff() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            t.insertData(2, 1);
        });

    }

    @Test
    void gvf() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            t.insertData(1,1);
        });

    }
    @Test
    void ad() {
        t.insert(15);
        t.insert(10);
        t.insert(20);
        assertTrue(t.findKey(10));
        assertFalse(t.findKey(99));
    }
    @Test
    void v() {
        t.insert(15);
        t.insert(10);
        t.insert(5);
        assertEquals(2, t.findHeight());
        t.insert(20);
        assertEquals(2, t.findHeight());
    }
    @Test
    void xy() {
        assertEquals(4, tt.getSize());
        assertEquals(2, tt.findHeight());
    }

    @Test
    void adf() {
        t.insert(8);
        t.insert(3);
        t.insert(10);
        t.insert(1);
        t.insert(6);
        t.insert(14);
        t.insert(4);
        t.insert(7);
        t.insert(13);
        ii = t.iterator();
        assertEquals(ii.next(), 1);
        assertEquals(ii.next(), 3);
        assertEquals(ii.next(), 4);
        assertEquals(ii.next(), 6);
        assertEquals(ii.next(), 7);
        assertEquals(ii.next(), 8);
        assertEquals(ii.next(), 10);
        assertEquals(ii.next(), 13);
        assertEquals(ii.next(), 14);

    }
    @Test
    void ty() {
        assertTrue(t.insert(10));
        assertFalse(t.insert(10));
        assertEquals(1, t.getSize());
    }
    @Test
    void gt() {
        t.insert(15);
        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(12);
        t.insert(18);
        t.insert(25);
        assertEquals(2, t.findHeight());
        t.insert(1);
        assertEquals(3, t.findHeight());
    }
    @Test
    void ton() {
        t.insert(10);
        t.insert(20);
        t.insert(30);
        Iterator<Integer> it = t.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
    @Test
    void tasdfsdt() {
        t.insert(10);
        t.insertData(10, 1);
        t.insertData(10, 2);
        t.insertData(10, 3);
        LinkedList<Integer> dataList = t.findDataList(10);
        assertEquals(3, dataList.size());
        assertTrue(dataList.contains(1));
        assertTrue(dataList.contains(2));
        assertTrue(dataList.contains(3));
    }
    @Test
    void dfggg() {
        t.insert(50);
        t.insert(30);
        t.insert(70);
        t.insert(20);
        t.insert(40);
        t.insert(60);
        t.insert(80);
        assertEquals(2, t.findHeight());
        Iterator<Integer> it = t.iterator();
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertEquals(40, it.next());
        assertEquals(50, it.next());
        assertEquals(60, it.next());
        assertEquals(70, it.next());
        assertEquals(80, it.next());
    }
    @Test
    void dfgkkgg() {
        t.insert(50);
        t.insertData(50,10);
        t.insertData(50,10);
    }
    @Test
    void tn() {
        t.insert(10);
        t.insertData(10, 100);
        t.insertData(10, 200);
        t.insertData(10, 300);
        LinkedList<Integer> dataList = t.findDataList(10);
        assertEquals(3, dataList.size());
        assertTrue(dataList.contains(100));
        assertTrue(dataList.contains(200));
        assertTrue(dataList.contains(300));
    }
    @Test
    void teee() {
        assertFalse(t.findKey(10));
    }
    @Test
    void tdfhd() {
        Iterator<Integer> it = t.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
}
