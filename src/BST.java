import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of a binary search tree.
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
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection has null data.");
        }
        for (T entry: data) {
            if (entry == null) {
                throw new IllegalArgumentException("Collection has null data.");
            }
            add(entry);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to tree.");
        }
        if (root == null) {
            size = 0;
        }
        root = addH(root, data);
    }

    /**
     * Helper method to recurse and add.
     *
     * @param curr node examined during each recursive step.
     * @param data data to be added
     * @return nodes being updated with the insertion for each step.
     */
    private BSTNode<T> addH(BSTNode<T> curr, T data) {
        if (curr == null) {
            size += 1;
            return new BSTNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addH(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addH(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf (no children). In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data.
     * You MUST use recursion to find and remove the successor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data");
        } else if (size <= 0) {
            throw new NoSuchElementException("Cannot remove from empty tree.");
        }
        BSTNode<T> temporary = new BSTNode<T>(null);
        root = removeH(root, data, temporary);
        if (temporary.getData() == null) {
            throw new java.util.NoSuchElementException("Element doesn't exist");
        } else {
            size--;
            return temporary.getData();
        }
    }

    /**
     * Helper method to recurse and remove.
     *
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param curr node examined during each recursive step.
     * @param data data to be removed.
     * @param temporary holder for the data that is removed.
     * @return removed node
     */
    private BSTNode<T> removeH(BSTNode<T> curr, T data, BSTNode<T> temporary) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Element doesn't exist");
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(removeH(curr.getLeft(), data, temporary));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(removeH(curr.getRight(), data, temporary));
        } else {
            temporary.setData(curr.getData());
            if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode<T> successor = successorH(curr.getRight(), curr);
                successor.setRight(curr.getRight());
                successor.setLeft(curr.getLeft());
                return successor;
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            }
            return null;
        }
        return curr;
    }

    /**
     * Finds to successor to allow for correct removal of certain cases.
     *
     * @param curr node examine at each recursive step.
     * @param dummy holder for rest of tree for correct removal.
     * @return successor of removed node.
     */
    private BSTNode<T> successorH(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setRight(curr.getRight());
        } else {
            while (curr.getLeft() != null) {
                dummy = curr;
                curr = curr.getLeft();
            }
            if (curr.getRight() == null) {
                dummy.setLeft(null);
            } else {
                dummy.setLeft(curr.getRight());
            }
        }
        return curr;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to tree.");
        }
        return getH(data, root);
    }

    /**
     * Helper method to recurse and get.
     *
     * @throws NoSuchElementException if data not in tree.
     * @param data trying to get.
     * @param curr node examined in each recursive step.
     * @return the data if its exists
     */
    private T getH(T data, BSTNode<T> curr) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Element doesn't exist");
        } else if (curr.getData().compareTo(data) > 0) {
            return getH(data, curr.getLeft());
        } else if (curr.getData().compareTo(data) < 0) {
            return getH(data, curr.getRight());
        }
        return curr.getData();
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to tree.");
        }
        return containsH(data, root);
    }

    /**
     * Helper method to recurse and check for presence.
     *
     * @param data being checked for presence.
     * @param curr node examined at each recursive step.
     * @return whether the tree contains the data
     */
    private boolean containsH(T data, BSTNode<T> curr) {
        if (curr == null) {
            return false;
        } else if (curr.getData().compareTo(data) > 0) {
            return containsH(data, curr.getLeft());
        } else if (curr.getData().compareTo(data) < 0) {
            return containsH(data, curr.getRight());
        }
        return true;
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        preorderH(root, list);
        return list;
    }

    /**
     * Helper method to recurse and gt preorder traversal.
     *
     * @param curr node examined at each recursive step.
     * @param list traversal order.
     */
    private void preorderH(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            list.add(curr.getData());
            preorderH(curr.getLeft(), list);
            preorderH(curr.getRight(), list);
        }
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        inorderH(root, list);
        return list;
    }

    /**
     * Helper method to recurse and get inorder traversal.
     *
     * @param curr node examined at each recursive step.
     * @param list traversal order
     */
    private void inorderH(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            inorderH(curr.getLeft(), list);
            list.add(curr.getData());
            inorderH(curr.getRight(), list);
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        postorderH(root, list);
        return list;
    }

    /**
     * Helper method to recurse and gt postorder traversal.
     *
     * @param curr node examined at each recursive step.
     * @param list traversal order.
     */
    private void postorderH(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            postorderH(curr.getLeft(), list);
            postorderH(curr.getRight(), list);
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n). This does not need to be done recursively.
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>();
        Queue<BSTNode<T>> level = new LinkedList<BSTNode<T>>();
        if (root == null) {
            return list;
        }
        level.add(root);
        while (!level.isEmpty()) {
            BSTNode<T> curr = level.remove();
            list.add(curr.getData());
            if (curr.getLeft() != null) {
                level.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                level.add(curr.getRight());
            }
        }
        return list;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return heightH(root);
        }
    }

    /**
     * Helper method to get height of root.
     *
     * @param curr node examined at each recursive step.
     * @return height of root
     */
    private int heightH(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        return 1 + Math.max(heightH(curr.getLeft()), heightH(curr.getRight()));
    }

    /**
     * Returns the size of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}