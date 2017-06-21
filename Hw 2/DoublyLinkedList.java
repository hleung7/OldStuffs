
/**
 * Your implementation of a DoublyLinkedList. Note the slightly different
 * time complexities from a SinglyLinkedList in the interface.
 *
 * @author Henry
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        LinkedListNode newNode = new LinkedListNode<T>(data);
        if (!isEmpty()) {
            LinkedListNode<T> curr = tail;
            curr.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            size++;
        } else {
            addToFront(data);
        }
    }


    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        LinkedListNode<T> node = new LinkedListNode<T>(data);
        if (isEmpty()) {
            head = node;
            tail = node;
            size++;
        } else {
            head.setPrev(node);
            node.setNext(head);
            head = node;
            size++;
        }
    }


    @Override
    public T remove() {
        if (!isEmpty()) {
            if (size == 1) {
                return removeFromFront();
            } else {
                LinkedListNode<T> current = tail;
                T del = current.getData();
                current = current.getPrev();
                current.setNext(null);
                tail = current;
                size--;
                return del;
            }
        }
        return null;
    }


    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T del = head.getData();
            head = null;
            tail = null;
            size--;
            return del;
        } else {
            LinkedListNode<T> current = head;
            T del = current.getData();
            current = current.getNext();
            head = current;
            current.setPrev(null);
            size--;
            return del;
        }
    }
    @Override
    public boolean isEmpty() {
        return head == null;
    }


    @Override
    public int size() {
        return size;
    }


    // DO NOT MODIFY CODE OR USE CODE BEYOND THIS POINT.


    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }


    @Override
    public LinkedListNode<T> getTail() {
        return tail;
    }
}
