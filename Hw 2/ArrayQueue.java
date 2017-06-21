/**
 * Your implementation of a Queue backed by an array.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private T[] backingArray;
    private int size;
    private int back;
    private int front;

    /**
     * Constructs a Queue with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * You must use constructor chaining to implement this constructor.
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }


    /**
     * Constructs a Queue with the specified initial capacity of
     * {@code initialCapacity}.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ArrayQueue(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
        size = 0;
        front = 0;
        back = -1;
    }


    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot"
                    + "enqueue null data.");
        } else if (size < backingArray.length) {
            back = ((back + 1) % backingArray.length);
            backingArray[back] = data;
        } else {
            T[] newArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = dequeue();
                size++;
            }
            front = 0;
            back = backingArray.length;
            backingArray = newArray;
            backingArray[back] = data;
        }
        size++;
    }


    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Q is empty.");
        }
        T data = backingArray[front];
        backingArray[front] = null;
        front = ((front + 1) % (backingArray.length));
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
     * @return the backing array of this queue
     */
    public Object[] getBackingArray() {
        return backingArray;
    }

}
