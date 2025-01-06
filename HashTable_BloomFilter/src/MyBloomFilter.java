/*
 * Name: TODO
 * PID: TODO
 */

/**
 *
 * @author Kevin Wu
 */

public class MyBloomFilter implements KeyedSet {

    private static final int DEFAULT_M = (int) 1e7;

    boolean[] bits;

    /**
     * Initialize MyBloomFilter with the default number of bits
     */
    public MyBloomFilter() {
        bits = new boolean[DEFAULT_M];
    }

    /**
     * Insert the string key into the bloom filter.
     *
     * @param key key to insert
     * @throws NullPointerException if value is null
     * @return true if the key was inserted, false if the key was already
     *         present
     */
    public boolean insert(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int indexA = hashFuncA(key);
        int indexB = hashFuncB(key);
        int indexC = hashFuncC(key);
        bits[indexA] = true;
        bits[indexB] = true;
        bits[indexC] = true;
        return true;
    }

    /**
     * Check if the given key is present in the bloom filter.
     * @param key key to look up
     * @throws NullPointerException if value is null
     * @return true if the key was found, false if the key was not found
     */
    public boolean lookup(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int indexA = hashFuncA(key);
        int indexB = hashFuncB(key);
        int indexC = hashFuncC(key);
        if (bits[indexC] && bits[indexB] && bits[indexA]) {
            return true;
        }
        return false;
    }

    /**
     * First hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncA(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int left = hashValue << 5;
            int right = hashValue >>> 27;

            hashValue = (left | right) ^ value.charAt(i);
        }
        return Math.abs(hashValue % bits.length);
    }


    /**
     * Second hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncB(String value) {
        int hashValue = 0;
        int n = 27;
        for (int i = 0; i < value.length(); i++) {
            hashValue = (hashValue * n + value.charAt(i))  % bits.length;
        }
        return Math.abs(hashValue);
    }

    /**
     * Third hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncC(String value) {
        int hashVal = 0;
        for (int j = 0; j < value.length(); j++) {
            int letter = value.charAt(j);
            hashVal = ((hashVal << 8) + letter) % bits.length;
        }
        return Math.abs(hashVal);
    }
}
