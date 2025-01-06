/*
    Name: TODO
    PID:  TODO
 */

/**
 * Queue implemented using a doubly linked list.
 *
 * @author Kevin Wu
 */

public class MyQueue<T> {

    /* instance variables   , feel free to add if you need */
    private DoublyLinkedList<T> list;

    /* ===separation=== */

    /**
     * Creates a new queue using doubly linked list.
     *
     *
     */
    public MyQueue() {
        list = new DoublyLinkedList<>();
    }
    /**
     * Checks if the queue is empty.
     *
     * @return Size of the doubly linked list.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return Size of the doubly linked list.
     */
    public int size() {
        return list.size();
    }
    /**
     * Enqueues a given data point into the queue
     *
     * @param data Data to be queued.
     * @throws IllegalArgumentException Thrown when the data to be queued is null.
     *
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        list.add(data);

    }

    /**
     * Removes an element from the front of the queue.
     *
     * @return Element removed from the queue
     */
    public T dequeue() {

        if (list.isEmpty()) {
            return null;
        }

        return list.remove(0);
    }

    /**
     * Returns the front of the queue.
     *
     * @return Front of the queue.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    /**
     * Turns the queue into a readable string.
     *
     * @return String representation of the data.
     */
    @Override
    public String toString(){
    if (list.isEmpty()) {
        return "[]";
    }
    return list.toString().replace("[(head) -> ", "[")
                       .replace(" -> (tail)]", "]");
    }
}
