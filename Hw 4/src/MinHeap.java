import java.util.NoSuchElementException;

/**
 * Your implementation of a Min Heap
 *
 * @author Henry Leung
 * @version 1.1
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    // Do NOT add or modify any of these instance variables
    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * Do NOT hardcode this value. Use the CONSTANT provided in the interface
     *
     * Should be O(1)
     */
    public MinHeap() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a Heap with an initial capacity of initialCapacity
     *
     * @param initialCapacity capacity of the new array to initialize
     *                        This value should be the length of the array
     *                        regardless of our heap being 1-indexed
     *
     * Should be O(1)
     */
    public MinHeap(int initialCapacity) {
        size = 0;
        backingArray = (T[]) new Comparable[initialCapacity];

    }
    /**
     * Creates a Heap from an initial set of values
     * !!! UPDATED in version 1.1 !!!
     * For this constructor, initialize the backing array to fit the passed in
     * data exactly.
     * Example:
     *   If an 5 elements are passed in, your backing array should be of size 6
     *   since the backing array is 1-indexed.
     *
     * When this constructors returns, the backing array should satisfy all
     * the properties of a heap
     *
     * You should implement this the way it was mentioned in lecture
     * The BuildHeap algorithm visualized on the following page is how it should
     * be implemented and is the same method that was taught in class
     * https://www.cs.usfca.edu/~galles/visualization/Heap.html
     *
     * @param values values to initialize the heap with
     *               T... values is the same as T[] values
     *               You may assume that none of the arguments passed in
     *               will be null
     */
    @SafeVarargs
    public MinHeap(T... values) {
        size = values.length;
        backingArray = (T[]) new Comparable[values.length + 1];
        // add values to our heap
        for (int i = 0; i < values.length; i++) {
            backingArray[i + 1] = values[i];
        }
        for (int j = size / 2; j > 0; j--) {
            bubbleDown(j);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        if (backingArray.length == size + 1) {
            // double if full
            T[] newArr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i <= size; i++) {
                newArr[i] = backingArray[i];
            }
            backingArray = newArr;
        }
        size++;
        backingArray[size] = data;
        bubbleUp(size);
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty heap.");
        }
        T rem = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        bubbleDown(1);
        return rem;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Heapify method to move an element up a heap.
     * @param index index we perform the bubbleUp function at.
     */

    private void bubbleUp(int index) {
        T store = backingArray[index];
        if (index != 1) {
            T parent = backingArray[index / 2];
            if (parent != null) {
                if (store.compareTo(parent) < 0) {
                    backingArray[index / 2] = store;
                    backingArray[index] = parent;
                    bubbleUp(index / 2);
                }
            }
        }
    }

    /**
     * Heapify method to move an element down a heap.
     * @param index index at which to perform the bubbleDown method at.
     */
    private void bubbleDown(int index) {
        T store = backingArray[index];
        if (2 * index == size) {
            if (store.compareTo(backingArray[2 * index]) > 0) {
                backingArray[index] = backingArray[2 * index];
                backingArray[2 * index] = store;
            }
        } else if (2 * index <= size) {
            T l = backingArray[2 * index];
            T r = backingArray[2 * index + 1];


            if ((l.compareTo(r) <= 0)) {
                if (store.compareTo(l) > 0) {
                    backingArray[index] = l;
                    backingArray[index * 2] = store;
                     bubbleDown(2 * index);
                }
                // bubble down
            } else {
                if (store.compareTo(r) > 0) {
                    backingArray[index] = r;
                    backingArray[(index * 2) + 1] = store;
                    bubbleDown((index * 2) + 1);
                }
            }
        }
    }

    // Do NOT edit or use this method in your code
    @Override
    public Comparable[] getBackingArray() {
        return backingArray;
    }
}
