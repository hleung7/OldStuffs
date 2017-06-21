import java.util.Collection;

/**
 * This interface describes the public methods needed for
 * LinkedList.
 *
 * We've given you the expected Big-O for each method this time around.
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
    public void add(T data);


    /**
     * Adds the element to the index specified.  If I were to add to index i
     * and get the data at index i, I should get the newly added data.
     *
     * Should be O(1) if index is 0 or {@code size}.
     * Should be O(n) otherwise.
     *
     * @param index The index where you want the new element.
     * @param data Any object of type T.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToIndex(int index, T data);


    /**
     * Creates a new node with {@code data} and adds it to the front of the
     * linked list.  This new node should be the new head of the list.
     *
     * Should be O(1).
     *
     * @param data The data that the new node should hold.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToFront(T data);


    /**
     * Appends all the data from the collection in order of appearence in the
     * collection.
     *
     * @param collection The collection with all the data being added to the
     * Linked list.
     * @throws java.lang.IllegalArgumentException if collection is null.
     */
    public void addAll(Collection<T> collection);


    /**
     * Returns whether the Linked List contains {@code data}.
     *
     * Should be O(1) if data is in head or tail.
     * Should be O(n) otherwise.
     *
     * @param data The data to check in the Linked List
     * @return True if data is found. False otherwise.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public boolean contains(T data);


    /**
     * Returns the element at the given index.
     *
     * Should be O(1) if index is 0 or {@code size} - 1.
     * Should be O(n) otherwise.
     *
     * @param index The index of the element
     * @return The object stored at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    public T get(int index);


    /**
     * Removes the last node from the Linked List and returns the data from it.
     * If the list is empty, return {@code null}.
     *
     * Must be O(n).
     *
     * @return The data from the last node or null.
     */
    public T remove();


    /**
     * Removes and returns the element at index.
     *
     * Should be O(1) if index is 0.
     * Should be O(n) otherwise.
     *
     * @param index The index of the element.
     * @return The data that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    public T removeAtIndex(int index);


    /**
     * Remove the front node from the list and return the data from it. If the
     * list is empty, return {@code null}.
     *
     * Must be O(1).
     *
     * @return The data from the front node or null.
     */
    public T removeFromFront();


    /**
     * Sets the data of the node at index with {@code data}.
     * Returns the replaced data.  This is not the same as {@code addAtIndex}
     *
     * Should be O(1) if index is in 0 or {@code size - 1}
     * Should be O(n) otherwise.
     *
     * @param index The index to replace to data.
     * @param data The new data to reaplce with.
     * @return The replaced data.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public T setAtIndex(int index, T data);


    /**
     * Returns the Linked List represented as an array of objects.
     * An empty list should return an empty array.
     *
     * Must be O(n).
     *
     * @return An array of length {@code size} holding each element in the same
     * order as it is in the linked list.
     */
    public Object[] toArray();


    /**
     * Returns a boolean value representing whether the Linked List is empty.
     *
     * Should be O(1).
     *
     * @return True if empty. False otherwise.
     */
    public boolean isEmpty();


    /**
     * Returns the size of the Linked List as an integer.
     *
     * Should be O(1).
     *
     * @return The size of the list.
     */
    public int size();


    /**
     * Clears the Linked List.
     *
     * Should be O(1).
     */
    public void clear();


    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the head of the linked list.
     */
    public LinkedListNode<T> getHead();


    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return Node representing the tail of the linked list.
     */
    public LinkedListNode<T> getTail();
}
