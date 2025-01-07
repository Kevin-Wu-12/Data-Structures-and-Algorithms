/*
    Name: Kevin Wu
    PID:  A17886439
 */

import javax.swing.plaf.IconUIResource;
import java.util.NoSuchElementException;

/**
 * Creates a circular queue.
 *
 * @author Kevin Wu
 *
 *
 */

public class CharQueue {
    private char[] circularArray;
    private int length;
    private int capacity;
    private int front;
    private int rear;
    private static final int DOUBLE = 2;
    private static final int DEFAULT_CAPACITY = 5;


    /**
     *
     * Creates is circular queue with a capacity of 5.
     *
     */
    public CharQueue() {
        front = 0;
        rear = 0;
        length = 0;
        capacity = DEFAULT_CAPACITY;
        circularArray = new char[DEFAULT_CAPACITY];
    }

    /**
     *
     * Creates is circular queue with a given capacity.
     *
     * @param capacity Capacity of the circular array.
     * @throws IllegalArgumentException Thrown when the capacity given is less than 1.
     *
     */
    public CharQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        front = 0;
        rear = 0;
        this.capacity = capacity;
        circularArray = new char[capacity];
        length = 0;
    }

    /**
     *
     * Checks if the circular array is empty.
     *
     * @return Boolean value that determines if the circular array is empty.
     *
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     *
     * Returns the amount of elements in circular array.
     *
     * @return returns the amount of elements in the array
     *
     */
    public int size() {
        return length;
    }

    /**
     *
     * Empty's the circular array.
     *
     */
    public void clear() {
        front = 0;
        rear = 0;
        length = 0;
    }

    /**
     *
     * Adds a char element to the circular queue and if it is full it doubles the size of the
     * current circular array, copy it over and adds the new element.
     *
     * @param elem Element to be queued into the circular array.
     *
     */
    public void enqueue(char elem) {
        if (size() == capacity) {
            int doubledCapacity = capacity * DOUBLE;
            char[] doubledCircularArray = new char[doubledCapacity];

            for (int i = 0; i < length; i++) {
                doubledCircularArray[i] = circularArray[(front + i) % capacity];
            }

            circularArray = doubledCircularArray;
            front = 0;
            rear = length;
            capacity = doubledCapacity;

        }
        circularArray[rear] = elem;
        rear = (rear + 1) % capacity;
        length++;
    }

    /**
     *
     * Returns the front of the circular array
     *
     * @return Returns the front of the circular array.
     * @exception NoSuchElementException Thrown when the circular queue is empty
     *
     */
    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return circularArray[front];
    }

    /**
     *
     * Returns the front of the circular array
     *
     * @return returns the element that was dequeued from the circular queue.
     * @exception NoSuchElementException Thrown when trying to dequeue from an empty queue.
     *
     */

    public char dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        char removed = circularArray[front];
        front = (front + 1) % capacity;
        length--;
        return removed;

    }
}
