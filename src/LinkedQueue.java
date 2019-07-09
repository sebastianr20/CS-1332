/**
 * Your implementation of a linked queue. It should NOT be circular.
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
public class LinkedQueue<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * Adds the given data to the queue.
     *
     * This method should be implemented in O(1) time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data.");
        }
        if (head == null) {
            size = 0;
            head = new LinkedNode<T>(data, null);
            tail = head;
        } else {
            tail.setNext(new LinkedNode<T>(data, null));
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * Removes the data from the front of the queue.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the data from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (head == null) {
            throw new java.util.NoSuchElementException("The queue is empty.");
        } else if (head.equals(tail)) {
            LinkedNode<T> temp = head;
            head = null;
            tail = null;
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
     * Retrieves the next data to be dequeued without removing it.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the next data or null if the queue is empty
     */
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    /**
     * Return the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head node of the queue.
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

    /**
     * Returns the tail node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}