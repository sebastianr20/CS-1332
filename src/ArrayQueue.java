/**
 * Your implementation of an array-backed queue.
 *
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: Divij Sachdeva
 *
 * Resources: none
 */
public class ArrayQueue<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * The initial capacity of a queue with fixed-size backing storage.
     */
    public static final int INITIAL_CAPACITY = 11;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
    }

    /**
     * Adds the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * resize it to double the current length. If a resize is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * This method should be implemented in amortized O(1) time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data.");
        }
        int back = (front + size) % backingArray.length;
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[size * 2];
            int index = 0;
            for (int i = 0; i < size; i++) {
                index = (front + i) % backingArray.length;
                temp[i] = backingArray[index];
            }
            backingArray = temp;
            front = 0;
            back = size;
        }
        backingArray[back] = data;
        size++;
        back++;
    }

    /**
     * Removes the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * This method should be implemented in O(1) time.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @return the data from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (backingArray[front] == null) {
            throw new java.util.NoSuchElementException("The queue is empty.");
        }
        T removed = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return removed;
    }

    /**
     * Retrieves the next data to be dequeued without removing it.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the next data or null if the queue is empty
     */
    public T peek() {
        if (backingArray[front] == null) {
            return null;
        }
        return backingArray[front];
    }

    /**
     * Returns the size of the queue.
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
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}