import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Basic student tests to check pattern matching algorithms. These tests are in
 * no way comprehensive nor do they guarantee any kind of grade.
 * The number of comparisons in these tests are exact.
 *
 * @version 1.1
 * @author CS 1332 TAs
 * @author gbianco6
 */

public class PatternMatchingStudentTests {

    private static final int TIMEOUT = 500;
    private CharSequence text;
    private CharSequence pattern;
    private CharSequence text2;
    private CharSequence pattern2;
    private CharSequence text3;
    private CharSequence pattern3;
    private CharSequence text4;
    private CharSequence pattern4;
    private CharSequence text5;
    private CharSequence pattern5;

    @Before
    public void setUp() {
        pattern = new SearchableString("ardvark");
        text = new SearchableString(
                "ardenlovestheardvarksandardvarkslovesardvarklovingarden");

        pattern2 = new SearchableString("pi");
        text2 = new SearchableString("peter_piper_picked_a_peck_of_pickled_"
                + "peppers");

        pattern3 = new SearchableString("aah");
        text3 = new SearchableString("aaaaaaaaaah");

        pattern4 = new SearchableString("abab");
        text4 = new SearchableString("ababababababababab");

        pattern5 = new SearchableString("a");
        text5 = new SearchableString("aaaaaaaaaaaa");
    }

    @Test(timeout = TIMEOUT)
    public void testBruteForce1() {
        System.out.println("Testing BruteForce 1: ");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern, text);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(13);
        expectedResult.add(24);
        expectedResult.add(37);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt Count: 77");

        assertEquals(expectedResult, actualResult);
        assertEquals(77, ((SearchableString) text).getCount());

        System.out.println("Finished BruteForce 1!\n");

    }

    @Test
    public void testBruteForce2() {
        System.out.println("Testing BruteForce 2: ");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern2,
                text2);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(6);
        expectedResult.add(12);
        expectedResult.add(29);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text2).getCount());
        System.out.println("\tExpected charAt Count: 52");

        assertEquals(expectedResult, actualResult);
        assertEquals(52, ((SearchableString) text2).getCount());

        System.out.println("Finished BruteForce 2!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testBruteForce3() {
        System.out.println("Testing BruteForce 3: ");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern3,
                text3);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(8);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text3).getCount());
        System.out.println("\tExpected charAt Count: 27");

        assertEquals(expectedResult, actualResult);
        assertEquals(27, ((SearchableString) text3).getCount());

        System.out.println("Finished BruteForce 3!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testBruteForce4() {
        System.out.println("Testing BruteForce 4: ");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern4,
                text4);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i <= 14; i += 2) {
            expectedResult.add(i);
        }

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text4).getCount());
        System.out.println("\tExpected charAt Count: 39");

        assertEquals(expectedResult, actualResult);
        assertEquals(39, ((SearchableString) text4).getCount());

        System.out.println("Finished BruteForce 4!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testBruteForce5() {
        System.out.println("Testing BruteForce 5: ");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern5,
                text5);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < text5.length(); i++) {
            expectedResult.add(i);
        }

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text5).getCount());
        System.out.println("\tExpected charAt Count: 12");

        assertEquals(expectedResult, actualResult);
        assertEquals(12, ((SearchableString) text5).getCount());

        System.out.println("Finished BruteForce 5!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testBruteForcePatternLonger() {
        System.out.println("Testing Brute Force Pattern > Text: ");
        pattern = new SearchableString("abcde");
        text = new SearchableString("abc");
        List<Integer> actualResult = PatternMatching.bruteForce(pattern, text);
        List<Integer> expectedResult = new java.util.ArrayList<>();
        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());
        assertEquals(expectedResult, actualResult);
        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt Count: 0");
        assertTrue(actualResult.isEmpty());
        assertEquals(0, ((SearchableString) text).getCount());
        System.out.println("Finished Brute Force Pattern > Text!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore1() {
        System.out.println("Testing Boyer Moore 1: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern, text);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(13);
        expectedResult.add(24);
        expectedResult.add(37);

        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt Count: 32");

        assertEquals(expectedResult, actualResult);
        assertEquals(32, ((SearchableString) text).getCount());

        System.out.println("Finished Boyer Moore 1!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore2() {
        System.out.println("Testing Boyer Moore 2: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern2,
                text2);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(6);
        expectedResult.add(12);
        expectedResult.add(29);

        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text2).getCount());
        System.out.println("\tExpected charAt Count: 28");

        assertEquals(expectedResult, actualResult);
        assertEquals(28, ((SearchableString) text2).getCount());

        System.out.println("Finished Boyer Moore 2!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore3() {
        System.out.println("Testing Boyer Moore 3: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern3,
                text3);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(8);

        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text3).getCount());
        System.out.println("\tExpected charAt Count: 11");

        assertEquals(expectedResult, actualResult);
        assertEquals(11, ((SearchableString) text3).getCount());

        System.out.println("Finished Boyer Moore 3!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore4() {
        System.out.println("Testing Boyer Moore 4: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern4,
                text4);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i <= 14; i += 2) {
            expectedResult.add(i);
        }

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text4).getCount());
        System.out.println("\tExpected charAt Count: 39");

        assertEquals(expectedResult, actualResult);
        assertEquals(39, ((SearchableString) text4).getCount());

        System.out.println("Finished Boyer Moore 4!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore5() {
        System.out.println("Testing Boyer Moore 5: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern5,
                text5);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < text5.length(); i++) {
            expectedResult.add(i);
        }

        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text5).getCount());
        System.out.println("\tExpected charAt Count: 12");

        assertEquals(expectedResult, actualResult);
        assertEquals(12, ((SearchableString) text5).getCount());

        System.out.println("Finished Boyer Moore 5!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoorePatternLonger() {
        System.out.println("Testing Boyer Moore Pattern > Text: ");
        pattern = new SearchableString("abcde");
        text = new SearchableString("abc");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern, text);
        List<Integer> expectedResult = new java.util.ArrayList<>();
        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());
        assertEquals(expectedResult, actualResult);
        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt Count: 0");
        assertTrue(actualResult.isEmpty());
        assertEquals(0, ((SearchableString) text).getCount());
        System.out.println("Finished Boyer Moore Pattern > Text!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable1() {
        Map<Character, Integer> actualTable =
                PatternMatching.buildLastTable(pattern);
        Map<Character, Integer> expectedTable = new HashMap<>();
        expectedTable.put('a', 4);
        expectedTable.put('r', 5);
        expectedTable.put('d', 2);
        expectedTable.put('v', 3);
        expectedTable.put('k', 6);

        System.out.println("Testing BuildLastTable 1: ");
        System.out.println("\tYour table:     "
                + actualTable.toString());
        System.out.println("\tExpected table: "
                + expectedTable.toString());
        assertEquals(expectedTable, actualTable);
        assertEquals(0, ((SearchableString) text).getCount());

        System.out.println("Finished BuildLastTable 1!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable2() {
        System.out.println("Testing BuildLastTable 2: ");
        pattern = new SearchableString("abra");
        System.out.println("\t" + pattern);
        Map<Character, Integer> actualTable =
                PatternMatching.buildLastTable(pattern);
        Map<Character, Integer> expectedTable = new HashMap<>();
        expectedTable.put('a', 3);
        expectedTable.put('b', 1);
        expectedTable.put('r', 2);

        System.out.println("\t\tYour table:     "
                + actualTable.toString());
        System.out.println("\t\tExpected table: "
                + expectedTable.toString());
        assertEquals(expectedTable, actualTable);

        pattern = new SearchableString("kadabra");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildLastTable(pattern);
        expectedTable = new HashMap<>();
        expectedTable.put('k', 0);
        expectedTable.put('a', 6);
        expectedTable.put('d', 2);
        expectedTable.put('b', 4);
        expectedTable.put('r', 5);

        System.out.println("\t\tYour table:     "
                + actualTable.toString());
        System.out.println("\t\tExpected table: "
                + expectedTable.toString());
        assertEquals(expectedTable, actualTable);

        pattern = new SearchableString("alakazam");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildLastTable(pattern);
        expectedTable = new HashMap<>();
        expectedTable.put('a', 6);
        expectedTable.put('l', 1);
        expectedTable.put('k', 3);
        expectedTable.put('z', 5);
        expectedTable.put('m', 7);

        System.out.println("\t\tYour table:     "
                + actualTable.toString());
        System.out.println("\t\tExpected table: "
                + expectedTable.toString());
        assertEquals(expectedTable, actualTable);

        System.out.println("Finished BuildLastTable 2!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testKmp1() {
        pattern = new SearchableString("abacab");
        text = new SearchableString("abacacababacabacabadabacab");
        System.out.println("Testing KMP 1: ");
        List<Integer> actualResult = PatternMatching.kmp(pattern, text);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(8);
        expectedResult.add(12);
        expectedResult.add(20);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());
        assertEquals(expectedResult, actualResult);
        System.out.println("\tYour charAt count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt count: 31");
        assertEquals(31, ((SearchableString) text).getCount());
        System.out.println("Finished KMP 1!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testKmp2() {
        System.out.println("Testing KMP 2: ");
        List<Integer> actualResult = PatternMatching.kmp(pattern2, text2);

        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(6);
        expectedResult.add(12);
        expectedResult.add(29);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());
        assertEquals(expectedResult, actualResult);
        System.out.println("\tYour charAt count:     "
                + ((SearchableString) text2).getCount());
        System.out.println("\tExpected charAt count: 49");
        assertEquals(49, ((SearchableString) text2).getCount());
        System.out.println("Finished KMP 1!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testKMP3() {
        System.out.println("Testing KMP 3: ");
        List<Integer> actualResult = PatternMatching.kmp(pattern3,
                text3);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(8);

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text3).getCount());
        System.out.println("\tExpected charAt Count: 19");

        assertEquals(expectedResult, actualResult);
        assertEquals(19, ((SearchableString) text3).getCount());

        System.out.println("Finished KMP 3!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testKMP4() {
        System.out.println("Testing KMP 4: ");
        List<Integer> actualResult = PatternMatching.kmp(pattern4,
                text4);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i <= 14; i += 2) {
            expectedResult.add(i);
        }

        System.out.println("\tYour result:     " + actualResult.toString());
        System.out.println("\tExpected result: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text4).getCount());
        System.out.println("\tExpected charAt Count: 18");

        assertEquals(expectedResult, actualResult);
        assertEquals(18, ((SearchableString) text4).getCount());

        System.out.println("Finished KMP 4!\n");

    }

    @Test(timeout = TIMEOUT)
    public void testKMP5() {
        System.out.println("Testing KMP 5: ");
        List<Integer> actualResult = PatternMatching.boyerMoore(pattern5,
                text5);
        List<Integer> expectedResult = new ArrayList<>();
        for (int i = 0; i < text5.length(); i++) {
            expectedResult.add(i);
        }

        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());

        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text5).getCount());
        System.out.println("\tExpected charAt Count: 12");

        assertEquals(expectedResult, actualResult);
        assertEquals(12, ((SearchableString) text5).getCount());

        System.out.println("Finished KMP 5!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testKMPPatternLonger() {
        System.out.println("Testing KMP Pattern > Text: ");
        pattern = new SearchableString("abcde");
        text = new SearchableString("abc");
        List<Integer> actualResult = PatternMatching.kmp(pattern, text);
        List<Integer> expectedResult = new java.util.ArrayList<>();
        System.out.println("\tYour results:     " + actualResult.toString());
        System.out.println("\tExpected results: " + expectedResult.toString());
        assertEquals(expectedResult, actualResult);
        System.out.println("\tYour charAt Count:     "
                + ((SearchableString) text).getCount());
        System.out.println("\tExpected charAt Count: 0");
        assertTrue(actualResult.isEmpty());
        assertEquals(0, ((SearchableString) text).getCount());
        System.out.println("Finished KMP Pattern > Text!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable1() {
        pattern = new SearchableString("abacab");
        int[] actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable = {0, 0, 1, 0, 1, 2};
        System.out.println("Testing BuildFailureTable 1: ");
        System.out.println("\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\tExpected table: "
                + Arrays.toString(expectedTable));
        assertArrayEquals(expectedTable, actualTable);
        System.out.println("Finished BuildFailureTable 1!\n");
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable2() {
        System.out.println("Testing BuildFailureTable 2: ");
        pattern = new SearchableString("AABAACAABAA");
        System.out.println("\t" + pattern);
        int[] actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable = {0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5};
        System.out.println("\t\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\t\tExpected table: "
                + Arrays.toString(expectedTable));
        assertArrayEquals(expectedTable, actualTable);

        pattern = new SearchableString("ABCDE");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable2 = {0, 0, 0, 0, 0};
        System.out.println("\t\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\t\tExpected table: "
                + Arrays.toString(expectedTable2));
        assertArrayEquals(expectedTable2, actualTable);

        pattern = new SearchableString("AAAAA");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable3 = {0, 1, 2, 3, 4};
        System.out.println("\t\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\t\tExpected table: "
                + Arrays.toString(expectedTable3));
        assertArrayEquals(expectedTable3, actualTable);

        pattern = new SearchableString("AAABAAA");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable4 = {0, 1, 2, 0, 1, 2, 3};
        System.out.println("\t\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\t\tExpected table: "
                + Arrays.toString(expectedTable4));
        assertArrayEquals(expectedTable4, actualTable);

        pattern = new SearchableString("AAACAAAAAC");
        System.out.println("\t" + pattern);
        actualTable = PatternMatching.buildFailureTable(pattern);
        int[] expectedTable5 = {0, 1, 2, 0, 1, 2, 3, 3, 3, 4};
        System.out.println("\t\tYour table:     "
                + Arrays.toString(actualTable));
        System.out.println("\t\tExpected table: "
                + Arrays.toString(expectedTable5));
        assertArrayEquals(expectedTable5, actualTable);

        System.out.println("Finished BuildFailureTable 2!\n");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionBruteForce() {
        boolean stillGood = true;
        System.out.println("Testing Brute Force exceptions: ");
        try {
            pattern = null;
            PatternMatching.bruteForce(pattern, text);
            System.out.println("\tNull Pattern:  Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Pattern:  Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Pattern:  Incorrect Exception");
            stillGood = false;
        }

        try {
            pattern = "";
            PatternMatching.bruteForce(pattern, text);
            System.out.println("\tEmpty Pattern: Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tEmpty Pattern: Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tEmpty Pattern: Incorrect Exception");
            stillGood = false;
        }

        try {
            text = null;
            PatternMatching.bruteForce(pattern, text);
            System.out.println("\tNull Text:     Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Text:     Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Text:     Incorrect Exception");
            stillGood = false;
        }
        assertTrue(stillGood);
        System.out.println("Finished Brute Force exceptions!");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionBoyerMoore() {
        boolean stillGood = true;
        System.out.println("Testing Brute Force exceptions: ");
        try {
            pattern = null;
            PatternMatching.boyerMoore(pattern, text);
            System.out.println("\tNull Pattern:  Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Pattern:  Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Pattern:  Incorrect Exception");
            stillGood = false;
        }

        try {
            pattern = "";
            PatternMatching.boyerMoore(pattern, text);
            System.out.println("\tEmpty Pattern: Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tEmpty Pattern: Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tEmpty Pattern: Incorrect Exception");
            stillGood = false;
        }

        try {
            text = null;
            PatternMatching.boyerMoore(pattern, text);
            System.out.println("\tNull Text:     Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Text:     Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Text:     Incorrect Exception");
            stillGood = false;
        }
        assertTrue(stillGood);
        System.out.println("Finished Brute Force exceptions!");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionKMP() {
        boolean stillGood = true;
        System.out.println("Testing Brute Force exceptions: ");
        try {
            pattern = null;
            PatternMatching.kmp(pattern, text);
            System.out.println("\tNull Pattern:  Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Pattern:  Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Pattern:  Incorrect Exception");
            stillGood = false;
        }

        try {
            pattern = "";
            PatternMatching.kmp(pattern, text);
            System.out.println("\tEmpty Pattern: Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tEmpty Pattern: Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tEmpty Pattern: Incorrect Exception");
            stillGood = false;
        }

        try {
            text = null;
            PatternMatching.kmp(pattern, text);
            System.out.println("\tNull Text:     Did not throw an Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Text:     Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Text:     Incorrect Exception");
            stillGood = false;
        }
        assertTrue(stillGood);
        System.out.println("Finished Brute Force exceptions!");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionBuildLastTable() {
        System.out.println("Testing Build Last Table exceptions: ");
        boolean stillGood = true;
        try {
            PatternMatching.buildLastTable(null);
            System.out.println("\tNull Pattern:     Did not throw an "
                    + "Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Pattern:     Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Pattern:     Incorrect Exception");
            stillGood = false;
        }
        assertTrue(stillGood);
        System.out.println("Finished Build Last Table exceptions!");
    }

    @Test(timeout = TIMEOUT)
    public void exceptionBuildFailureTable() {
        System.out.println("Testing Build Failure Table exceptions: ");
        boolean stillGood = true;
        try {
            PatternMatching.buildFailureTable(null);
            System.out.println("\tNull Pattern:     Did not throw an "
                    + "Exception");
            stillGood = false;
        } catch (IllegalArgumentException e) {
            System.out.println("\tNull Pattern:     Correct Exception");
        } catch (Throwable e) {
            System.out.println("\tNull Pattern:     Incorrect Exception");
            stillGood = false;
        }
        assertTrue(stillGood);
        System.out.println("Finished Build Failure Table exceptions!");
    }
}