/**
 * This interface describes the public methods needed for a LinkedList.
 *
 * We've given you the expected Big-O for the methods, note that some of them
 * have been updated from the SinglyLinkedList.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 */
public interface LinkedListInterface<T> {

    /**
     * Creates a new node with data {@code data} and adds the node to the
     * end of the Linked List.  The tail should be the new node.
     *
     * Should be O(1).
     *
     * @param data The data the new node should hold.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    void add(T data);


    /**
     * Creates a new node with {@code data} and adds it to the front of the
     * linked list.  This new node should be the new head of the list.
     *
     * Should be O(1).
     *
     * @param data The data that the new node should hold.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    void addToFront(T data);


    /**
     * Removes the last node from the Linked List and returns the data from it.
     * If the list is empty, return {@code null}.
     *
     * Must be O(1).
     *
     * @return The data from the last node or null if empty.
     */
    T remove();


    /**
     * Removes the front node from the list and return the data from it. If the
     * list is empty, return {@code null}.
     *
     * Must be O(1).
     *
     * @return The data from the front node or null if empty.
     */
    T removeFromFront();


    /**
     * Returns a boolean value representing whether the Linked List is empty.
     *
     * Should be O(1).
     *
     * @return True if empty. False otherwise.
     */
    boolean isEmpty();


    /**
     * Returns the size of the Linked List as an integer.
     *
     * Should be O(1).
     *
     * @return The size of the list.
     */
    int size();


    // DO NOT ALTER OR USE ANYTHING BEYOND THIS POINT

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the head of the linked list.
     */
    LinkedListNode<T> getHead();


    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the tail of the linked list.
     */
    LinkedListNode<T> getTail();
}
