
import java.util.Collection;

/**
 * Your implementation of a SinglyLinkedList.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot use null data.");
        }
        LinkedListNode<T> create = new LinkedListNode<T>(data);
        if (head == null) {
            head = create;
            tail = head;
            size++;
        } else {
            tail.setNext(create);
            tail = create;
            size++;
        }
    }


    @Override
    public void addToIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("That index isnt within"
                + " our current linked list.");
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot use null data.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            LinkedListNode<T> another = new LinkedListNode<T>(data);
            tail.setNext(another);
            tail = another;
            size++;
        } else {
            int count = 0;
            LinkedListNode<T> current = head;
            LinkedListNode<T> previous = head;
            while (count < index) {
                previous = current;
                current = current.getNext();
                count++;
            }
            LinkedListNode<T> create = new LinkedListNode<T>(data);
            create.setNext(current);
            previous.setNext(create);
            size++;
        }
    }


    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot have null data.");
        }
        if (head == null) {
            LinkedListNode<T> create = new LinkedListNode<T>(data);
            head = create;
            tail = create;
            size++;
        } else {
            LinkedListNode<T> create = new LinkedListNode<T>(data, head);
            head = create;
            size++;
        }
    }


    @Override
    public void addAll(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection is null.");
        }
        for (T item : collection) {
            add(item);
        }
    }


    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot use null data.");
        }
        if (head.getData() == data) {
            return true;
        }
        if (tail.getData() == data) {
            return true;
        }
        if (size == 0) {
            return false;
        } else {
            LinkedListNode<T> current = head.getNext();
            while ((current != null)) {
                if (current.getData() == data) {
                    return true;
                }
                current = current.getNext();
            }
            return false;
        }
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("That index isnt within"
                    + " our current linked list.");
        }
        LinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }


    @Override
    public T remove() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;

        } else {
            T data = tail.getData();
            LinkedListNode<T> previous = head;
            LinkedListNode<T> current = head;
            while (current.getNext() != null) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(null);
            tail = previous;
            size--;
            return data;
        }
    }


    @Override
    public T removeAtIndex(int index) {
        LinkedListNode<T> current = head;
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("That index isnt in our LL.");
        }
        if (index == 0) {
            return removeFromFront();
        } else {
            int count = 0;
            while (count < index - 1) {
                current = current.getNext();
                count++;
            }
            T remove = current.getNext().getData();
            LinkedListNode<T> fixNode = current.getNext().getNext();
            current.setNext(fixNode);
            size--;
            return remove;
        }
    }


    @Override
    public T removeFromFront() {

        if (!isEmpty()) {
            LinkedListNode<T> front = head;
            head = front.getNext();
            size--;
            return front.getData();
        }
        return null;
    }


    @Override
    public T setAtIndex(int index, T data) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("That index isnt within"
                    + " our current linked list.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot use null.");
        }
        if (index == 0) {
            head.setData(data);
            return head.getData();
        }
        if (index == (size - 1)) {
            tail.setData(data);
            return tail.getData();
        } else {
            LinkedListNode<T> current = head;
            int increment = 0;
            while (increment < index) {
                current = current.getNext();
                increment++;
            }
            current.setData(data);
            return data;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] listArray = new Object[size];
        LinkedListNode<T> current = head;
        for (int count = 0; count < size; count++) {
            listArray[count] = current.getData();
            current = current.getNext();
        }
        return listArray;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
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
