import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Basic tests for the implementation of a BST.
 *
 * @author CS 1332 TAs
 * @author gbianco6
 * @version 1.1
 */
public class MinHeapStudentTests {

    private MinHeap<Integer> minHeap;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddConstructor1() {
        minHeap = new MinHeap<>(4, 6, 8, 3, 2);

        assertEquals(6, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals(2, minHeap.getBackingArray()[1]);
        assertEquals(3, minHeap.getBackingArray()[2]);
        assertEquals(8, minHeap.getBackingArray()[3]);
        assertEquals(4, minHeap.getBackingArray()[4]);
        assertEquals(6, minHeap.getBackingArray()[5]);

        assertEquals(5, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddConstructor2() {
        minHeap = new MinHeap<>(25, 6, 19, 8, 2, 14, 15, 7);

        assertEquals(9, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals(2, minHeap.getBackingArray()[1]);
        assertEquals(6, minHeap.getBackingArray()[2]);
        assertEquals(14, minHeap.getBackingArray()[3]);
        assertEquals(7, minHeap.getBackingArray()[4]);
        assertEquals(25, minHeap.getBackingArray()[5]);
        assertEquals(19, minHeap.getBackingArray()[6]);
        assertEquals(15, minHeap.getBackingArray()[7]);
        assertEquals(8, minHeap.getBackingArray()[8]);

        assertEquals(8, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd1() {
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(0, minHeap.size());

        minHeap.add(4);
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(1, minHeap.size());
        assertEquals(4, minHeap.getBackingArray()[1]);
        assertNull(minHeap.getBackingArray()[0]);

        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        assertEquals(10, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals(2, minHeap.getBackingArray()[1]);
        assertEquals(3, minHeap.getBackingArray()[2]);
        assertEquals(8, minHeap.getBackingArray()[3]);
        assertEquals(6, minHeap.getBackingArray()[4]);
        assertEquals(4, minHeap.getBackingArray()[5]);
        assertNull(minHeap.getBackingArray()[6]);
        assertNull(minHeap.getBackingArray()[7]);
        assertNull(minHeap.getBackingArray()[8]);
        assertNull(minHeap.getBackingArray()[9]);

        assertEquals(5, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd2() {

        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(17);
        minHeap.add(3);
        minHeap.add(11);
        minHeap.add(8);
        minHeap.add(10);
        minHeap.add(2);
        minHeap.add(9);
        minHeap.add(16);
        minHeap.add(2);
        minHeap.add(20);

        assertEquals(20, minHeap.getBackingArray().length);

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals(2, minHeap.getBackingArray()[1]);
        assertEquals(2, minHeap.getBackingArray()[2]);
        assertEquals(8, minHeap.getBackingArray()[3]);
        assertEquals(4, minHeap.getBackingArray()[4]);
        assertEquals(3, minHeap.getBackingArray()[5]);
        assertEquals(17, minHeap.getBackingArray()[6]);
        assertEquals(10, minHeap.getBackingArray()[7]);
        assertEquals(6, minHeap.getBackingArray()[8]);
        assertEquals(9, minHeap.getBackingArray()[9]);
        assertEquals(16, minHeap.getBackingArray()[10]);
        assertEquals(11, minHeap.getBackingArray()[11]);
        assertEquals(20, minHeap.getBackingArray()[12]);
        assertNull(minHeap.getBackingArray()[13]);
        assertNull(minHeap.getBackingArray()[14]);
        assertNull(minHeap.getBackingArray()[15]);
        assertNull(minHeap.getBackingArray()[16]);
        assertNull(minHeap.getBackingArray()[17]);
        assertNull(minHeap.getBackingArray()[18]);
        assertNull(minHeap.getBackingArray()[19]);

        assertEquals(12, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove1() {
        minHeap.add(4);
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(1, minHeap.size());

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[10], minHeap.getBackingArray());

        minHeap.clear();
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(0, minHeap.size());

        minHeap.add(4);
        minHeap.add(6);
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(2, minHeap.size());

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(1, minHeap.size());
        assertArrayEquals(new Integer[] {null, 6, null, null, null, null,
                null, null, null, null}, minHeap.getBackingArray());

        minHeap.clear();

        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(5, minHeap.size());

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(4, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 3, 4, 8, 6,
                        null, null, null, null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 3, minHeap.remove());
        assertEquals(3, minHeap.size());
        assertArrayEquals(new Integer[] {null, 4, 6, 8, null, null,
                null, null, null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(2, minHeap.size());
        assertArrayEquals(new Integer[] {null, 6, 8, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 6, minHeap.remove());
        assertEquals(1, minHeap.size());
        assertArrayEquals(new Integer[] {null, 8, null, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 8, minHeap.remove());
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[10], minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove2() {
        minHeap.add(4);
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(1, minHeap.size());
        assertNull(minHeap.getBackingArray()[0]);
        assertEquals((Integer) 4, minHeap.getBackingArray()[1]);
        minHeap.add(6);
        minHeap.add(17);
        minHeap.add(3);
        minHeap.add(11);
        minHeap.add(8);
        minHeap.add(10);
        minHeap.add(2);
        minHeap.add(9);
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(9, minHeap.size());
        minHeap.add(16);
        assertEquals(20, minHeap.getBackingArray().length);
        assertEquals(10, minHeap.size());
        minHeap.add(2);
        minHeap.add(20);

        assertEquals(20, minHeap.getBackingArray().length);
        assertEquals(12, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 2, 2, 8, 4, 3, 17, 10, 6, 9, 16, 11, 20,
                        null, null, null, null, null, null, null}, minHeap
                        .getBackingArray());

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(11, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 2, 3, 8, 4, 11, 17, 10, 6, 9, 16, 20,
                        null, null, null, null, null, null, null, null}, minHeap
                        .getBackingArray());

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(10, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 3, 4, 8, 6, 11, 17, 10, 20, 9, 16, null,
                        null, null, null, null, null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 3, minHeap.remove());
        assertEquals(9, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 4, 6, 8, 9, 11, 17, 10, 20, 16, null, null,
                        null, null, null, null, null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(8, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 6, 9, 8, 16, 11, 17, 10, 20, null, null,
                        null, null, null, null, null, null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 6, minHeap.remove());
        assertEquals(7, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 8, 9, 10, 16, 11, 17, 20, null, null, null,
                        null, null, null, null, null, null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 8, minHeap.remove());
        assertEquals(6, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 9, 11, 10, 16, 20, 17, null, null, null,
                        null, null, null, null, null, null, null, null, null, null},
                minHeap.getBackingArray());

        assertEquals((Integer) 9, minHeap.remove());
        assertEquals(5, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 10, 11, 17, 16, 20, null, null, null, null,
                        null, null, null, null, null, null, null, null, null,
                        null}, minHeap.getBackingArray());

        assertEquals((Integer) 10, minHeap.remove());
        assertEquals(4, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 11, 16, 17, 20, null, null, null, null,
                        null, null, null, null, null, null, null, null, null,
                        null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 11, minHeap.remove());
        assertEquals(3, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 16, 20, 17, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null,
                        null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 16, minHeap.remove());
        assertEquals(2, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 17, 20, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                        null}, minHeap.getBackingArray());

        assertEquals((Integer) 17, minHeap.remove());
        assertEquals(1, minHeap.size());
        assertArrayEquals(
                new Integer[] {null, 20, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                        null, null}, minHeap.getBackingArray());

        assertEquals((Integer) 20, minHeap.remove());
        assertEquals(0, minHeap.size());
        assertArrayEquals(
                new Integer[20], minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove3() {
        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(0, minHeap.size());

        minHeap.add(2);
        minHeap.add(4);
        minHeap.add(4);
        minHeap.add(10);
        minHeap.add(12);
        minHeap.add(9);
        minHeap.add(11);

        assertEquals(10, minHeap.getBackingArray().length);
        assertEquals(7, minHeap.size());

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(6, minHeap.size());

        assertNull(minHeap.getBackingArray()[0]);
        assertEquals(4, minHeap.getBackingArray()[1]);
        assertEquals(10, minHeap.getBackingArray()[2]);
        assertEquals(4, minHeap.getBackingArray()[3]);
        assertEquals(11, minHeap.getBackingArray()[4]);
        assertEquals(12, minHeap.getBackingArray()[5]);
        assertEquals(9, minHeap.getBackingArray()[6]);
        assertNull(minHeap.getBackingArray()[7]);
        assertNull(minHeap.getBackingArray()[8]);
        assertNull(minHeap.getBackingArray()[9]);

        assertEquals(6, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromCollection() {
        minHeap = new MinHeap<>(13, 4, 9, 12, 10, 7, 20, 8, 14, 2, 6, 21);
        assertEquals(12, minHeap.size());
        assertEquals(13, minHeap.getBackingArray().length);
        assertArrayEquals(minHeap.getBackingArray(), new Integer[] {null, 2,
                4, 7, 8, 6, 9, 20, 12, 14, 10, 13, 21});

        assertEquals((Integer) 2, minHeap.remove());
        assertEquals(11, minHeap.size());
        assertArrayEquals(minHeap.getBackingArray(), new Integer[] {null, 4,
                6, 7, 8, 10, 9, 20, 12, 14, 21, 13, null});

        assertEquals((Integer) 4, minHeap.remove());
        assertEquals(10, minHeap.size());
        assertArrayEquals(minHeap.getBackingArray(), new Integer[] {null, 6,
                8, 7, 12, 10, 9, 20, 13, 14, 21, null, null});

        assertEquals(13, minHeap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(minHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        assertEquals(0, minHeap.size());
        minHeap.add(1);
        assertEquals(1, minHeap.size());
        minHeap.add(2);
        assertEquals(2, minHeap.size());
        minHeap.remove();
        assertEquals(1, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        minHeap.add(4);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(3);
        minHeap.add(2);

        minHeap.clear();
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[10], minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddExceptionNullData() {
        minHeap.add(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveExceptionEmpty() {
        minHeap.remove();
    }
}