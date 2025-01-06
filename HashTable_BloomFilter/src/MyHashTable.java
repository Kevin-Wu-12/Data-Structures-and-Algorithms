/*
 * Name: TODO
 * PID: TODO
 */

import java.util.LinkedList;

/**
 * Creates a HashTable.
 *
 * @author Kevin Wu
 */

public class MyHashTable implements KeyedSet {

    /* instance variables */
    private int size; // number of elements stored
    private int rehashCount;
    private float loadFactor;
    private int collisonCount;
    private LinkedList<String> statsLog;
    private LinkedList<String>[] table; // data table
    private static final int DEFAULT_CAPACITY = 19;
    private static final int THRESHHOLD = 7;


    /**
     * HashTable constructor.
     *
     */
    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * HashTable Constructor
     *
     * @param capacity Capacity of the Hash Table
     * @throws IllegalArgumentException When the capacity is less than 7
     *
     */
    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        if (capacity < THRESHHOLD) throw new IllegalArgumentException();

        table = new LinkedList[capacity];
        statsLog = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Insert string into HashTable
     * @param value String to be inserted.
     * @throws NullPointerException When string given is null.
     * @return True or false if string was inserted.
     */

    public boolean insert(String value) {
        if (value == null) throw new NullPointerException();

        if ((float) size / capacity() > 1.0) {
            loadFactor = (float) size / capacity();
            rehashCount++;
            statsLog.add("Before rehash # " + rehashCount +
                            ": load factor " + String.format("%.2f", loadFactor) +
                            ", " + collisonCount + " collision(s).\n"
            );

            collisonCount = 0;
            rehash();
        }

        int index = hashString(value);
        for (String entry : table[index]) {
            if (entry.equals(value)) {
                return false;
            }
        }
        if (table[index].isEmpty()) {
            table[index].add(value);
            size++;
        } else {
            collisonCount++;
            size++;
            table[index].add(value);
        }
        return true;

    }

    /**
     * Delete string from HashTable
     * @param value String to be inserted.
     * @throws NullPointerException When string given is null.
     * @return True or false if string could be deleted.
     */

    public boolean delete(String value) {
        if (value == null) throw new NullPointerException();

        int index = hashString(value);
        LinkedList<String> bucket = table[index];
        for (String entry : bucket) {
            if (entry.equals(value)) {
                bucket.remove(value);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true or false if a String is in the table.
     * @param value String to be searched.
     * @return True or false if a string is in the table.
     */
    public boolean lookup(String value) {
        int index = hashString(value);
        LinkedList<String> bucket = table[index];

        for (String entry : bucket) {
            if (entry.equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all the values in the table in order.
     * @return Array of all values in the table.
     *
     */
    public String[] returnAll() {
        LinkedList<String> array = new LinkedList<>();
        for (LinkedList<String> val : table) {
            array.addAll(val);
        }
        return array.toArray(new String[0]);
    }

    /**
     * Size of the table.
     * @return Size of the table.
     *
     */
    public int size() {
        return size;
    }

    /**
     * Max Capacity of table.
     * @return Capacity of table.
     *
     */
    public int capacity() {
        return table.length;
    }

    /**
     * Statistics of the hashtable after each rehash.
     * @return String of the statistics.
     *
     */
    public String getStatsLog() {
        System.out.println(String.join("", statsLog));
        return String.join("", statsLog);
    }

    /**
     * Utility function provided to help with debugging
     */
    public void printTable() {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s = s + i + ":";
            if (!table[i].isEmpty()) {
                for (String t : table[i])
                    s = s + " " + t + ",";
                // remove trailing comma
                s = s.substring(0, s.length() - 1);
            }
            s = s + "\n";
        }
        // remove trailing newline
        s = s.substring(0, s.length() - 1);
        System.out.println(s);
    }

    /**
     * Rehashes the table.
     *
     *
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<String>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;

        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        for (LinkedList<String> bucket : oldTable) {
            for (String val : bucket) {
                insert(val);
            }
        }
    }
    /**
     * Calculates the hash value of the string.
     *
     * @param value String to be converted.
     * @return Hash value of the string.
     *
     */
    private int hashString(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int left = hashValue << 5;
            int right = hashValue >>> 27;

            hashValue = (left | right) ^ value.charAt(i);
        }
        return Math.abs(hashValue % capacity());
    }
}
