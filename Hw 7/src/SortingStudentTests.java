import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.omg.CORBA.TIMEOUT;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;
/**
 * Basic student tests to check sorting algorithms. These tests are in no way comprehensive nor do they guarantee
 * any kind of grade. Also, most of these upper bounds are not strict; you can probably implement your algorithm to
 * give you fewer comparisons than we have given for most of these
 *
 * @version 3.2
 * @author CS 1332 TAs
 * @author Grayson Bianco   gbianco6
 */

public class SortingStudentTests {

    private static final int TIMEOUT = 500;
    private Integer[] unsorted;
    private Integer[] sorted;
    private Comparator<Integer> cmp;

    @Before
    public void setUp() {
        unsorted = new Integer[] {5, 7, 3, 6, 8};
        sorted = new Integer[] {3, 5, 6, 7, 8};
        cmp = new ComparatorPlusPlus<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                increaseCount();
                if (a < b) {
                    return -1;
                } else if (a == b) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
    }

    /**
     * BubbleSort:
     * In-Place
     * Stable
     */
    @Test(timeout = TIMEOUT)
    public void testBubbleSortTA() {
        System.out.println("Bubble Sort Test TA: ");
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(unsorted));
        Sorting.bubbleSort(unsorted, cmp);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(unsorted));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(sorted));
        assertArrayEquals(sorted, unsorted);
        System.out.println("\tMaximum comparison count: 10");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 10);
        System.out.println("Bubble Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortCaseAverage() {
        Integer first4 = new Integer(4);
        Integer second4 = new Integer(4);
        Integer first27 = new Integer(27);
        Integer second27 = new Integer(27);
        Integer first38 = new Integer(38);
        Integer second38 = new Integer(38);
        Integer[] myArr = {24, first27, first4, 46, second27, 43, second4,
                15, 1, 28, 48, 14, first38, second38, 7, 23};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, first4, second4, 7, 14, 15, 23, 24, first27,
                second27, 28, first38, second38, 43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.bubbleSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count:   120");
        System.out.println("\tYour comparison count:      "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 120);
        System.out.println("Bubble Sort Test Average Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortCaseWorst() {
        System.out.println("Bubble Sort Test Worst: ");
        Integer[] myArr = {48, 46, 43, 38, 38, 28, 27, 27, 24, 23, 15, 14, 7,
                4, 4, 1};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.bubbleSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count:   120");
        System.out.println("\tYour comparison count:      "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 120);
        System.out.println("Bubble Sort Test Worst Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortCaseBest() {
        System.out.println("Bubble Sort Test Best: ");
        Integer[] myArr = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.bubbleSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count:   15");
        System.out.println("\tYour comparison count:      "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 15);
        System.out.println("Bubble Sort Test Best Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortEmptyOr1() {
        System.out.println("Bubble Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        Integer[] myArr = {};
        assertEquals(0, myArr.length);
        Integer[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.bubbleSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new Integer[]{1};
        correct = new Integer[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.bubbleSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Bubble Sort Test Empty/1 Passed!\n\n");
    }

    /**
     * InsertionSort:
     * In-place
     * Stable
     */
    @Test(timeout = TIMEOUT)
    public void testInsertionSortTA() {
        System.out.println("Insertion Sort Test TA:");
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(unsorted));
        Sorting.insertionSort(unsorted, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(unsorted));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(sorted));
        assertArrayEquals(sorted, unsorted);
        System.out.println("\tMaximum comparison count: 8");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 8);
        System.out.println("Insertion Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortCaseAverage() {
        System.out.println("Insertion Sort Test Average:");
        Integer first4 = new Integer(4);
        Integer second4 = new Integer(4);
        Integer first27 = new Integer(27);
        Integer second27 = new Integer(27);
        Integer first38 = new Integer(38);
        Integer second38 = new Integer(38);
        Integer[] myArr = {24, first27, first4, 46, second27, 43, second4,
                15, 1, 28, 48, 14, first38, second38, 7, 23};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, first4, second4, 7, 14, 15, 23, 24, first27,
                second27, 28, first38, second38, 43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.insertionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 71");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 71);
        System.out.println("Insertion Sort Test Average Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortCaseBest() {
        System.out.println("Insertion Sort Test Best: ");
        Integer[] myArr = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.insertionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count:   15");
        System.out.println("\tYour comparison count:      "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 15);
        System.out.println("Insertion Sort Test Best Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortCaseWorst() {
        System.out.println("Insertion Sort Test Worst:");
        Integer[] myArr = {48, 46, 43, 38, 38, 28, 27, 27, 24, 23, 15, 14, 7,
                4, 4, 1};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.insertionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 120");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 120);
        System.out.println("Insertion Sort Test Worst Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortEmptyOr1() {
        System.out.println("Insertion Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        Integer[] myArr = {};
        assertEquals(0, myArr.length);
        Integer[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.insertionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new Integer[]{1};
        correct = new Integer[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.insertionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Insertion Sort Test Empty/1 Passed!\n\n");
    }

    /**
     * SelectionSort:
     * In-place
     * Unstable
     */
    @Test(timeout = TIMEOUT)
    public void testSelectionSortTA() {
        System.out.println("Selection Sort Test TA:");
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(unsorted));
        Sorting.selectionSort(unsorted, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(unsorted));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(sorted));
        assertArrayEquals(sorted, unsorted);
        System.out.println("\tMaximum comparison count: 10");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 10);
        System.out.println("Selection Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortAll() {
        System.out.println("Selection Sort Test All:");
        Integer[] myArr = {24, 27, 4, 46, 27, 43, 4, 15, 1, 28,
                48, 14, 38, 38, 7, 23};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.selectionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 120");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 120);
        System.out.println("Selection Sort Test All Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortEmptyOr1() {
        System.out.println("Selection Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        Integer[] myArr = {};
        assertEquals(0, myArr.length);
        Integer[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.selectionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new Integer[]{1};
        correct = new Integer[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.selectionSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Selection Sort Test Empty/1 Passed!\n\n");
    }

    /**
     * MergeSort:
     * Out-of-place
     * Stable
     */
    @Test(timeout = TIMEOUT)
    public void testMergeSortTA() {
        System.out.println("Merge Sort Test TA:");
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(unsorted));
        Sorting.mergeSort(unsorted, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(unsorted));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(sorted));
        assertArrayEquals(sorted, unsorted);
        System.out.println("\tMaximum comparison count: 8");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 8);
        System.out.println("Merge Sort Test TA Passed!\n\n");
    }


    @Test(timeout = TIMEOUT)
    public void testMergeSortCaseAverage() {
        System.out.println("Merge Sort Test Average:");
        Integer first4 = new Integer(4);
        Integer second4 = new Integer(4);
        Integer first27 = new Integer(27);
        Integer second27 = new Integer(27);
        Integer first38 = new Integer(38);
        Integer second38 = new Integer(38);
        Integer[] myArr = {24, first27, first4, 46, second27, 43, second4,
                15, 1, 28, 48, 14, first38, second38, 7};
        assertEquals(15, myArr.length);
        Integer[] correct = {1, first4, second4, 7, 14, 15, 24, first27,
                second27, 28, first38, second38, 43, 46, 48};
        assertEquals(15, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.mergeSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 43");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 43);
        System.out.println("Merge Sort Test Average Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortCaseWorst() {
        System.out.println("Merge Sort Test Worst:");
        Integer[] myArr = {8, 4, 6, 2, 10, 7, 3, 5, 1, 9};
        assertEquals(10, myArr.length);
        Integer[] correct = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(10, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.mergeSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 26");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 26);
        System.out.println("Merge Sort Test Worst Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortCaseBest() {
        System.out.println("Merge Sort Test Best:");
        Integer[] myArr = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.mergeSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 32");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 32);
        System.out.println("Merge Sort Test Best Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortEmptyOr1() {
        System.out.println("Merge Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        Integer[] myArr = {};
        assertEquals(0, myArr.length);
        Integer[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.mergeSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new Integer[]{1};
        correct = new Integer[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.mergeSort(myArr, cmp);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Merge Sort Test Empty/1 Passed!\n\n");
    }
    /**
     * QuickSort:
     * In-place
     * Unstable
     */
    @Test(timeout = TIMEOUT)
    public void testQuickSortTA() {
        System.out.println("Quick Sort Test TA: ");
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(unsorted));
        Sorting.quickSort(unsorted, cmp, new Random(0x53476da));
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(unsorted));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(sorted));
        assertArrayEquals(sorted, unsorted);
        System.out.println("\tMaximum comparison count: 10");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 10);
        System.out.println("Quick Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortCheckInfiniteLoopEdgeCase() {
        System.out.println("Quick Sort Test TA: ");
        Integer[] myArr = {2, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        Integer[] correct = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6};
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random(4096));
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 64");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 64);
        System.out.println("Quick Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortCaseAverage() {
        System.out.println("Quick Sort Test Average: ");
        Integer[] myArr = {24, 27, 4, 46, 27, 43, 4, 15, 1, 28};
        assertEquals(10, myArr.length);
        Integer[] correct = {1, 4, 4, 15, 24, 27, 27, 28, 43, 46};
        assertEquals(10, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random(0x53476da));
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 28");
        // Comparison calculation
        // T(n)=nL-2^L+L; L = # of levels, n = # of elements
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 28);
        System.out.println("Quick Sort Test Average Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortCaseBest() {
        System.out.println("Quick Sort Test Best: ");
        Integer[] myArr = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, myArr.length);
        Integer[] correct = {1, 4, 4, 7, 14, 15, 23, 24, 27, 27, 28, 38, 38,
                43, 46, 48};
        assertEquals(16, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random(441287210));
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 64");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 64);
        System.out.println("Quick Sort Test Best Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortCaseWorst() {
        System.out.println("Quick Sort Test Worst: ");
        Integer[] myArr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        assertEquals(10, myArr.length);
        Integer[] correct = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(10, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random(53520));
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 45");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() <= 45);
        System.out.println("Quick Sort Test Best Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortEmptyOr1() {
        System.out.println("Quick Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        Integer[] myArr = {};
        assertEquals(0, myArr.length);
        Integer[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random());
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new Integer[]{1};
        correct = new Integer[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.quickSort(myArr, cmp, new Random());
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Quic Sort Test Empty/1 Passed!\n\n");
    }

    /**
     * LSDRadixSort:
     * Out-of-place
     * Stable
     */
    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortTA() {
        System.out.println("LSD Radix Sort Test TA: ");
        int[] myArr = {5, 7, 3, 6, 8};
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(new int[] {3, 5, 6, 7, 8}));
        assertArrayEquals(new int[] {3, 5, 6, 7, 8}, myArr);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);
        System.out.println("LSD Radix Sort Test TA Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortAll() {
        System.out.println("LSD Radix Sort Test All: ");
        int[] myArr = {1334, 3239, 5004, 1602, 3337, 785, 2155, 9993, 4715,
                2504, 7224, 2910, 5694};
        assertEquals(13, myArr.length);
        int[] correct = {785, 1334, 1602, 2155, 2504, 2910, 3239, 3337, 4715,
                5004, 5694, 7224, 9993};
        assertEquals(13, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);
        System.out.println("LSD Radix Sort Test All Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortAll2() {
        System.out.println("LSD Radix Sort Test All: ");
        int f1334 = 1334;
        int s1334 = 1334;
        int f3337 = -3337;
        int s3337 = -3337;
        int[] myArr = {f1334, 3239, 5004, s1334, 4715, 785, 2155, 9993, f3337,
                2504, 7224, s3337, 2910, 5694};
        assertEquals(14, myArr.length);
        int[] correct = {f3337, s3337, 785, f1334, s1334, 2155, 2504, 2910,
                3239, 4715, 5004, 5694, 7224, 9993};
        assertEquals(14, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);
        System.out.println("LSD Radix Sort Test All Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortWithNegatives() {
        System.out.println("LSD Radix Sort Test Negatives: ");
        int[] myArr = {5, 7, -3, 6, 8, -103, 36, -14, 0};
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(new int[] {-103, -14, -3, 0, 5, 6, 7, 8, 36}));
        assertArrayEquals(new int[] {-103, -14, -3, 0, 5, 6, 7, 8, 36}, myArr);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);
        System.out.println("LSD Radix Sort Test Negatives Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortAllNegatives() {
        System.out.println("LSD Radix Sort Test All Negatives: ");
        int[] myArr = {-1334, -3239, -5004, -1602, -3337, -785, -2155, -9993,
                -4715, -2504, -7224, -2910, -5694};
        assertEquals(13, myArr.length);
        int[] correct = {-9993, -7224, -5694, -5004, -4715, -3337, -3239,
                -2910, -2504, -2155, -1602, -1334, -785};
        assertEquals(13, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t"
                + Arrays.toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(correct, myArr);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);
        System.out.println("LSD Radix Sort Test All Negatives Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void testLSDRadixSortEmptyOr1() {
        System.out.println("LSD Radix Sort Test Empty/1:");
        System.out.println("Now checking size 0:");
        int[] myArr = {};
        assertEquals(0, myArr.length);
        int[] correct = {};
        assertEquals(0, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("Now checking size 1:");

        myArr = new int[]{1};
        correct = new int[]{1};
        assertEquals(1, myArr.length);
        assertEquals(1, correct.length);
        System.out.println("\tYour original Array: \t"
                + Arrays.toString(myArr));
        Sorting.lsdRadixSort(myArr);
        System.out.println("\tYour sorted Array:   \t" + Arrays
                .toString(myArr));
        System.out.println("\tCorrect Array:       \t"
                + Arrays.toString(correct));
        assertArrayEquals(myArr, correct);
        System.out.println("\tMaximum comparison count: 0");
        System.out.println("\tYour comparison count:    "
                + ((ComparatorPlusPlus<Integer>) cmp).getCount());
        assertTrue(((ComparatorPlusPlus<Integer>) cmp).getCount() == 0);

        System.out.println("LSD Radix Sort Test Empty/1 Passed!\n\n");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsBubbleSort() {
        try {
            Sorting.bubbleSort(null, cmp);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
        try {
            Sorting.bubbleSort(unsorted, null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Comparator.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Comparator.", false);
        }
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsInsertionSort() {
        try {
            Sorting.insertionSort(null, cmp);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
        try {
            Sorting.insertionSort(unsorted, null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Comparator.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Comparator.", false);
        }
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsSelectionSort() {
        try {
            Sorting.selectionSort(null, cmp);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
        try {
            Sorting.selectionSort(unsorted, null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Comparator.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Comparator.", false);
        }
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsQuickSort() {
        try {
            Sorting.quickSort(null, cmp, new Random());
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
        try {
            Sorting.quickSort(unsorted, null, new Random());
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Comparator.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Comparator.", false);
        }
        try {
            Sorting.quickSort(unsorted, cmp, null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Random.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Random.", false);
        }
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsMergeSort() {
        try {
            Sorting.mergeSort(null, cmp);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
        try {
            Sorting.mergeSort(unsorted, null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null Comparator.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null "
                    + "Comparator.", false);
        }
    }

    @Test(timeout = TIMEOUT)
    public void exceptionsLSDRadixSort() {
        try {
            Sorting.lsdRadixSort(null);
            assertTrue("Didn't throw IllegalArgumentException for passing in "
                    + "a null array.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            assertTrue("Threw wrong exception for passing in a null array.",
                    false);
        }
    }


    private abstract class ComparatorPlusPlus<Integer> implements Comparator<Integer> {
        private int count;

        public int getCount() {
            return count;
        }

        public void increaseCount() {
            count++;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}