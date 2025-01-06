/*
 * NAME: Kevin Wu
 * PID: A17886439
 */

import java.util.AbstractList;

/**
 * Creates a doubly linked list and its methods.
 *
 * @author Kevin Wu
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int size;
    private Node head;
    private Node tail;
    private T cur;
    private Node prevNode;
    private Node nextNode;
    private Node newNode;
    private Node curNode;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;


        /**
         * Constructor to create singleton Node
         *
         * @param element Element of the node.
         */
        private Node(T element) {
            data = element;
            next = null;
            prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            data = element;
            next = nextNode;
            prev = prevNode;

        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         *
         * @return returns the data of the node.
         */
        public T getElement() {
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            prev.next = next;
            next.prev = prev;
            next = null;
            prev = null;

        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null, null, head);
        head.next = tail;
        head.prev = null;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {

        if (element == null) {
            throw new NullPointerException();
        }

        newNode = new Node(element, tail, tail.prev);
        tail.prev.setNext(newNode);
        tail.setPrev(newNode);
        size++;

        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param element The element to be added.
     * @param index   The index to add the element in.
     * @throws IndexOutOfBoundsException thrown if the index given is less than 0 or bigger than
     *                                   the size.
     * @throws NullPointerException      Thrown if the element ot be added is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            prevNode = head;
            nextNode = head.next;
        } else if (index == size) {
            prevNode = tail.prev;
            nextNode = tail;
        } else {
            prevNode = getNth(index - 1);
            nextNode = getNth(index);
        }
        newNode = new Node(element, nextNode, prevNode);
        nextNode.setPrev(newNode);
        prevNode.setNext(newNode);
        size++;


    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
        ;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element Element to look for.
     * @return True or false depending on if the element is in the linked list.
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        Node current = head.next;

        while (current != tail) {
            if (current.data != null && current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index Index of the element to get.
     * @return Node of the element at that index.
     * @throws IndexOutOfBoundsException thrown if the index given is less than 0 or bigger than
     *                                   the size -1.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {

        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        curNode = getNth(index);

        return curNode.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index Index of the element.
     * @return Element that that index.
     */
    private Node getNth(int index) {
        Node current = head.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;

    }

    /**
     * Determine if the list empty
     *
     * @return True or false if the doubly linked list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index Element to be removed.
     * @return Element that was removed.
     * @throws IndexOutOfBoundsException thrown if the index given is less than 0 or when index
     *                                   is greater than the size - 1.
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node nodeRemove = getNth(index);
        T removedElement = nodeRemove.getElement();
        nodeRemove.remove();
        size--;

        return removedElement;


    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index   Index of the current element.
     * @param element Element to replace the current element
     * @return The element before being replaced.
     * @throws IndexOutOfBoundsException thrown if the index given is less than 0 or bigger than
     *                                   the size - 1.
     * @throws NullPointerException      Thrown if the element ot be added is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new NullPointerException();
        }

        curNode = getNth(index);
        T prevData = curNode.getElement();
        curNode.setElement(element);

        return prevData;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return Size of the doubly linked list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return String representation of the doubly linked list.
     */
    @Override
    public String toString() {
        String stringRep = "[(head) -> ";
        curNode = head.next;
        while (curNode != tail) {

            stringRep += curNode.getElement() + " -> ";
            curNode = curNode.getNext();
        }
        stringRep += "(tail)]";
        return stringRep;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Mimic Python list slicing, modify list in place
     *
     * @param end   Where the slicing will end not inclusive.
     * @param start When the slicing starts inclusive.
     * @param step  How many elements to skip for every element.
     * @throws IllegalArgumentException Thrown when start and end are less than 0 or if step is
     *                                  less than 1
     */
    public void slice(int start, int end, int step) {
        if (start < 0 || end < 0 || step < 1) {
            throw new IllegalArgumentException();
        }
        int currentIndex = 0;
        Node currentNode = head.next;
        Node dummy = head;

        while (currentIndex < end && currentNode != tail) {
            Node next = currentNode.next;
            if (currentIndex >= start && (currentIndex - start) % step == 0) {
                dummy.next = currentNode;
                currentNode.prev = dummy;
                dummy = currentNode;
            } else {
                size--;
            }
            currentIndex++;
            currentNode = next;
        }
        dummy.next = tail;
        tail.prev = dummy;
    }

    /**
     * Recursively swaps halves of list for n recursive layers
     *
     * @param n     How many times to swap.
     * @param start Swap value to include
     * @param end   Swap value to stop at.
     * @throws IllegalArgumentException Thrown when n is less than 0 or when the list cannot be
     *                                  split into equal halves 9 times
     */
    public void swapHalves(int n, int start, int end) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        int length = end - start;

        if ((length + 1) % 2 == 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0 || start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        Node startNode = getNth(start);
        Node midNode = getNth(mid);
        Node endNode = getNth(end);

        Node temp = startNode;
        startNode = midNode;
        midNode = temp;

        swapHalves(n - 1, start, mid);
        swapHalves(n - 1, mid, end);
    }

    public void swapHaljves(int toInsert) {
        if (head.next == null) {
            throw new NullPointerException();
        }
        curNode = head.next;
        while (curNode != null) {
            if (curNode.data > 0 && curNode.next.data > 0) {
                Node newNode = new Node(toInsert);
                curNode.next = newNode;
                newNode.next = curNode.next;

            }
        }

    }
}




