import java.util.List;

/**
 * The interface for an AVL Tree.
 * DO NOT EDIT THIS FILE!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface AVLInterface<T extends Comparable<? super T>> {
    /**
     * Add the data as a leaf in the AVL.  Should traverse the tree to find the
     * appropriate location. If the data being added already exists in the
     * tree, do nothing. The tree has to be balanced after each adding
     * operation.
     *
     * Rotations are performed as necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree.  There are 3 cases to consider:
     * 1: the node containing the data is a leaf node.
     *  In this case, simply remove it.
     * 2: the node containing the data has one child.  In this case, simply
     *  replace the node with the child node.
     * 3: the data has 2 children.  There are generally two approaches:
     *  replacing the data with either the largest element in the left subtree
     *  (commonly called the predecessor), or replacing it with the smallest
     *  element in the right subtree (commonly called the successor). For this
     *  assignment, use the successor.
     *
     * This method must run in O(2 log n) or better.
     *
     * Rotations are performed as necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param data data to remove from the tree
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    T remove(T data);

    /**
     * Adds values of each vertex (node) which lie in between the node
     * containing data equals to {@code start}, and the node containing 
     * data equals to {@code end}, inclusive and in sequence (nodes closest to
     * start have to be added first). {@code start} and {@code end} have
     * to added to the list as well.
     *
     * There are 3 cases to consider:
     * 1. Start and End are of the same node. In this case, if the value exists
     *    in the tree, return a list of one element, ie. the value itself
     * 2. Either node is an ancestor of the other node
     * 3. Both nodes share at least one common ancestor 
     *
     *          50
     *        /    \
     *      25      75
     *     /  \
     *    12   37
     *   /  \    \
     *  10  15    40
     *     /
     *    13
     *
     * For example, the path between 13 and 40 is
     *   [13, 15, 12, 25, 37, 40]
     * On the other hand, the path between 40 and 13 is
     *   [40, 37, 25, 12, 15, 13]
     * The path between 25 and 13 is
     *   [25, 12, 15, 13]
     * The path between 10 and 10 is
     *   [10]
     *
     * @throws java.lang.IllegalArgumentException if either start or end is
     * null
     * @throws java.util.NoSuchElementException if either start or end is not
     * in the AVL tree
     * @param start value of the starting node
     * @param end value of the ending node
     * @return the list of values representing the path between two nodes
     */
    List<T> findPathBetween(T start, T end);

    /**
     * Returns the data in the tree matching the parameter passed in.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.  Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    T get(T data);

    /**
     * Returns whether or not the parameter is contained within the tree.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    boolean contains(T data);

    /**
     * Get the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Clear the tree.
     */
    void clear();

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as max(left.height, right.height) + 1. A leaf node has
     * a height of 0.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();

    /**
     * Calculate the depth of the node containing the data.
     * The depth of the node is defined as (parent node's depth)+1.
     * The depth of the root node is 1.
     *
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree.
     * @param data data to calculate the depth of
     * @return the depth of the node containing the data if it is in the tree
     */
    int depth(T data);

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot();

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @param node the root of the tree
     */
    public void setRoot(AVLNode<T> node);

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @param size the size of the tree
     */
    public void setSize(int size); 
}
