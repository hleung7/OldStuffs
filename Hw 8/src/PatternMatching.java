import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Your implementations of various pattern matching algorithms.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class PatternMatching {

    /**
     * Brute Force Algorithm compares a patern with the text for each possible
     * shift of pattern with respect to the text.
     *
     * Runtime: O(nm) where n is the size of text and m is the size of pattern
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> bruteForce(CharSequence pattern,
                                           CharSequence text) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        } else if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern length is 0.");
        } else if (text == null) {
            throw new IllegalArgumentException("Text is null.");
        }
        int pl = pattern.length();
        int tl = text.length();
        List<Integer> patternList = new ArrayList<Integer>();

        for (int i = 0; i <= tl - pl; i++) {
            boolean same = true;
            for (int j = i; (same && (j < (pl + i))); j++) {
                if ((text.charAt(j)) != (pattern.charAt(j - i))) {
                    same = false;
                }
            }
            if (same) {
                patternList.add(i);
            }
        }
        return patternList;
    }
    /**
     * Boyer Moore algorithm that uses a last table. Works better with large
     * alphabets.
     *
     * Runtime : O(nm + s) where n is the size of text, m is the size of pattern
     * and s is the size of alphabet
     *
     * NOTE: Make sure to implement {@code buildLastTable} before
     * implementing this method.
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * if text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text) {

        if (pattern == null) {
            throw new IllegalArgumentException("Can't use 0/null pattern.");
        } else if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern is 0length.");
        } else if (text == null) {
            throw new IllegalArgumentException("Text is null.");
        }

        List<Integer> list = new ArrayList<Integer>();
        int pl = pattern.length();
        int tl = text.length();
        int i = pl - 1;
        int j = pl - 1;

        if ((tl == 0) || (pl > tl)) {
            return list;
        }

        Map<Character, Integer> table = buildLastTable(pattern);

        while (i < tl) {
            char oldChar = text.charAt(i);
            if (oldChar == pattern.charAt(j)) {
                if (j == 0) {
                    list.add(i);
                    i += pl;
                    j = pl - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                int fix = 0;
                if (table.get(oldChar) == null) {
                    fix = -1;
                } else {
                    fix = table.get(oldChar);
                }
                j = pl - 1;
                i += pl - Math.min(j, (fix + 1));
            }
        }
        return list;
    }
    /**
     * Builds last occurrence table for the Boyer Moore algorithm.
     *
     * NOTE : each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x
     * and you will have to check for that in your BoyerMoore
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Can't have null pattern.");
        }
        Map<Character, Integer> lTable = new HashMap<Character, Integer>();
        int pl = pattern.length();
        for (int i = 0; i < pl; i++) {
            char c = pattern.charAt(i);
            if (lTable.containsKey(c)) {
                lTable.put(c, i);
            } else {
                lTable.put(c, i);
            }
        }
        return lTable;
    }

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Runtime: O(m+n) where n is the size of the text and m is the size of the
     * pattern
     * NOTE: Make sure to implement {@code buildFailureTable} before
     * implementing this method.
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * if text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern is null or length 0.");
        } else if (text == null) {
            throw new IllegalArgumentException("The text is null");
        }
        int[] failureTable = buildFailureTable(pattern);
        int patternIndex = 0;
        int index = 0;
        ArrayList<Integer> patternList = new ArrayList<Integer>();
        while (index + (pattern.length() - patternIndex - 1) < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(index)) {
                if (patternIndex == pattern.length() - 1) {
                    patternList.add(index - (pattern.length() - 1));
                    patternIndex++;
                    index++;
                    patternIndex = failureTable[patternIndex - 1];
                } else {
                    patternIndex++;
                    index++;
                }
            } else if (patternIndex > 0) {
                patternIndex = failureTable[patternIndex - 1];
            } else if (patternIndex == 0)  {
                index++;
            }
        }
        return patternList;
    }
    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * A given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building failure table for
     * @return integer array of size text.length that you are building a failure
     * table for
     */
    public static int[] buildFailureTable(CharSequence pattern) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null.");
        }
        int i = 1;
        int j = 0;
        int pl = pattern.length();
        int[] fTable = new int[pl];
        while (i < pl) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                fTable[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = fTable[j - 1];
            } else {
                i++;
            }
        }
        return fTable;
    }

}
