import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author gbianco6
 * @author CS 1332 TAs
 * @version 1.1
 */
public class AVLStudentTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightRotation() {
        avlTree.add(30);
        avlTree.add(20);
        avlTree.add(10);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 20, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 10,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 30,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRightRotation() {
        avlTree.add(30);
        avlTree.add(10);
        avlTree.add(20);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertNotNull(avlTree.getRoot());
        assertEquals((Integer) 20, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 10,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 30,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    /**
     * Tests for each type of rotation
     * left: add(80)
     * right: add(32)
     * left-right: add(57)
     * right-left: add(30)
     */
    @Test(timeout = TIMEOUT)
    public void testAdd1() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);
        avlTree.add(25);
        avlTree.add(80);
        avlTree.add(55);
        avlTree.add(35);
        avlTree.add(32);
        avlTree.add(33);
        avlTree.add(57);

        assertEquals(13, avlTree.size());
        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals((Integer) 30, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 35, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 57, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 25, avlTree.getRoot().getLeft().getLeft()
                .getRight().getData());
        assertEquals((Integer) 32, avlTree.getRoot().getLeft().getRight()
                .getLeft().getData());
        assertEquals((Integer) 40, avlTree.getRoot().getLeft().getRight()
                .getRight().getData());
        assertEquals((Integer) 55, avlTree.getRoot().getRight().getLeft()
                .getLeft().getData());
        assertEquals((Integer) 33, avlTree.getRoot().getLeft().getRight()
                .getLeft().getRight().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getRight().getData());
        assertEquals(4, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testAddCollection() {
        ArrayList<Integer> col = new ArrayList<>();
        col.add(50);
        col.add(20);
        col.add(60);
        col.add(40);
        col.add(30);
        col.add(70);
        col.add(25);
        col.add(80);
        col.add(55);
        col.add(35);
        col.add(32);
        col.add(33);
        col.add(57);

        avlTree = new AVL<>(col);

        assertEquals(13, avlTree.size());
        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals((Integer) 30, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 35, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 57, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 25, avlTree.getRoot().getLeft().getLeft()
                .getRight().getData());
        assertEquals((Integer) 32, avlTree.getRoot().getLeft().getRight()
                .getLeft().getData());
        assertEquals((Integer) 40, avlTree.getRoot().getLeft().getRight()
                .getRight().getData());
        assertEquals((Integer) 55, avlTree.getRoot().getRight().getLeft()
                .getLeft().getData());
        assertEquals((Integer) 33, avlTree.getRoot().getLeft().getRight()
                .getLeft().getRight().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getRight().getData());
        assertEquals(4, avlTree.height());
    }

    /**
     * Tests for removal of node with 1, 2, and 0 children
     */
    @Test(timeout = TIMEOUT)
    public void testRemove2() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);
        avlTree.add(25);
        avlTree.add(80);
        avlTree.add(55);
        avlTree.add(35);
        avlTree.add(32);
        avlTree.add(33);
        avlTree.add(57);
        assertNotNull(avlTree.getRoot());

        assertEquals(13, avlTree.size());
        assertEquals(4, avlTree.height());

        assertEquals((Integer) 30, avlTree.remove(30));

        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals((Integer) 32, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 35, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 57, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 25, avlTree.getRoot().getLeft().getLeft()
                .getRight().getData());
        assertEquals((Integer) 33, avlTree.getRoot().getLeft().getRight()
                .getLeft().getData());
        assertEquals((Integer) 40, avlTree.getRoot().getLeft().getRight()
                .getRight().getData());
        assertEquals((Integer) 55, avlTree.getRoot().getRight().getLeft()
                .getLeft().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getRight().getData());

        assertEquals(3, avlTree.height());
        assertEquals(12, avlTree.size());

        assertEquals((Integer) 20, avlTree.remove(20));

        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals((Integer) 32, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 25, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 35, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 57, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 33, avlTree.getRoot().getLeft().getRight()
                .getLeft().getData());
        assertEquals((Integer) 40, avlTree.getRoot().getLeft().getRight()
                .getRight().getData());
        assertEquals((Integer) 55, avlTree.getRoot().getRight().getLeft()
                .getLeft().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getRight().getData());

        assertEquals(3, avlTree.height());
        assertEquals(11, avlTree.size());

        assertEquals((Integer) 55, avlTree.remove(55));

        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals((Integer) 32, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 25, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 35, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 57, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 33, avlTree.getRoot().getLeft().getRight()
                .getLeft().getData());
        assertEquals((Integer) 40, avlTree.getRoot().getLeft().getRight()
                .getRight().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getRight().getData());
        assertEquals(3, avlTree.height());
        assertEquals(10, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLRRotation() {
        avlTree.add(50);
        avlTree.add(30);
        avlTree.add(20);
        avlTree.add(10);
        avlTree.add(29);
        avlTree.add(70);
        avlTree.add(15);

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals(7, avlTree.size());
        assertEquals(3, avlTree.height());

        assertEquals((Integer) 29, avlTree.remove(29));

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals((Integer) 15, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 50, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getRight()
                .getData());

        assertEquals(6, avlTree.size());
        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRLRotation() {
        avlTree.add(30);
        avlTree.add(15);
        avlTree.add(50);
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(70);
        avlTree.add(40);
        avlTree.add(60);

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals(8, avlTree.size());
        assertEquals(3, avlTree.height());

        assertEquals((Integer) 40, avlTree.remove(40));

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals((Integer) 15, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 50, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getRight()
                .getData());

        assertEquals(7, avlTree.size());
        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRRotation() {
        avlTree.add(30);
        avlTree.add(15);
        avlTree.add(60);
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(50);
        avlTree.add(70);
        avlTree.add(5);

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals(8, avlTree.size());
        assertEquals(3, avlTree.height());

        assertEquals((Integer) 20, avlTree.remove(20));

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 5, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 15, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 50, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getRight()
                .getData());

        assertEquals(7, avlTree.size());
        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLRotation() {
        avlTree.add(30);
        avlTree.add(15);
        avlTree.add(60);
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(50);
        avlTree.add(70);
        avlTree.add(80);

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals(8, avlTree.size());
        assertEquals(3, avlTree.height());

        assertEquals((Integer) 50, avlTree.remove(50));

        assertEquals((Integer) 30, avlTree.getRoot().getData());
        assertEquals((Integer) 15, avlTree.getRoot().getLeft().getData());
        assertEquals((Integer) 70, avlTree.getRoot().getRight().getData());
        assertEquals((Integer) 10, avlTree.getRoot().getLeft().getLeft()
                .getData());
        assertEquals((Integer) 20, avlTree.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 60, avlTree.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 80, avlTree.getRoot().getRight().getRight()
                .getData());

        assertEquals(7, avlTree.size());
        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testBalanceFactors() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);
        avlTree.add(25);
        avlTree.add(80);
        avlTree.add(55);
        avlTree.add(35);
        avlTree.add(32);
        avlTree.add(33);
        avlTree.add(57);

        // root 50
        assertEquals(1, avlTree.getRoot().getBalanceFactor());
        // 30
        assertEquals(-1, avlTree.getRoot().getLeft().getBalanceFactor());
        // 70
        assertEquals(1, avlTree.getRoot().getRight().getBalanceFactor());
        // 20
        assertEquals(-1, avlTree.getRoot().getLeft().getLeft()
                .getBalanceFactor());
        // 35
        assertEquals(1, avlTree.getRoot().getLeft().getRight()
                .getBalanceFactor());
        // 57
        assertEquals(0, avlTree.getRoot().getRight().getLeft()
                .getBalanceFactor());
        // 80
        assertEquals(0, avlTree.getRoot().getRight().getRight()
                .getBalanceFactor());
        // 25
        assertEquals(0, avlTree.getRoot().getLeft().getLeft()
                .getRight().getBalanceFactor());
        // 32
        assertEquals(-1, avlTree.getRoot().getLeft().getRight()
                .getLeft().getBalanceFactor());
        // 40
        assertEquals(0, avlTree.getRoot().getLeft().getRight()
                .getRight().getBalanceFactor());
        // 55
        assertEquals(0, avlTree.getRoot().getRight().getLeft()
                .getLeft().getBalanceFactor());
        // 60
        assertEquals(0, avlTree.getRoot().getRight().getLeft()
                .getRight().getBalanceFactor());
        // 33
        assertEquals(0, avlTree.getRoot().getLeft().getRight()
                .getLeft().getRight().getBalanceFactor());

        assertEquals(13, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void testNodeHeights() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);
        avlTree.add(25);
        avlTree.add(80);
        avlTree.add(55);
        avlTree.add(35);
        avlTree.add(32);
        avlTree.add(33);
        avlTree.add(57);

        // root 50
        assertEquals(4, avlTree.getRoot().getHeight());
        // 30
        assertEquals(3, avlTree.getRoot().getLeft().getHeight());
        // 70
        assertEquals(2, avlTree.getRoot().getRight().getHeight());
        // 20
        assertEquals(1, avlTree.getRoot().getLeft().getLeft()
                .getHeight());
        // 35
        assertEquals(2, avlTree.getRoot().getLeft().getRight()
                .getHeight());
        // 57
        assertEquals(1, avlTree.getRoot().getRight().getLeft()
                .getHeight());
        // 80
        assertEquals(0, avlTree.getRoot().getRight().getRight()
                .getHeight());
        // 25
        assertEquals(0, avlTree.getRoot().getLeft().getLeft()
                .getRight().getHeight());
        // 32
        assertEquals(1, avlTree.getRoot().getLeft().getRight()
                .getLeft().getHeight());
        // 40
        assertEquals(0, avlTree.getRoot().getLeft().getRight()
                .getRight().getHeight());
        // 55
        assertEquals(0, avlTree.getRoot().getRight().getLeft()
                .getLeft().getHeight());
        // 60
        assertEquals(0, avlTree.getRoot().getRight().getLeft()
                .getRight().getHeight());
        // 33
        assertEquals(0, avlTree.getRoot().getLeft().getRight()
                .getLeft().getRight().getHeight());

        assertEquals(13, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove1() {
        Integer toBeRemoved = new Integer(40);
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(toBeRemoved);
        avlTree.add(30);

        assertSame(toBeRemoved, avlTree.remove(new Integer(40)));

        assertEquals(4, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals((Integer) 30,
                root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 60,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);

        assertEquals(2, avlTree.height());

        avlTree.clear();
        assertNull(avlTree.getRoot());
        assertEquals(0, avlTree.size());

        assertEquals(-1, avlTree.height());
        avlTree.add(50);
        assertEquals((Integer) 50, avlTree.getRoot().getData());
        assertEquals(0, avlTree.height());
        assertEquals(1, avlTree.size());

        avlTree.add(20);
        assertEquals(1, avlTree.height());
        assertEquals(2, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer maximum = new Integer(50);
        avlTree.add(40);
        avlTree.add(20);
        avlTree.add(30);
        avlTree.add(maximum);
        avlTree.add(60);

        assertSame(maximum, avlTree.get(new Integer(50)));
    }

    @Test(timeout = TIMEOUT)
    public void testDepth() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);

        assertEquals(3, avlTree.depth(40));
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);

        assertFalse(avlTree.contains(10));
        assertTrue(avlTree.contains(30));
    }

    @Test(timeout = TIMEOUT)
    public void testFindPathBetween() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);

        List<Integer> expected = new ArrayList<>();
        expected.add(20);
        expected.add(30);
        expected.add(50);
        expected.add(60);
        expected.add(70);

        expected = new ArrayList<>();
        expected.add(60);
        expected.add(50);
        expected.add(30);
        expected.add(40);

        assertEquals(expected, avlTree.findPathBetween(60, 40));

        avlTree.add(25);
        avlTree.add(80);
        avlTree.add(55);
        avlTree.add(35);
        avlTree.add(32);
        avlTree.add(33);

        expected = new ArrayList<>();
        expected.add(25);
        expected.add(20);
        expected.add(30);
        expected.add(35);
        expected.add(32);
        expected.add(33);

        assertEquals(expected, avlTree.findPathBetween(25, 33));

        expected = new ArrayList<>();
        expected.add(33);

        assertEquals(expected, avlTree.findPathBetween(33, 33));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionFPBNullStart() {
        avlTree.findPathBetween(null, 35);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionFPBNullEnd() {
        avlTree.findPathBetween(35, null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void exceptionFPBNoStart() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);

        avlTree.findPathBetween(10, 70);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void exceptionFPBNoEnd() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);

        avlTree.findPathBetween(50, 100);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionContainsNull() {
        avlTree.contains(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionAddNull() {
        avlTree.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionRemoveNull() {
        avlTree.add(50);
        avlTree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void exceptionRemoveNotInTree() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);

        avlTree.remove(23);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void exceptionGetNotInTree() {
        avlTree.add(50);
        avlTree.add(20);
        avlTree.add(60);
        avlTree.add(40);
        avlTree.add(30);
        avlTree.add(70);

        avlTree.get(10);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionGetNull() {
        avlTree.add(50);
        avlTree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionDepthNull() {
        avlTree.add(50);
        avlTree.depth(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void exceptionDepthNotInTree() {
        avlTree.add(50);
        avlTree.depth(40);
    }


    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        Integer first = new Integer(1);
        Integer second = new Integer(1);
        avlTree.add(first);
        assertEquals((Integer) 1, avlTree.getRoot().getData());
        assertEquals(1, avlTree.size());
        assertEquals(0, avlTree.height());
        assertNull(avlTree.getRoot().getLeft());
        assertNull(avlTree.getRoot().getRight());

        avlTree.add(second);
        assertEquals((Integer) 1, avlTree.getRoot().getData());
        assertEquals(1, avlTree.size());
        assertEquals(0, avlTree.height());
        assertNull(avlTree.getRoot().getLeft());
        assertNull(avlTree.getRoot().getRight());
        assertSame(first, avlTree.getRoot().getData());

        avlTree.add(-1);
        avlTree.add(2);
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());

        avlTree.add(second);
        assertNull(avlTree.getRoot().getLeft().getLeft());
        assertNull(avlTree.getRoot().getLeft().getRight());
        assertNull(avlTree.getRoot().getRight().getLeft());
        assertNull(avlTree.getRoot().getRight().getRight());
        assertSame(first, avlTree.getRoot().getData());
        assertEquals(3, avlTree.size());
        assertEquals(1, avlTree.height());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionCollectionNull() {
        avlTree = new AVL<>(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void exceptionCollectionNullData() {
        ArrayList<Integer> col = new ArrayList<>();
        col.add(50);
        col.add(null);
        col.add(30);
        avlTree = new AVL<>(col);
    }
}