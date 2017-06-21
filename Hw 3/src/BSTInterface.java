import java.util.List;

/**
 * The interface for a Binary Search Tree
 * DO NOT EDIT THIS FILE.
 * @version 1.0
 * @author CS 1332 TAs
 */
public interface BSTInterface<T extends Comparable<? super T>> {
    /**
     * Adds a new node with the given data by traversing the tree and finding
     * the appropriate location. If the data is already present in the tree do
     * nothing.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     * 1: the node containing data is a leaf node.  In this case, simply
     * remove it.
     * 2: the node containing data has one child. In this case, simply replace
     * it with its child, then remove the child.
     * 3: the node containing data has 2 children. There are two approaches:
     *  - replacing the data with either the largest element that is smaller
     *    than the element being removed (commonly called the predecessor)
     *  - replacing it with the smallest element that is larger than the element
     *    being removed (commonly called the successor).
     * For this assignment, use the predecessor.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. DO NOT RETURN THE PARAMETER DATA.
     * RETURN THE DATA THAT WAS STORED IN THE TREE.
     */
    T remove(T data);

    /**
     * Gets the data in the tree matching the parameter passed in (think
     * whether you should use .equals or == ?).
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data removed from the tree. DO NOT RETURN THE PARAMETER DATA.
     * RETURN THE DATA THAT WAS STORED IN THE TREE.
     */
    T get(T data);

    /**
     * Checks whether or not the parameter is contained within the tree.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether the parameter is contained within the tree or not.
     */
    boolean contains(T data);

    /**
     * Finds the smallest element greater than data, or the successor,
     * from the tree. There are 2 cases to consider:
     * 1: The right sub-tree is non-empty, then successor is the leftmost node
     * the right sub-tree
     * 2: The right sub-tree is empty, then successor is the lowest ancestor of
     * the node containing data, whose left child is also an ancestor of given
     * data eg.
     *                    73
     *                   /  \
     *                  34   90
     *                 /  \
     *                32  40
     * If we need to find the nextLargest of 40, you would return 73.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data the data for which the nextLargest is to be found
     * @return the next largest data in the tree. If there is no larger data
     * than the one given, return {@code null}.
     */
    T nextLargest(T data);

    /**
     * Returns a string representation of the tree. The string should be
     * formatted as follows:
     *        {currentData, leftSubtree, rightSubtree}
     *
     * For example, for a tree that looks like the following:
     *                    8
     *                   / \
     *                 6    10
     *                / \     \
     *              4     7    15
     *
     * The string should be:
     *      {8, {6, {4, {}, {}}, {7, {}, {}}}, {10, {}, {15, {}, {}}}}
     *
     * An empty tree should return an empty set of brackets, i.e. {}.
     *
     * Running Time: O(n)
     *
     * @return String representation of the tree
     */
    String toString();

    /**
     * Finds the largest item in the tree and returns it.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     *
     * @return largest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    T getMax();

    /**
     * Finds the smallest item in the tree and returns it.
     *
     * Running Time: Worst: O(n)
     *               Average: O(log n)
     *
     * @return smallest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    T getMin();

    /**
     * Running Time: O(1)
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Generates a preorder traversal of the tree.
     *
     * Running Time: O(n)
     *
     * @return a preorder traversal of the tree
     */
    List<T> preorder();

    /**
     * Generates a postorder traversal of the tree.
     *
     * Running Time: O(n)
     *
     * @return a postorder traversal of the tree
     */
    List<T> postorder();

    /**
     * Generates an inorder traversal of the tree.
     *
     * Running Time: O(n)
     *
     * @return an inorder traversal of the tree
     */
    List<T> inorder();

    /**
     * Generates a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, dequeue one node, add its data to the list being returned, and 
     * enqueue its left and right child nodes to the queue (left first).
     * If what you just removed is {@code null}, ignore it and continue with
     * the rest of the nodes.
     *
     * Running Time: O(n)
     *
     * @return a level order traversal of the tree
     */
    List<T> levelorder();

    /**
     * Clears the tree.
     *
     * Running Time: O(1)
     */
    void clear();

    /**
     * Calculates and returns the height of the root of the tree.
     * REMEMBER: A node's height is defined as
     * {@code max(left.height, right.height) + 1}.
     *
     * A leaf node has a height of 0.
     *
     * Running Time: O(n)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    BSTNode<T> getRoot();
}