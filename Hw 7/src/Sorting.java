import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Henry Leung
 * @version 1.0
 */
public class Sorting {

    /**
     * Your implementation of bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Array/Comparator is null.");
        } else {
        /*
         * use bool to make sure we aren't swapping too many times
         */
            boolean hasSwapped = true;
            for (int i = (arr.length - 1); (hasSwapped && (i >= 0)); i--) {
                hasSwapped = false;
                for (int j = 0; j < i; j++) {
                    // if the (j+1)th element is greater than the jth, swap.
                    if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                        T temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        hasSwapped = true;
                    }
                }
            }
        }
    }
    /**7
     * Your implementation of insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Array/Comparator is null.");
        } else {
            for (int i = 0; i < arr.length; i++) {
                int j = i - 1;
                T temp = arr[i];
                while ((j >= 0) && (comparator.compare(arr[j], temp) > 0)) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = temp;
            }
        }
    }


    /**
     * Your implementation of selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Array/Comparator is null");
        } else {
            for (int i = 0; i < (arr.length - 1); i++) {
                int temp = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (comparator.compare(arr[temp], arr[j]) >= 0) {
                        temp = j;
                    }
                }
                T tempInt = arr[temp];
                arr[temp] = arr[i];
                arr[i] = tempInt;
            }
        }
    }


    /**
     * Your implementation of quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {

        if ((arr == null) || (comparator == null) || (rand == null)) {
            throw new IllegalArgumentException("Array/comparator/random "
                    + "cannot be null.");
        } else {
            quickSort(arr, comparator, rand, 0, arr.length - 1);
        }
    }

    /**
     * Using pivots, correctly sorts the array
     * then partitions it correctly.
     * @param comparator used to check 2 values
     * @param <T> data type to sort
     * @param arr the given array
     * @param a lower index
     * @param b upper index
     * @param rand random pivot generator
     */
    private static <T> void quickSort(T[] arr, Comparator<T>
            comparator, Random rand, int a, int b) {
        if (a < b) {

            T temp;
            int pivotIndex = rand.nextInt(b - a + 1) + a;
            int leftNum = a;
            int rightNum = b - 1;
            T pivot = arr[pivotIndex];
            arr[pivotIndex] = arr[b];
            pivotIndex = b;
            arr[b] = pivot;
            while (leftNum <= rightNum) {
                while ((leftNum <= rightNum)
                        && (comparator.compare(arr[rightNum], pivot) > 0)) {
                    rightNum--;
                }
                while ((leftNum <= rightNum)
                        && (comparator.compare(arr[leftNum], pivot) < 0)) {
                    leftNum++;
                }
                if (leftNum <= rightNum) {
                    temp = arr[leftNum];
                    arr[leftNum] = arr[rightNum];
                    arr[rightNum] = temp;
                    leftNum++;
                    rightNum--;
                }
            }
            temp = arr[leftNum];
            arr[leftNum] = pivot;
            arr[pivotIndex] = temp;
            quickSort(arr, comparator, rand, a, leftNum - 1);
            quickSort(arr, comparator, rand, leftNum + 1, b);
        }
    }
    /**
     * Your implementation of merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Array/Comparator cannot"
                    + "be null");
        } else {
            int n = arr.length;
            if (n > 1) {
                int median = n / 2;
                Object[] lowerHalf = new Object[median];
                Object[] upperHalf = new Object[n - median];
                T[] lH = (T[]) lowerHalf;
                T[] uH = (T[]) upperHalf;

                for (int i = 0; i < median; i++) {
                    lH[i] = arr[i];
                }
                for (int j = median; j < n; j++) {
                    uH[j - median] = arr[j];
                }
                mergeSort(uH, comparator);
                mergeSort(lH, comparator);
                merge(lH, uH, arr, comparator);
            }
        }
    }


    /**
     * Method used to build back the array after
     * initial mergeSort splits.
     * @param <T> sorts data type
     * @param array1st the first array half
     * @param array2nd the second array half
     * @param arr the original array
     * @param comparator comparator to check 2 values
     */
    private static <T> void merge(T[] array1st, T[] array2nd,
                                  T[] arr, Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        while ((i + j) < arr.length) {
            if ((j == array2nd.length) || ((i < array1st.length)
                    && (comparator.compare(array1st[i], array2nd[j]) <= 0))) {
                arr[i + j] = array1st[i++];
            } else {
                arr[i + j] = array2nd[j++];
            }
        }
    }

    /**
     * Your implementation of LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can't be"
                    + "null");
        } else {
            // 20 buckets; 0-9 are negative, 10-19 are positive
            List<Integer>[] bucket = new ArrayList[19];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<Integer>();
            }
            // keep track of passes with bool
            boolean foundGreatest = false;
            int radIndex = 1;
            while (!foundGreatest) {
                foundGreatest = true;

                for (int i = 0; i < arr.length; i++) {
                    int temp = arr[i] / radIndex;
                    bucket[(temp % 10) + 9].add(arr[i]);

                    if ((temp > 0) && foundGreatest) {
                        foundGreatest = false;
                    }
                }

                int index = 0;
                for (int i = 0; i < 19; i++) {
                    for (Integer j : bucket[i]) {
                        arr[index++] = j;
                    }
                    bucket[i].clear();
                }
                radIndex = radIndex * 10;
            }
        }
        return arr;
    }
    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int result = pow(base, exp / 2);
        if (exp % 2 == 1) {
            return result * result * base;
        } else {
            return result * result;
        }
    }
}
