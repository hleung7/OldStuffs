/**
 * Your implementation of a Stack backed by an array.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private T[] backingArray;
    private int size;

    /**
     * Constructs a Stack with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * You must use constructor chaining to implement this constructor.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }


    /**
     * Constructs a Stack with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ArrayStack(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
        size = 0;
    }


    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't"
                + " push null data.");
        }
        if (!(size < backingArray.length)) {
            T[] arr = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                arr[i] = backingArray[i];
            }
            backingArray = arr;
        }
        backingArray[size] = data;
        size++;
    }


    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Empty."
                + " Cannot pop from this stack.");
        }
        T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return data;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // DO NOT ALTER OR USE ANYTHING BEYOND THIS POINT!


    /**
     * Used for testing and grading purposes.
     * DO NOT USE THIS METHOD IN YOUR IMPLEMENTATION!
     *
     * @return the backing array of this stack
     */
    public Object[] getBackingArray() {
        return backingArray;
    }
}