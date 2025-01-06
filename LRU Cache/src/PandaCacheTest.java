import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandaCacheTest {


    @Test
    public void tsedf() {
        PandaCache p = new PandaCache(3);
        p.set(123,1);
        p.set(456,2);
        p.set(789,1);
        assertEquals(p.get(123), 1);
        p.set(999,3);
        assertEquals(p.get(456),-1);
    }
}