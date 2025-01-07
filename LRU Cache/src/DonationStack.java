

import java.util.*;

/**
 * Keeps track of donations
 *
 * @author Kevin Wu
 *
 */

public class DonationStack {
    private int top;
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    private int max;
    public DonationStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();

    }

    public void push(int val) {
        stack.push(val);
        if (maxStack.isEmpty() || val >= maxStack.peek()) {
            maxStack.push(val);
        } else {
            maxStack.push(maxStack.peek());
        }
    }

    public int peek() {
        return stack.peek();
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int max() {
        return maxStack.peek();
    }
}
