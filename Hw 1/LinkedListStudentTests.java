import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Student tests for the Linked List Homework.
 *
 * THIS DOES NOT TEST EVERYTHING.  YOU SHOULD WRITE YOUR OWN TESTS AS WELL.
 *
 * @author gbianco6
 */
public class LinkedListStudentTests {

    public static final int TIMEOUT = 500;

    private LinkedListInterface<String> list;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());

        list.add("one");
        assertEquals(list.getHead(), list.getTail());
        // Your pointers are not pointed correctly
        assertNull(list.getTail().getNext());
        // Your tail is not pointing to a null instance
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        try {
            list.add(null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        assertEquals(5, list.size());

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        LinkedListNode<String> curr = list.getHead();
        assertNotNull(curr);
        assertEquals("one", curr.getData());

        curr = curr.getNext();
        assertNotNull(curr);
        assertEquals("two", curr.getData());

        curr = curr.getNext();
        assertNotNull(curr);
        assertEquals("three", curr.getData());

        curr = curr.getNext();
        assertNotNull(curr);
        assertEquals("four", curr.getData());

        curr = curr.getNext();
        assertNotNull(curr);
        assertEquals("five", curr.getData());

        assertSame(curr, list.getTail());
        assertNull(curr.getNext());
    }


    @Test(timeout = TIMEOUT)
    public void testAddToIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addToIndex(0, "one");
        list.addToIndex(1, "two");
        list.addToIndex(2, "three");
        list.addToIndex(3, "four");
        list.addToIndex(0, "five");

        try {
            list.addToIndex(2, null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        try {
            list.addToIndex(-1, "lol");
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        }

        assertEquals(5, list.size());

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("five", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("one", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("two", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("three", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("four", current.getData());

        assertSame(current, list.getTail());
        assertNull(current.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addToFront("one");
        list.addToFront("two");
        list.addToFront("three");
        list.addToFront("four");
        list.addToFront("five");

        try {
            list.addToFront(null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        assertEquals(5, list.size());

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("five", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("four", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("three", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("two", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("one", current.getData());

        assertSame(current, list.getTail());
        assertNull(current.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAll() {
        assertEquals(0, list.size());

        ArrayList<String> testAll = new ArrayList<>();

        try {
            list.addAll(null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        testAll.add("one");
        testAll.add("two");
        testAll.add("three");
        testAll.add("four");
        testAll.add("five");

        assertEquals(5, testAll.size());

        list.addAll(testAll);

        assertEquals(5, list.size());

        LinkedListNode<String> traverse = list.getHead();
        assertEquals("one", traverse.getData());
        traverse = traverse.getNext();

        assertEquals("two", traverse.getData());
        traverse = traverse.getNext();

        assertEquals("three", traverse.getData());
        traverse = traverse.getNext();

        assertEquals("four", traverse.getData());
        traverse = traverse.getNext();

        assertEquals("five", traverse.getData());
        assertEquals(traverse, list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveGeneral() {
        assertEquals(0, list.size());

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        assertEquals(5, list.size());

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        assertEquals("five", list.remove());
        assertEquals(4, list.size());

        assertEquals("four", list.remove());
        assertEquals(3, list.size());

        LinkedListNode<String> cur = list.getHead();
        assertEquals("one", cur.getData());

        cur = cur.getNext();
        assertEquals("two", cur.getData());

        cur = cur.getNext();
        assertEquals("three", cur.getData());
        assertSame(cur, list.getTail());

        cur = cur.getNext();
        assertNull(cur);

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.remove());

        list.add("one");
        assertEquals(1, list.size());
        assertEquals("one", list.remove());
        assertTrue(list.isEmpty());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        try {
            list.removeAtIndex(0);
            assertTrue("You did NOT properly implement " +
                    "IndexOutOFBoundsException" +
                    ".", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        }

        assertEquals(0, list.size());

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        assertEquals("one", list.removeAtIndex(0));
        assertEquals(4, list.size());

        assertEquals("five", list.removeAtIndex(3));
        assertEquals(3, list.size());

        assertEquals("two", list.removeAtIndex(0));
        assertEquals(2, list.size());

        assertEquals("four", list.removeAtIndex(1));
        assertEquals(1, list.size());

        assertEquals("three", list.removeAtIndex(0));
        assertEquals(0, list.size());

        try {
            list.removeAtIndex(-1);
            assertTrue("You did NOT properly implement " +
                    "IndexOutOFBoundsException" +
                    ".", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        }

        assertEquals(0, list.size());
        list.add("String");
        assertEquals(1, list.size());
        assertEquals("String", list.removeAtIndex(0));
        assertEquals(0, list.size());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        assertEquals(5, list.size());

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        assertEquals("three", list.removeAtIndex(2));
        assertEquals(4, list.size());

        assertEquals("five", list.remove());
        assertEquals(3, list.size());

        LinkedListNode<String> curr = list.getHead();
        assertEquals("one", curr.getData());

        curr = curr.getNext();
        assertEquals("two", curr.getData());

        curr = curr.getNext();
        assertEquals("four", curr.getData());
        assertSame(curr, list.getTail());

        curr = curr.getNext();
        assertNull(curr);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertEquals(0, list.size());

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        assertEquals(5, list.size());

        try {
            list.contains(null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        assertTrue(list.contains("one"));
        assertTrue(list.contains("two"));
        assertTrue(list.contains("three"));
        assertTrue(list.contains("four"));
        assertTrue(list.contains("five"));
        assertFalse(list.contains("pickles"));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        assertEquals(0, list.size());

        list.addToIndex(0, "one");
        list.addToIndex(1, "two");
        list.addToIndex(2, "three");
        list.addToIndex(3, "four");
        list.addToIndex(4, "five");

        assertEquals(5, list.size());

        String one = list.removeFromFront();
        assertEquals(one, "one");
        assertEquals(4, list.size());
        assertEquals("two", list.getHead().getData());

        String two = list.removeFromFront();
        assertEquals(two, "two");
        assertEquals(3, list.size());
        assertEquals("three", list.getHead().getData());

        String three = list.removeFromFront();
        assertEquals(three, "three");
        assertEquals(2, list.size());
        assertEquals("four", list.getHead().getData());

        String four = list.removeFromFront();
        assertEquals(four, "four");
        assertEquals(1, list.size());
        assertEquals("five", list.getHead().getData());
        assertEquals("five", list.getTail().getData());

        String five = list.removeFromFront();
        assertEquals(five, "five");
        assertEquals(0, list.size());
        assertNull(list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addToIndex(0, "one");
        list.addToIndex(1, "two");
        list.addToIndex(2, "three");
        list.addToIndex(3, "four");
        list.addToIndex(4, "five");

        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("four", list.get(3));
        assertEquals("five", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testClearAndIsEmpty() {
        list.addToIndex(0, "one");
        list.addToIndex(1, "two");
        list.addToIndex(2, "three");
        list.addToIndex(3, "four");
        list.addToIndex(4, "five");

        assertEquals(5, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());

    }

    @Test(timeout = TIMEOUT)
    public void testSetAtIndex(){
        assertEquals(0, list.size());

        try {
            list.setAtIndex(-1, "something");
            assertTrue("You did NOT properly implement " +
                    "IndexOutOFBoundsException" +
                    ".", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        }

        list.addToIndex(0, "one");
        list.addToIndex(1, "two");
        list.addToIndex(2, "three");
        list.addToIndex(3, "four");
        list.addToIndex(4, "five");

        try {
            list.setAtIndex(6, "something");
            assertTrue("You did NOT properly implement " +
                    "IndexOutOFBoundsException" +
                    ".", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IndexOutOfBoundsException" +
                    ".", false);
        }

        try {
            list.setAtIndex(1, null);
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("You did NOT properly implement " +
                    "IllegalArgumentException" +
                    ".", false);
        }

        String hold1 = list.setAtIndex(1, "dos");
        assertEquals(hold1, "two");
        String hold2 = list.setAtIndex(2, "tres");
        assertEquals(hold2, "three");
        String hold3 = list.setAtIndex(4, "cinco");
        assertEquals(hold3, "five");

        assertEquals("one", list.get(0));
        assertEquals("dos", list.get(1));
        assertEquals("tres", list.get(2));
        assertEquals("four", list.get(3));
        assertEquals("cinco", list.get(4));

    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.add(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }
}