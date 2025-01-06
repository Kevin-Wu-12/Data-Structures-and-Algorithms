/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Kevin Wu
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node
    private BSTNode curNode;
    private BSTNode newNode;
    private int compare;
    /* * * * * BST Node Inner Class * * * * */

    /**
     * Creation of a BST node
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = null;
            this.right = null;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = null;
            this.right = null;
            this.key = key;
            this.dataList = new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);

        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            if (dataList.contains(data)) {
                dataList.remove(data);
                return true;
            }

            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.nelems = 0;
        root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key Key to be inserted
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException When key is null
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key)) {
            return false;
        }
        if (root == null) {
            root = new BSTNode(null, null, key);
            this.nelems++;
            return true;
        } else {
            return insertHelp(root, key);
        }
    }

    /**
     * Helper to help insert a key.
     *
     * @param key  Key to be inserted.
     * @param node Node of the BST
     * @return True or false if the key was inserted
     */
    private boolean insertHelp(BSTNode node, T key) {
        compare = key.compareTo(node.getKey());

        if (compare < 0) {
            if (node.left == null) {
                newNode = new BSTNode(null, null, key);
                node.setLeft(newNode);
                this.nelems++;
                return true;
            } else {
                return insertHelp(node.getLeft(), key);
            }
        } else if (node.right == null) {
            newNode = new BSTNode(null, null, key);
            node.right = newNode;
            this.nelems++;
            return true;
        } else {
            return insertHelp(node.getRight(), key);
        }
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise.
     *
     * @param key To be searched.
     * @return True if the 'key' is found, false otherwise.
     * @throws NullPointerException If key is null.
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return findKeyHelper(root, key);
    }

    /**
     * Finds the key of a BST
     *
     * @param key  Key to be found
     * @param node Node of the BST
     * @return True or false if the key was found
     */
    private boolean findKeyHelper(BSTNode node, T key) {
        if (node == null) {
            return false;
        }
        compare = key.compareTo(node.getKey());

        if (compare < 0) {
            return findKeyHelper(node.getLeft(), key);
        } else if (compare > 0) {
            return findKeyHelper(node.getRight(), key);
        } else {
            return true;
        }
    }


    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        BSTNode nodeInsert = findNode(root, key);
        if (nodeInsert == null) {
            throw new IllegalArgumentException();
        }

        nodeInsert.addNewInfo(data);
    }

    /**
     * Finds a Node of a BST
     *
     * @param key  Key to be added.
     * @param node Node of the BST.
     * @return True or false if the node was found.
     */
    private BSTNode findNode(BSTNode node, T key) {
        if (node == null) {
            return null;
        }
        compare = key.compareTo(node.getKey());

        if (compare < 0) {
            return findNode(node.getLeft(), key);
        } else if (compare > 0) {
            return findNode(node.getRight(), key);
        } else {
            return node;
        }
    }


    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        BSTNode keyNode = findNode(root, key);
        if (keyNode == null) {
            throw new IllegalArgumentException();
        }

        return keyNode.getDataList();

    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;  // Base case: if the node is null, return -1
        }
        int leftHeight = findHeightHelper(root.getLeft());
        int rightHeight = findHeightHelper(root.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Iterator for the BST
     *
     * @return The iterator for the BST.
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * BST Iterator * * * * */

    /**
     * Iterator implmentation for the BST.
     */
    public class BSTree_Iterator implements Iterator<T> {
        private Stack<BSTNode> stack;
        private BSTNode curNode;
        private BSTNode node;

        /**
         * Creates a stack to be used as an iterator.
         */
        public BSTree_Iterator() {
            stack = new Stack<>();
            curNode = root;
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.getLeft();
            }
        }

        /**
         * Checks if there is a next in BST
         *
         * @return True or false depending on if there is a next
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Iterates to the next element in the BST
         *
         * @return Node that is being popped.
         * @throws NoSuchElementException If BST does not have a next.
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            node = stack.pop();
            T result = node.getKey();
            if (node.getRight() != null) {
                curNode = node.getRight();
                while (curNode != null) {
                    stack.push(curNode);
                    curNode = curNode.getLeft();
                }
            }
            return result;
        }
    }

    /* * * * * Extra Credit Methods * * * * */

    /**
     * Method that checks for intersection
     *
     * @param iter1 First iterator.
     * @param iter2 Second iterator.
     * @return List of the intersection data.
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> result = new ArrayList<>();
        T val1 = null;
        T val2 = null;

        if (iter1 == null || iter2 == null) {
            return result;
        }

        if (iter1.hasNext()) {
            val1 = iter1.next();
        }
        if (iter2.hasNext()) {
            val2 = iter2.next();
        }
        while (val1 != null && val2 != null) {
            int compare = val1.compareTo(val2);
            if (compare == 0) {
                result.add(val1);
                val1 = iter1.hasNext() ? iter1.next() : null;
                val2 = iter2.hasNext() ? iter2.next() : null;
            } else if (compare < 0) {
                val1 = iter1.hasNext() ? iter1.next() : null;
            } else {
                val2 = iter2.hasNext() ? iter2.next() : null;
            }
        }
        return result;
    }

    /**
     * The max level of the BST.
     *
     * @param level Level of the BST.
     * @return Max of that level of the BST.
     */
    public T levelMax(int level) {
        if (level < 0) {
            return null;
        }
        return maxHelper(root, level);
    }

    /**
     * Helper for levelMax
     *
     * @param node  BST node to be looked at.
     * @param level Level of the BST.
     * @return Max of that level of the BST.
     */
    private T maxHelper(BSTNode node, int level) {
        if (node == null) {
            return null;
        }
        if (level == 0) {
            return node.getKey();
        }
        T leftMax = maxHelper(node.getLeft(), level - 1);
        T rightMax = maxHelper(node.getRight(), level - 1);

        if (leftMax == null && rightMax == null) {
            return null;
        } else if (leftMax == null) {
            return rightMax;
        } else if (rightMax == null) {
            return leftMax;
        } else {
            if (leftMax.compareTo(rightMax) > 0) {
                return leftMax;
            } else {
                return rightMax;
            }
        }
    }
}



