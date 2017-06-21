/**
 * Your implementation of a Stack backed by a LinkedList.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class LinkedListStack<T> implements StackInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListInterface<T> backingList;

    /**
     * Constructs an empty Stack.
     */
    public LinkedListStack() {
        backingList = new DoublyLinkedList<T>();
    }


    @Override
    public void push(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't "
                + "push null data.");
        }
        backingList.addToFront(data);
    }


    @Override
    public T pop() {
        T data = backingList.removeFromFront();
        if (data == null) {
            throw new java.util.NoSuchElementException("Stack's empty.");
        }
        return data;
    }


    @Override
    public int size() {
        return backingList.size();
    }


    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }


    // DO NOT ALTER OR USE ANYTHING BEYOND THIS POINT!


    /**
     * Used for testing and grading purposes.
     * DO NOT USE THIS METHOD IN YOUR IMPLEMENTATION!
     *
     * @return the backing list of this stack
     */
    public LinkedListInterface<T> getBackingList() {
        return backingList;
    }

}