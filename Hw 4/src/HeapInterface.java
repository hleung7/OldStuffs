/**
 * Heap Interface which your MinHeap class should implement
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface HeapInterface<T extends Comparable<? super T>> {

    int INITIAL_CAPACITY = 10;

    /**
     * Add an item to the heap
     * Double the size of the array if it is full and you are trying to add
     * another element
     *
     * Make sure that you start with your root element at index 1 NOT 0
     *
     * Should be O(log n)
     *
     * You may implement this method iteratively or recursively
     * However, if you do it recursively, you must adhere to the good, clean
     * recursion style discussed in recitation by using a private helper method
     * for the recursive calls
     *
     * @throws IllegalArgumentException if the item is null
     * @param data the item to be added to the heap
     */
    void add(T data);

    /**
     * Remove and return the highest priority item from the heap
     * (in this case a min-heap)
     *
     * Should be O(log n)
     *
     * You may implement this method iteratively or recursively
     * However, if you do it recursively, you must adhere to the good, clean
     * recursion style discussed in recitation by using a private helper method
     * for the recursive calls
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    T remove();

    /**
     * Return if the heap is empty or not.
     *
     * Should be O(1)
     *
     * @return a boolean representing if the heap is empty
     */
    boolean isEmpty();

    /**
     * Return the size of the heap.
     *
     * Should be O(1)
     *
     * @return the size of the heap
     */
    int size();

    /**
     * Clear the heap AND return array to {@code INITIAL_CAPACITY}
     *
     * Should be O(1)
     */
    void clear();
    
    /**
     * This method is only for testing/grading purposes
     * DO NOT USE OR EDIT THIS METHOD IN YOUR CODE
     *
     * @return the backing array
     */
    Comparable[] getBackingArray();
}
