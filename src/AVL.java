import java.util.Collection;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: Divij Scahdeva
 *
 * Resources: none
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        for (T entry: data) {
            if (entry ==  null) {
                throw new IllegalArgumentException("Data Entry is null.");
            } else {
                add(entry);
            }
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data ==  null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (root == null) {
            size = 0;
        }
        root = addH(root, data);
    }

    /**
     * Helper method for add() to recurse through tree
     *
     * @param curr node for recursive step
     * @param data data to be added
     * @return added node
     */
    private AVLNode<T> addH(AVLNode<T> curr, T data) {
        if (curr == null) {
            size += 1;
            AVLNode<T> temp = new AVLNode<T>(data);
            return rotate(temp);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addH(curr.getLeft(), data));
            curr.setHeight(getHeightNode(curr));
            curr.setBalanceFactor(getBFNode(curr));
            if (curr.getBalanceFactor() == 2) {
                if (curr.getLeft().getBalanceFactor() < 0) {
                    curr = leftRightRotation(curr);
                } else {
                    curr = rightRotation(curr);
                }
            }
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addH(curr.getRight(), data));
            curr.setHeight(getHeightNode(curr));
            curr.setBalanceFactor(getBFNode(curr));
            if (curr.getBalanceFactor() == -2) {
                if (curr.getRight().getBalanceFactor() > 0) {
                    curr = rightLeftRotation(curr);
                } else {
                    curr = leftRotation(curr);
                }
            }
        }
        curr.setHeight(getHeightNode(curr));
        curr.setBalanceFactor(getBFNode(curr));
        return curr;
    }

    /**
     * Left Rotation case implementation
     *
     * @param node1 root of problematic subtree
     * @return root of fixed subtree
     */
    private AVLNode<T> leftRotation(AVLNode<T> node1) {
        AVLNode<T> middleNode = node1.getRight();
        AVLNode<T> leftNode = middleNode.getLeft();

        middleNode.setLeft(node1);
        node1.setRight(leftNode);

        update(node1);
        update(middleNode);
        return middleNode;
    }

    /**
     * Right Rotation case implementation
     *
     * @param node1 root of problematic subtree
     * @return root of fixed subtree
     */
    private AVLNode<T> rightRotation(AVLNode<T> node1) {
        AVLNode<T> middleNode = node1.getLeft();
        AVLNode<T> rightNode = middleNode.getRight();
        middleNode.setRight(node1);
        node1.setLeft(rightNode);
        update(node1);
        update(middleNode);
        return middleNode;
    }

    /**
     * Double Left Right Rotation case implementation
     *
     * @param node root of problematic subtree
     * @return root of fixed tree
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.setLeft(leftRotation(node.getLeft()));
        update(node);
        return rightRotation(node);
    }

    /**
     * Double Right Left Rotation case implementation
     *
     * @param node root of problematic subtree
     * @return root of fixed subtree
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.setRight(rightRotation(node.getRight()));
        update(node);
        return leftRotation(node);
    }

    /**
     * Gets height for specific node
     *
     * @param node node with height in question
     * @return node's height
     */
    private int getHeightNode(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        }
        if (node.getLeft() == null) {
            return node.getRight().getHeight() + 1;
        } else if (node.getRight() == null) {
            return node.getLeft().getHeight() + 1;
        } else {
            return Math.max(node.getLeft().getHeight(),
                    node.getRight().getHeight()) + 1;
        }
    }

    /**
     * Gets balance factor for specific node
     *
     * @param node node with balance factor in question
     * @return node's balance factor
     */
    private int getBFNode(AVLNode<T> node) {
        int leftHeight;
        int rightHeight;
        if (node.getLeft() == null) {
            leftHeight = -1;
        } else {
            leftHeight = node.getLeft().getHeight();
        }
        if (node.getRight() == null) {
            rightHeight = -1;
        } else {
            rightHeight = node.getRight().getHeight();
        }
        return leftHeight - rightHeight;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor. As a reminder, rotations can occur after removing
     * the predecessor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data");
        } else if (size <= 0) {
            String message = "Cannot remove from empty tree.";
            throw new java.util.NoSuchElementException(message);
        }
        AVLNode<T> temporary = new AVLNode<>(null);
        root = removeH(root, data, temporary);
        if (temporary.getData() == null) {
            throw new java.util.NoSuchElementException("Element doesn't exist");
        } else {
            size--;
            return temporary.getData();
        }
    }

    /**
     * helper method for remove() to recurse through tree
     *
     * @param curr node for recursive step
     * @param data data to be removed
     * @param temporary place holder for edge case
     * @return removed node
     */
    private AVLNode<T> removeH(AVLNode<T> curr, T data, AVLNode<T> temporary) {
        if (curr == null) {
            return null;
        } else {
            int numNodes = getNumChildren(curr);
            if (curr.getData().compareTo(data) == 0) {
                temporary.setData(curr.getData());
                if (numNodes == 0) {
                    update(curr);
                    curr = rotate(curr);
                    return null;
                } else if (numNodes == 1) {
                    AVLNode<T> temp =
                            curr.getLeft() == null
                                    ? curr.getRight() : curr.getLeft();

                    update(curr);
                    curr = rotate(curr);
                    return temp;
                } else if (numNodes == 2) {
                    AVLNode<T> temp = new AVLNode<>(null);
                    curr.setLeft(predecessorH(curr.getLeft(), temp));
                    curr.setData(temp.getData());

                    update(curr);
                    curr = rotate(curr);
                }
            }
            if (curr.getData().compareTo(data) < 0) {
                curr.setRight(removeH(curr.getRight(), data, temporary));

                update(curr);
                curr = rotate(curr);
            } else if (curr.getData().compareTo(data) > 0) {
                curr.setLeft(removeH(curr.getLeft(), data, temporary));

                update(curr);
                curr = rotate(curr);
            }
            update(curr);
            curr = rotate(curr);
            return curr;
        }
    }

    /**
     * Method to find predecessor for edge case removal
     *
     * @param curr node at recursive step
     * @param dummy place holder for predecessor
     * @return predecessor
     */
    private AVLNode<T> predecessorH(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(predecessorH(curr.getRight(), dummy));
        }
        update(curr);
        curr = rotate(curr);
        return curr;
    }

    /**
     * Method to andle rotations for remove method
     *
     * @param node root of problematic subtree
     * @return root of fixed subtree
     */
    private AVLNode<T> rotate(AVLNode<T> node) {
        if (node.getBalanceFactor() > 1) {
            if (node.getLeft() != null
                    && node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(leftRotation(node.getLeft()));
                return rightRotation(node);
            } else {
                return rightRotation(node);
            }
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight() != null
                    && node.getRight().getBalanceFactor() > 0) {
                node.setRight(rightRotation(node.getRight()));
                return leftRotation(node);
            } else {
                return leftRotation(node);
            }
        }
        return node;
    }

    /**
     * Method to update node data (height and balance factor)
     *
     * @param node node to be fixed
     * @return updated node
     */
    private AVLNode<T> update(AVLNode<T> node) {
        if (node != null) {
            node.setHeight(getHeightNode(node));
            node.setBalanceFactor(getBFNode(node));
        }
        return node;
    }


    /**
     * Helper method to find the number of children of a node
     * @param node the node whose number of children we wish to find
     * @return the number of children
     */
    private int getNumChildren(AVLNode<T> node) {
        int numNodes = 0;
        numNodes += node.getRight() != null ? 1 : 0;
        numNodes += node.getLeft() != null ? 1 : 0;
        return numNodes;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
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
            throw new IllegalArgumentException("Data is null.");
        }
        return getH(root, data);
    }

    /**
     * Helper method for get() to recurse through tree.
     *
     * @param curr current node for recursive step
     * @param data data trying to be retrieved
     * @return data in tree that matches parameter
     */
    private T getH(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data does not exist.");
        } else if (curr.getData().compareTo(data) > 0) {
            return getH(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return getH(curr.getRight(), data);
        }
        return curr.getData();
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        return containsH(root, data);
    }

    /**
     * Helper method for contains() to recurse through tree.
     *
     * @param curr current node for recursive step
     * @param data data being searched for
     * @return whether tree contains data
     */
    private boolean containsH(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        } else if (curr.getData().compareTo(data) > 0) {
            return containsH(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return containsH(curr.getRight(), data);
        } else {
            return true;
        }
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return root == null ? -1 : root.getHeight();
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}