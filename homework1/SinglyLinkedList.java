/**
 * Your implementation of a non-circular singly linked list with a tail.
 *
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class SinglyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            String e = "The desired index is out of bounds within the list.";
            throw new IndexOutOfBoundsException(e);
        }
        if (data == null) {
            String e = "The data is null and cannot be put into the list.";
            throw new IllegalArgumentException(e);
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> curr = head;
            int count = 0;
            while (curr.getNext() != null) {
                if (index - 1 == count) {
                    LinkedListNode<T> newNode;
                    newNode = new LinkedListNode<T>(data, curr.getNext());
                    curr.setNext(newNode);
                }
                curr = curr.getNext();
                count += 1;
            }
            size += 1;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            String e = "The data is null and cannot be put into the list.";
            throw new IllegalArgumentException(e);
        }
        head = new LinkedListNode<T>(data, head);
        size += 1;
        if (size == 1) {
            tail = head;
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            String e = "The data is null and cannot be put into the list.";
            throw new IllegalArgumentException(e);
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (head == null) {
            head = newNode;
            tail =  newNode;
            size = 1;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size += 1;
        }
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 should be O(1), all other cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        LinkedListNode<T> temp = null;
        if (index < 0 || index >= size) {
            String e  = "The desired index is out of bounds within the list.";
            throw new IndexOutOfBoundsException(e);
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; curr.getNext() != null && i < index - 1; i++) {
                curr = curr.getNext(); 
            }
            temp = curr.getNext();
            LinkedListNode<T> next = curr.getNext().getNext();
            if (next != null) {
                curr.setNext(next);
            }
            size -= 1;
        }
        return temp.getData();
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        if (head == null) {
            return null;
        } else if (head.equals(tail)) {
            LinkedListNode<T> temp = head;
            head = null;
            tail = null;
            size = 0;
            return temp.getData();
        } else {
            LinkedListNode<T> temp = head;
            head = head.getNext();
            size -= 1;
            return temp.getData();
        }
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(n) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        if (head == null) {
            return null;
        } else if (head.equals(tail)) {
            LinkedListNode<T> temp = tail;
            head = null;
            tail = null;
            size = 0;
            return temp.getData();
        } else {
            LinkedListNode<T> temp = tail;
            LinkedListNode<T> curr = head;
            while (!curr.getNext().equals(tail)) {
                curr = curr.getNext();
            }
            tail = curr;
            curr.setNext(null);
            size--;
            return temp.getData();
        }
    }

    /**
     * Removes the last copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data the data to be removed from the list
     * @return the removed data occurrence from the list itself (not the data
     * passed in), null if no occurrence
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            String e = "The data is null and cannot be put into the list.";
            throw new IllegalArgumentException(e);
        }
        LinkedListNode<T> last = null;
        LinkedListNode<T> curr = head;
        int index = 0;
        int lastIndex = -1;
        while (curr != null) {
            if (curr.getData().equals(data)) {
                last = curr;
                lastIndex = index;
            }
            curr = curr.getNext();
            index += 1;
        }
        if (last != null) {
            removeAtIndex(lastIndex);
        }
        if (last.getData() == null) {
            return null;
        } else {
            return last.getData();
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting index 0 or size - 1 should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            String e = "The desired index is out of bounds within the list.";
            throw new IndexOutOfBoundsException(e);
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> curr = head;
            int count = 0;
            while (curr.getNext() != null) {
                if (index == count) {
                    return curr.getData();
                }
                curr = curr.getNext();
                count += 1;
            }
        }
        return null;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order
     */
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }
        Object[] arr = new Object[this.size()];
        LinkedListNode<T> curr = head;
        int index = 0;
        while (curr != null) {
            arr[index] = curr.getData();
            curr = curr.getNext();
            index += 1;
        }
        return arr;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the tail of the linked list
     */
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}

