/*
 * Name: TODO
 * PID: TODO
 */

import java.util.*;

/**
 * Cache Implementation
 *
 * @author Kevin Wu

 */

public class PandaCache {
    private HashMap map;
    private int capacity;
    private LinkedList<Integer> list;

    public PandaCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int pandaID) {
        if (!map.containsKey(pandaID)) return -1;
        list.addFirst(pandaID);
        return (int) map.get(pandaID);
    }

    public void set(int pandaID, int zoneNumber) {
        if (map.size() >= capacity) {
            int tail = list.getLast();

            while (tail == list.getFirst() && list.size() > 1) {
                list.removeLast();
                tail = list.getLast();
            }

            list.removeLast();
            map.remove(tail);
        }

        map.put(pandaID, zoneNumber);
        list.addFirst(pandaID);
    }
}
