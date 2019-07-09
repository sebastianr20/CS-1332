/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: none
 *
 * Resources: none
 */
public class LinkedStack<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    /**
     * Adds the given data onto the stack. The given element becomes the
     * top-most element of the stack.
     *
     * This method should be implemented in O(1) time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot push null data.");
        }
        if (head == null) {
            size = 0;
        }
        head = new LinkedNode<T>(data, head);
        size += 1;
    }

    /**
     * Removes and returns the top-most element on the stack.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (head == null) {
            throw new java.util.NoSuchElementException("The stack is empty.");
        } else if (size == 0) {
            LinkedNode<T> temp = head;
            head = null;
            size = 0;
            return temp.getData();
        } else {
            LinkedNode<T> temp = head;
            head = head.getNext();
            size -= 1;
            return temp.getData();
        }
    }

    /**
     * Retrieves the next element to be popped without removing it.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the next data or null if the stack is empty
     */
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    /**
     * Return the size of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the stack
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head node of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}