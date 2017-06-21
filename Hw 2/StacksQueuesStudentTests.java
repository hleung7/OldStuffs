import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Basic tests for the stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;
    private DoublyLinkedList<Integer> doublyLinkedList;
    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testDoublyLinkedListAdd() {
        System.out.println("Starting DoublyLinkedList add() testing ...");
        doublyLinkedList = new DoublyLinkedList<>();
        assertEquals(0, doublyLinkedList.size());
        assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.add(33);
        doublyLinkedList.add(10);
        doublyLinkedList.add(24);
        doublyLinkedList.add(42);

        // current order: 33, 10, 24, 42
        assertEquals(4, doublyLinkedList.size());
        LinkedListNode<Integer> current = doublyLinkedList.getHead();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 33);
        assertNull(current.getPrev());
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 10);
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 24);
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 42);
        assertNull(current.getNext());

        assertSame(current, doublyLinkedList.getTail());
        System.out.println("Finished DoublyLinkedList add() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testDoublyLinkedListAddToFront() {
        System.out.println("Starting DoublyLinkedList addToFront() "
                + "testing ...");
        doublyLinkedList = new DoublyLinkedList<>();
        assertEquals(0, doublyLinkedList.size());
        assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.addToFront(33);
        doublyLinkedList.addToFront(10);
        doublyLinkedList.addToFront(24);
        doublyLinkedList.addToFront(42);

        // current correct list order: 42, 24, 10, 33
        assertEquals(4, doublyLinkedList.size());
        LinkedListNode<Integer> current = doublyLinkedList.getHead();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 42);
        assertNull(current.getPrev());
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 24);
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 10);
        current = current.getNext();

        assertNotNull(current);
        assertEquals(current.getData(), (Integer) 33);
        assertNull(current.getNext());

        assertSame(current, doublyLinkedList.getTail());
        System.out.println("Finished DoublyLinkedList addToFront() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testDoublyLinkedRemove() {
        System.out.println("Starting DoublyLinkedList remove() testing...");
        doublyLinkedList = new DoublyLinkedList<>();
        assertEquals(0, doublyLinkedList.size());

        doublyLinkedList.add(33);
        doublyLinkedList.add(10);
        doublyLinkedList.add(24);
        doublyLinkedList.add(42);

        // current correct list order: 33, 10, 24, 42
        assertEquals(4, doublyLinkedList.size());

        assertEquals((Integer) 42, doublyLinkedList.remove());
        assertEquals(3, doublyLinkedList.size());

        // current correct list order: 33, 10, 24
        assertEquals((Integer) 24, doublyLinkedList.remove());
        assertEquals(2, doublyLinkedList.size());

        // current correct list order: 33, 10
        assertEquals((Integer) 10, doublyLinkedList.remove());
        assertEquals(1, doublyLinkedList.size());

        // current correct list order: 33
        assertEquals((Integer) 33, doublyLinkedList.remove());
        assertEquals(0, doublyLinkedList.size());

        // current correct list order: [empty]
        assertNull(doublyLinkedList.remove());
        assertEquals(0, doublyLinkedList.size());
        assertTrue(doublyLinkedList.isEmpty());

        assertNull(doublyLinkedList.getHead());
        assertNull(doublyLinkedList.getTail());
        System.out.println("Finished DoublyLinkedList addToFront() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testDoublyLinkedRemoveFromFront() {
        System.out.println("Starting DoublyLinkedList removeFromFront() "
                + "testing...");
        doublyLinkedList = new DoublyLinkedList<>();
        assertEquals(0, doublyLinkedList.size());

        doublyLinkedList.add(33);
        doublyLinkedList.add(10);
        doublyLinkedList.add(24);
        doublyLinkedList.add(42);

        // current correct list order: 33, 10, 24, 42
        assertEquals(4, doublyLinkedList.size());

        assertEquals((Integer) 33, doublyLinkedList.removeFromFront());
        assertEquals(3, doublyLinkedList.size());

        // current correct list order: 10, 24, 42
        assertEquals((Integer) 10, doublyLinkedList.removeFromFront());
        assertEquals(2, doublyLinkedList.size());

        // current correct list order: 24, 42
        assertEquals((Integer) 24, doublyLinkedList.removeFromFront());
        assertEquals(1, doublyLinkedList.size());

        // current correct list order: 42
        assertEquals((Integer) 42, doublyLinkedList.removeFromFront());
        assertEquals(0, doublyLinkedList.size());

        // current correct list order: [empty]
        assertNull(doublyLinkedList.removeFromFront());
        assertEquals(0, doublyLinkedList.size());

        assertNull(doublyLinkedList.getHead());
        assertNull(doublyLinkedList.getTail());
        System.out.println("Finished DoublyLinkedList removeFromFront() "
                + "testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        System.out.println("Starting ArrayStack push() testing...");
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 25; i++) {
            if (i == 10) {
                System.out.println("Now testing for first array expansion to "
                        + "20...");
            }
            if (i == 20) {
                System.out.println("Now testing for first array expansion to "
                        + "40...");
            }
            stack.push(i);
            assertEquals(i + 1, stack.size());
        }
        assertEquals(25, stack.size());

        // current correct array order (from front): 0, 1, 2, 3
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        // 10 was doubled to 20, then doubled to 40
        Object[] expected = new Object[40];
        for (int i = 0; i < 25; i++) {
            expected[i] = i;
        }

        assertArrayEquals(expected, backingArray);
        assertEquals(25, stack.size());
        System.out.println("Finished ArrayStack push() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        System.out.println("Starting ArrayStack pop() testing...");
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 15; i++) {
            if (i == 10) {
                System.out.println("Now testing for first array exapnsion to "
                        + "20...");
            }
            stack.push(i);
            assertEquals(i + 1, stack.size());
        }

        assertEquals(15, stack.size());

        // current stack: 0, ... , 15
        for (int i = 14; i > 8; i--) {
            assertEquals((Integer) i, stack.pop());
            assertEquals(i, stack.size());
        }
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        // since we had 15 objects pushed, we should have a n array of size 20
        Object[] expected = new Object[20];
        for (int i = 0; i < 9; i++) {
            expected[i] = i;
        }

        assertArrayEquals(expected, backingArray);
        assertEquals(9, stack.size());
        System.out.println("Finished ArrayStack pop() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        System.out.println("Starting ArrayQueue enqueue() testing...");
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        for (int i = 1; i <= 25; i++) {
            if (i == 10) {
                System.out.println("Now testing for first array exapnsion to "
                        + "20...");
            }
            if (i == 20) {
                System.out.println("Now testing for first array exapnsion to "
                        + "40...");
            }
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }
        assertEquals(25, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        // 10 was doubled to 20, then doubled to 40
        Object[] expected = new Object[40];
        for (int i = 0; i < 25; i++) {
            expected[i] = i + 1;
        }
        System.out.println("Visual test for correct array vs "
                + "backingarray...\n");
        for (int i = 0; i < backingArray.length; i++) {
            System.out.println(expected[i] + ", " + backingArray[i]);
        }

        assertArrayEquals(expected, backingArray);
        assertEquals(25, queue.size());
        System.out.println("\nFinished ArrayQueue enqueue() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        System.out.println("Starting ArrayQueue dequeue() testing...");
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        // fills queue with numbers 1 through 10
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }
        assertEquals(10, queue.size());

        assertEquals((Integer) 1, queue.dequeue());
        assertEquals(9, queue.size());
        // should have null then 2 through 10
        assertEquals((Integer) 2, queue.dequeue());
        assertEquals(8, queue.size());
        // should have null, null then 3 through 10
        Object[] currBackingArray = new Object[10];
        // array = 10 nulls
        for (int i = 2; i < 10; i++) {
            currBackingArray[i] = i + 1;
        }
        // array = null, null, 3, 4, 5, 6, 7, 8, 9, 10
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        assertArrayEquals(currBackingArray, backingArray);

        queue.enqueue(11);
        // should equal 11, null, 3 through 10
        queue.enqueue(12);
        // should equal 11, 12, 3 through 10
        queue.dequeue();
        // should equal 11, 12, null, 4 through 10

        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        currBackingArray[0] = 11;
        currBackingArray[1] = 12;
        currBackingArray[2] = null;
        currBackingArray[3] = 4;
        currBackingArray[4] = 5;
        currBackingArray[5] = 6;
        currBackingArray[6] = 7;
        currBackingArray[7] = 8;
        currBackingArray[8] = 9;
        currBackingArray[9] = 10;

        assertArrayEquals(currBackingArray, backingArray);

        queue.enqueue(13);
        // should equal 11, 12, 13, 4 thorugh 10
        queue.enqueue(14);
        // should expand and equal 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, and 9
        // nulls; size = 20
        Object[] currBackingArray2 = new Object[20];
        // should have 20 nulls
        for (int i = 0; i < 11; i++) {
            currBackingArray2[i] = i + 4;
        }
        // should have 4 through 14, then 9 nulls
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        System.out.println("Visual test for correct array vs "
                + "backingarray...\n");
        for (int i = 0; i < backingArray.length; i++) {
            System.out.println(currBackingArray2[i] + ", " + backingArray[i]);
        }
        assertArrayEquals(currBackingArray2, backingArray);
        System.out.println("\nFinished ArrayQueue dequeue() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListStackPush() {
        System.out.println("Starting LinkedListStack push() testing...");
        stack = new LinkedListStack<>();
        assertEquals(0, stack.size());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.size());
        DoublyLinkedList<Integer> backingArray = (DoublyLinkedList<Integer>) (
                (LinkedListStack<Integer>) stack).getBackingList();

        DoublyLinkedList<Integer> expected = new DoublyLinkedList<>();
        for (int i = 4; i >= 1; i--) {
            expected.add(i);
        }
        LinkedListNode<Integer> backArrCurr = backingArray.getHead();
        LinkedListNode<Integer> expectedCurr = expected.getHead();
        // 1
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 2
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 3
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 4
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        assertEquals(backArrCurr, backingArray.getTail());
        assertEquals(expectedCurr, expected.getTail());

        assertEquals(4, stack.size());
        System.out.println("Finished LinkedListStack push() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListStackPop() {
        System.out.println("Starting LinkedListStack pop() testing...");
        stack = new LinkedListStack<>();
        assertEquals(0, stack.size());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.size());

        assertEquals((Integer) 5, stack.pop());
        assertEquals((Integer) 4, stack.pop());
        assertEquals((Integer) 3, stack.pop());
        assertEquals((Integer) 2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals((Integer) 1, stack.pop());

        assertEquals(0, stack.size());
        System.out.println("Finished LinkedListStack pop() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListQueueEnqueue() {
        System.out.println("Starting LinkedListQueue enqueue() testing...");
        queue = new LinkedListQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        DoublyLinkedList<Integer> backingArray = (DoublyLinkedList<Integer>) (
                (LinkedListQueue<Integer>) queue).getBackingList();

        DoublyLinkedList<Integer> expected = new DoublyLinkedList<>();
        for (int i = 1; i <= 4; i++) {
            expected.add(i);
        }
        LinkedListNode<Integer> backArrCurr = backingArray.getHead();
        LinkedListNode<Integer> expectedCurr = expected.getHead();
        // 1
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 2
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 3
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        backArrCurr = backArrCurr.getNext();
        expectedCurr = expectedCurr.getNext();
        // 4
        assertEquals(backArrCurr.getData(), expectedCurr.getData());
        assertEquals(backArrCurr, backingArray.getTail());
        assertEquals(expectedCurr, expected.getTail());

        assertEquals(4, queue.size());
        System.out.println("Finished LinkedListQueue enqueue() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListQueueDequeue() {
        System.out.println("Starting LinkedListQueue dequeue() testing...");
        queue = new LinkedListQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        assertEquals(5, queue.size());

        assertEquals((Integer) 10, queue.dequeue());
        assertEquals((Integer) 20, queue.dequeue());
        assertEquals((Integer) 30, queue.dequeue());
        assertEquals((Integer) 40, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals((Integer) 50, queue.dequeue());

        assertEquals(0, queue.size());
        System.out.println("Finished LinkedListQueue dequeue() testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testEdgeCasesAndIsEmpty() {
        System.out.println("Starting Edge Case Testing...");

        stack = new ArrayStack<>();
        queue = new ArrayQueue<>();
        doublyLinkedList = new DoublyLinkedList<>();
        StackInterface<Integer> stack2 = new LinkedListStack<>();
        QueueInterface<Integer> queue2 = new LinkedListQueue<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        assertTrue(queue.isEmpty());
        assertEquals(0, stack.size());

        assertTrue(doublyLinkedList.isEmpty());
        assertEquals(0, stack.size());
        assertNull(doublyLinkedList.getHead());
        assertNull(doublyLinkedList.getTail());

        assertTrue(stack2.isEmpty());
        assertEquals(0, stack2.size());

        assertTrue(queue2.isEmpty());
        assertEquals(0, queue2.size());

        assertTrue(stack.isEmpty());
        stack.push(0);
        assertEquals(1, stack.size());
        assertEquals((Integer) 0, stack.pop());
        assertTrue(stack.isEmpty());
        System.out.println("Size 0 & 1 edge tests: Your ArrayStack "
                + "passes.");

        assertTrue(stack2.isEmpty());
        stack2.push(0);
        assertEquals(1, stack2.size());
        assertEquals((Integer) 0, stack2.pop());
        assertTrue(stack2.isEmpty());
        System.out.println("Size 0 & 1 edge tests: Your LinkedListStack "
                + "passes.");

        assertTrue(queue.isEmpty());
        queue.enqueue(0);
        assertEquals(1, queue.size());
        assertEquals((Integer) 0, queue.dequeue());
        assertTrue(queue.isEmpty());
        System.out.println("Size 0 & 1 edge tests: Your ArrayQueue "
                + "passes.");

        assertTrue(queue2.isEmpty());
        queue2.enqueue(0);
        assertEquals(1, queue2.size());
        assertEquals((Integer) 0, queue2.dequeue());
        assertTrue(queue2.isEmpty());
        System.out.println("Size 0 & 1 edge tests: Your LinkedListQueue "
                + "passes.");

        assertTrue(doublyLinkedList.isEmpty());
        doublyLinkedList.add(0);
        assertEquals((Integer) 0, doublyLinkedList.getHead().getData());
        assertTrue(doublyLinkedList.getTail().equals(doublyLinkedList
                .getHead()));
        assertEquals((Integer) 0, doublyLinkedList.remove());
        assertNull(doublyLinkedList.remove());
        System.out.println("Size 0 & 1 edge tests: Your DoublyLinkedList "
                + "passes for tests for add() and remove().");

        assertTrue(doublyLinkedList.isEmpty());
        doublyLinkedList.addToFront(0);
        assertEquals((Integer) 0, doublyLinkedList.getHead().getData());
        assertTrue(doublyLinkedList.getTail().equals(doublyLinkedList
                .getHead()));
        assertEquals((Integer) 0, doublyLinkedList.removeFromFront());
        assertNull(doublyLinkedList.removeFromFront());
        assertTrue(doublyLinkedList.isEmpty());
        System.out.println("Size 0 & 1 edge tests: Your DoublyLinkedList "
                + "passes for tests for addToFront() and removeFromFront().");

        System.out.println("Finished Edge Case Testing!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testExceptions() {
        System.out.println("Starting Exceptions testing...");
        stack = new ArrayStack<>();
        queue = new ArrayQueue<>();
        doublyLinkedList = new DoublyLinkedList<>();
        StackInterface<Integer> stack2 = new LinkedListStack<>();
        QueueInterface<Integer> queue2 = new LinkedListQueue<>();

        try {
            stack.push(null);
            System.out.println("It is illegal to push null data"
                    + " to your stack. Check your IllegalArgumentException"
                    + " in your ArrayStack class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "IllegalArgumentException in your ArrayStack class.");
            assertTrue(false);
        }

        try {
            queue.enqueue(null);
            System.out.println("It is illegal to enqueue null data"
                    + " to your queue. Check your IllegalArgumentException"
                    + " in your ArrayQueue class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "IllegalArgumentException in your ArrayQueue class.");
            assertTrue(false);
        }

        try {
            stack.pop();
            System.out.println("You did not throw a "
                    + "NoSuchElementException in your ArrayStack class "
                    + "for when a stack is empty.");
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your ArrayStack class.");
            assertTrue(false);
        }

        try {
            queue.dequeue();
            System.out.println("You did not throw a "
                    + "NoSuchElementException in your ArrayQueue class "
                    + "for when a queue is empty.");
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your ArrayQueue class.");
            assertTrue(false);
        }

        try {
            stack2.push(null);
            System.out.println("It is illegal to push null data"
                    + " to your stack. Check your IllegalArgumentException"
                    + " in your LinkedListStack class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "IllegalArgumentException in your ArrayStack class.");
            assertTrue(false);
        }

        try {
            queue2.enqueue(null);
            System.out.println("It is illegal to enqueue null data"
                    + " to your queue. Check your IllegalArgumentException"
                    + " in your LinkedListQueue class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "IllegalArgumentException in your LinkedListQueue class"
                    + ".");
            assertTrue(false);
        }

        try {
            stack2.pop();
            System.out.println("You did not throw a "
                    + "NoSuchElementException in your LinkedListStack class "
                    + "for when a stack is empty.");
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your LinkedListStack class.");
            assertTrue(false);
        }

        try {
            queue2.dequeue();
            System.out.println("You did not throw a "
                    + "NoSuchElementException in your LinkedListQueue class "
                    + "for when a queue is empty.");
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your LinkedListQueue class.");
            assertTrue(false);
        }

        try {
            doublyLinkedList.add(null);
            System.out.println("It is illegal to add null data"
                    + " to your list. Check your IllegalArgumentException"
                    + " in your DoublyLinkedList class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your DoublyLinkedList class.");
            assertTrue(false);
        }

        try {
            doublyLinkedList.addToFront(null);
            System.out.println("It is illegal to add null data"
                    + " to your list. Check your IllegalArgumentException"
                    + " in your DoublyLinkedList class.");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("You incorrectly implemented "
                    + "NoSuchElementException in your DoublyLinkedList class.");
            assertTrue(false);
        }
        System.out.println("Finished Exceptions testing!\n");
    }
}