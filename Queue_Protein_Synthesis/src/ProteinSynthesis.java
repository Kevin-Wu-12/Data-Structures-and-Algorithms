/*
    Name: Kevin Wu
    PID:  A17886439
 */

/**
 * Transcribes DNA into RNA and then translate the RNA into a protein sequence.
 *
 * @author Kevin Wu
 *
 *
 */

class ProteinSynthesis {
    private static final int CODON_LENGTH = 3;
    /**
     *
     * Transcribes a given String of DNA into RNA.
     *
     * @return Strand of DNA that has been transcribed into RNA
     * @param dna String of DNA to be transcribed.
     *
     */
     
    public CharQueue transcribeDNA(String dna) {

        if (dna.length() % 3 != 0) {
            throw new IllegalArgumentException();
        }

        CharQueue dnaStrand = new CharQueue();
        for (char nucleotide : dna.toCharArray()) {
            if (nucleotide == 'T') {
                dnaStrand.enqueue('U');
            } else {
                dnaStrand.enqueue(nucleotide);
            }
        }
        return dnaStrand;
    }

    /**
     *
     * Transcribes a given String of RNA into protein.
     *
     * @return Returns the protein sequence from the RNA.
     * @param rna Circular array of the rna.
     *
     */
    public CharQueue translateRNA(CharQueue rna) {
        String codon;
        boolean start = false;
        CharQueue protein = new CharQueue();

        while (!rna.isEmpty()) {
            codon = "" + rna.dequeue() + rna.dequeue() + rna.dequeue();
            if (codon.equals("AUG")) {
                start = true;
            }
            if ((codon.equals("UAA") ||  codon.equals("UAG") || codon.equals("UGA")) && start) {
                protein.enqueue(CodonMap.getAminoAcid(codon));
                return protein;
            }
            if (start) {
                protein.enqueue(CodonMap.getAminoAcid(codon));

            }
        }
        return protein;
    }
}
