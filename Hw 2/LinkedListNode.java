/**
 * Represents a node in a doubly linked list. To be used in your
 * DoublyLinkedList implementation.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 */
public class LinkedListNode<T> {

    private T data;
    private LinkedListNode<T> prev;
    private LinkedListNode<T> next;


    /**
     * Creates a new LinkedListNode with the given data.
     *
     * @param data the data to be stored in this node.
     */
    public LinkedListNode(T data) {
        this(data, null, null);
    }

    /**
     * Creates a new LinkedListNode with the given data and a specified next
     * node. The {@code prev} reference will default to {@code null}.
     *
     * @param next the next node
     * @param data the data to be stored in this node
     */
    public LinkedListNode(T data, LinkedListNode<T> next) {
        this(data, null, next);
    }


    /**
     * Creates a new LinkedListNode with the given data and a specified prev
     * node. The {@code next} reference will default to {@code null}.
     *
     * @param prev the prev node
     * @param data the data to be stored in this node.
     */
    public LinkedListNode(LinkedListNode<T> prev, T data) {
        this(data, prev, null);
    }


    /**
     * Creates a new LinkedListNode with the given data, prev, and next
     * references.
     *
     * @param data the data to be stored in the node
     * @param prev the previous node
     * @param next the next node
     */
    public LinkedListNode(T data, LinkedListNode<T> prev,
                          LinkedListNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }


    /**
     * Sets the data stored in the node.
     *
     * @param data the new data to be set
     */
    public void setData(T data) {
        this.data = data;
    }


    /**
     * Gets the data stored in the node.
     *
     * @return the data in this node
     */
    public T getData() {
        return data;
    }


    /**
     * Gets the next node.
     *
     * @return the next node
     */
    public LinkedListNode<T> getNext() {
        return next;
    }


    /**
     * Sets the next node.
     *
     * @param next the new next node to be set
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }


    /**
     * Sets the previous node.
     *
     * @param prev the new previous node to be set
     */
    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }


    /**
     * Gets the previous node.
     *
     * @return the previous node
     */
    public LinkedListNode<T> getPrev() {
        return prev;
    }


    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}
