/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Kevn Wu
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private static final int DEFAULT_SIZE = 10;
    private static final int BINARY_HEAP = 10;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(BINARY_HEAP, DEFAULT_SIZE, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(BINARY_HEAP, heapSize, true);
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
    }

    /**
     * Returns the size of the heap
     *
     * @return Size of the heap.
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Removes the root, replaces it with the last element and then trickles down if needed.
     *
     * @throws NoSuchElementException If the size of the heap is 0.
     * @return The element removed from the heap
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T root = heap[0];
        heap[0] = heap[--nelems];
        trickleDown(0);
        return root;

    }

    /**
     * Adds an element to the end of the heap and then bubbles up if needed.
     *
     * @throws NullPointerException If the item to add is null
     * @param item to be added to the heap.
     *
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (nelems == heap.length) {
            resize();
        }
        heap[nelems] = item;
        nelems++;
        bubbleUp(nelems - 1);

    }
    /**
     * Clears the heap.
     *
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[heap.length];
        this.nelems = 0;
    }

    /**
     * Returns the root of the heap.
     *
     * @throws NoSuchElementException Thrown when there are 0 elements in the heap.
     * @return The root element of the heap
     */
    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }


    /**
     * Returns the parent index of the elements index.
     *
     * @param index Index of the element
     * @return The parent index op the element index.
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

    /**
     * Bubbles up the given index
     *
     * @param index Index of the element
     *
     */
    private void bubbleUp(int index) {
        int parent = parent(index);
        if (index == 0) {
            return;
        }
        if (compare(heap[index], heap[parent]) > 0) {
            swap(index, parent);
            bubbleUp(parent);
        }
    }

    /**
     * Trickles down the given index
     *
     * @param index Index of the element
     *
     */
    private void trickleDown(int index) {
        int largestChild = maxChild(index);

        if (largestChild != index && compare(heap[largestChild], heap[index]) > 0) {
            swap(index, largestChild);
            trickleDown(largestChild);
        }
    }


    /**
     * Finds the max child of the given index.
     *
     * @return The maxchild.
     * @param index Index of the element
     *
     */
    private int maxChild(int index) {
        int firstChild = d * index + 1;
        int lastChild = Math.min(nelems, firstChild + d);
        int maxChild = index;

        for (int i = firstChild; i < lastChild; i++) {
            if (compare(heap[i], heap[maxChild]) > 0) {
                maxChild = i;
            }
        }
        return maxChild;
    }

    /**
     * Swaps two elements in the heap
     *
     * @param i Index of the element
     * @param j Index of the element
     *
     */
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    /**
     * Comapres two elements depending on max or min heap
     *
     * @param a Heap element.
     * @param b Index of the element
     * @return Int value which tells which element is larger.
     *
     */
    private int compare(T a, T b) {
        if (isMaxHeap) {
            return a.compareTo(b);
        } else {
            return b.compareTo(a);
        }
    }

    /**
     * Doubles the size of the heap when it is full
     *
     *
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }
    
}
