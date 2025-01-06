import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProteinSynthesisTest {
    @Test
    public void testMis() {
        ProteinSynthesis synthesis = new ProteinSynthesis();
        String dna = "ATGATCTCGTAA";
        String dna2 = "ATGATTCGTAA";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            synthesis.transcribeDNA(dna2);
        });
        CharQueue rnaQueue = synthesis.transcribeDNA(dna);
        CharQueue protein = synthesis.translateRNA(rnaQueue);
        assertEquals('M', protein.dequeue());
        assertEquals('I', protein.dequeue());
        assertEquals('S', protein.dequeue());
        assertEquals('*', protein.dequeue());

    }

    @Test
    public void testC() {
        ProteinSynthesis synthesis = new ProteinSynthesis();
        String dna = "CCCCTGTCATAA";
        CharQueue rnaQueue = synthesis.transcribeDNA(dna);
        assertEquals(12, rnaQueue.size());
        CharQueue protein = synthesis.translateRNA(rnaQueue);
        assertEquals(0, protein.size());

    }

    @Test
    public void testM() {
        ProteinSynthesis synthesis = new ProteinSynthesis();
        String dna = "ATGATCTCGTAA";
        CharQueue rnaQueue = synthesis.transcribeDNA(dna);
        assertEquals('A', rnaQueue.dequeue());

    }

    @Test
    public void t() {
        ProteinSynthesis synthesis = new ProteinSynthesis();
        String dna = "ATCATGTCGTAA";
        CharQueue rnaQueue = synthesis.transcribeDNA(dna);
        assertEquals(12, rnaQueue.size());
        assertEquals('A', rnaQueue.peek());
        assertEquals('A', rnaQueue.dequeue());
        assertEquals('U', rnaQueue.dequeue());
        assertEquals('C', rnaQueue.dequeue());
        CharQueue protein = synthesis.translateRNA(rnaQueue);
        assertEquals('M', protein.dequeue());
        assertEquals(2, protein.size());
    }
    @Test
    public void t1() {
        ProteinSynthesis protein1 = new ProteinSynthesis();
        CharQueue r = protein1.transcribeDNA("GGCTAGATC");
        CharQueue testRNA1 = new CharQueue();
        testRNA1.enqueue('G');
        testRNA1.enqueue('G');
        testRNA1.enqueue('C');
        testRNA1.enqueue('U');
        testRNA1.enqueue('A');
        testRNA1.enqueue('G');
        testRNA1.enqueue('A');
        testRNA1.enqueue('U');
        testRNA1.enqueue('C');
        assertEquals(testRNA1.size(), r.size());
    }
    @Test
    public void t41() {
        ProteinSynthesis p = new ProteinSynthesis();
        CharQueue ar = p.transcribeDNA("TTTGCT");
        CharQueue r = new CharQueue();
        r.enqueue('U');
        r.enqueue('U');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('C');
        r.enqueue('U');
        assertEquals(r.size(), ar.size());

    }

    @Test
    public void t14() {
        ProteinSynthesis pro = new ProteinSynthesis();
        CharQueue ar = pro.transcribeDNA("GAATCG");
        CharQueue r = new CharQueue();
        CharQueue p = new CharQueue();
        r.enqueue('G');
        r.enqueue('A');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('C');
        r.enqueue('G');
        assertEquals(r.size(), ar.size());
        p = pro.translateRNA(r);
        assertEquals(p.size(), 0);

    }
    @Test
    public void t1345() {
        ProteinSynthesis p = new ProteinSynthesis();
        CharQueue ar = p.transcribeDNA("");
        CharQueue r = new CharQueue();
        assertEquals(r.size(), ar.size());
    }

    @Test
    public void e() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ProteinSynthesis p = new ProteinSynthesis();
            CharQueue r = p.transcribeDNA("TADD");
        });
    }


    @Test
    void t2() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        CharQueue p = new CharQueue();
        CharQueue r = new CharQueue();
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('A');
        p.enqueue('M');
        p.enqueue('M');
        p.enqueue('Q');
        p.enqueue('*');
        p = proteinSynthesis.translateRNA(r);
        assertEquals(2, p.size());
        assertEquals('M', p.peek());
        p.dequeue();
        assertEquals('*', p.peek());

    }
    @Test
    void t3() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        CharQueue r = new CharQueue();
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('A');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('A');
        CharQueue p = proteinSynthesis.translateRNA(r);
        assertEquals(4, p.size());
        assertEquals('M', p.peek());
        p.dequeue();
        assertEquals('M', p.peek());
        p.dequeue();
        assertEquals('M', p.peek());
        p.dequeue();
        assertEquals('*', p.peek());
    }

    @Test
    void translateRNATesft() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        CharQueue r = new CharQueue();
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('G');
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('G');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('G');
        r.enqueue('U');
        r.enqueue('A');
        r.enqueue('G');
        CharQueue realProtein8 = proteinSynthesis.translateRNA(r);
        assertEquals(2, realProtein8.size());
        assertEquals('M', realProtein8.peek());
        realProtein8.dequeue();
        assertEquals('*', realProtein8.peek());
        realProtein8.dequeue();
    }
    @Test
    void ad() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        CharQueue r = new CharQueue();
        r.enqueue('U');
        r.enqueue('G');
        r.enqueue('A');
        r.enqueue('A');
        r.enqueue('U');
        r.enqueue('G');
        CharQueue p = proteinSynthesis.translateRNA(r);
        assertEquals(1, p.size());
        assertEquals(p.peek(), 'M');

    }
}