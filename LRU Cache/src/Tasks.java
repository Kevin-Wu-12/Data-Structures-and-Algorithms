/*
 * Name: Kevin Wu
 */

import java.util.*;

/**
 * This class provides utility methods for detecting duplicates,
 * finding the middle node of a linked list, constructing strings
 * from character pools, and identifying the top k frequent customers.
 *
 * @author Kevin Wu
 */

public class Tasks {

    /**
     * TODO: Question A
     *
     * @param entries
     * @return
     */
    public static boolean raffleChecker(int[] entries) {
        HashSet<Integer> set = new HashSet<>();
        for (int entry : entries) {
            if (!set.add(entry)) {
                return true;
            }
        }
        return false;
    }


    // List Node class provided for reference
    public static class Node {
        Node next;
        String name;

        public Node(String name) {
            this.name = name;
        }
    }

    /**
     * TODO: Question B
     *
     * @param head
     * @return
     */
    public static String middleNode(Node head) {
        Node middle = head;
        Node size = head;
        while (size.next != null && middle.next != null) {
            middle = middle.next;
            size = size.next.next;
        }

        return middle.name;

    }

    /**
     *
     *
     * @param newMessage
     * @param oldMessage
     * @return True or false if the string can be made.
     */
    public static boolean canConstruct(String newMessage, String oldMessage) {
        // Convert strings to char arrays and sort them
        char[] newM = newMessage.toCharArray();
        char[] oldM = oldMessage.toCharArray();
        int newIndex = 0;
        int oldIndex = 0;

        Arrays.sort(newM);
        Arrays.sort(oldM);

        while (newIndex < newM.length && oldIndex < oldM.length) {
            if (newM[newIndex] == oldM[oldIndex]) {
                newIndex++;
            }
            oldIndex++;
        }

        return newIndex == newM.length;
    }

    /**
     * TODO:
     * Question D
     *
     * @param buyers
     * @param k
     * @return
     */
    public static String[] frequentCustomers(String[] buyers, int k) {
        int[] counts = new int[buyers.length];
        boolean[] counted = new boolean[buyers.length];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < buyers.length; i++) {
            if (!counted[i]) {
                int count = 1;
                for (int j = i + 1; j < buyers.length; j++) {
                    if (buyers[i].equals(buyers[j])) {
                        count++;
                        counted[j] = true;
                    }
                }
                counts[i] = count;
                maxHeap.add(counts[i]);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int count : counts) {
            list.add(count);
        }

        ArrayList<String> output = new ArrayList<>();
        int count = 0;
        while (maxHeap.peek() != null && count < k) {
            int a = maxHeap.poll();
            int index = list.indexOf(a);
            output.add(buyers[index]);
            list.set(index, 0);
            count++;
        }

        return output.toArray(new String[0]);
    }

}
