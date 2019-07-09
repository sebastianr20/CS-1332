import java.util.ArrayList;
import java.util.Comparator;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Juan Rodriguez
 * @userid jrodriguez325
 * @GTID 903379809
 * @version 1.0
 *
 * Collaborators: none
 *
 * Resources: none
 */
public class Sorting {

    /**
     * Implement insertion sort.
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
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        int length = arr.length;
        if (length < 2) {
            return;
        }
        int midIndex = length / 2;
        T[] leftArr = (T[]) new Object[midIndex];
        T[] rightArr = (T[]) new Object[length - midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = midIndex; i < length; i++) {
            rightArr[i - midIndex] = arr[i];
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while (leftIndex < midIndex && rightIndex < length - midIndex) {
            if (comparator.compare(leftArr[leftIndex],
                    rightArr[rightIndex]) <= 0) {
                arr[currentIndex] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }
        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArr[leftIndex];
            leftIndex++;
            currentIndex++;
        }
        while (rightIndex < length - midIndex) {
            arr[currentIndex] = rightArr[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array");
        }
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            buckets.add(new ArrayList<>());
        }
        int iterations = 0;
        int length = arr.length;
        int maxValue = 0;
        for (int n : arr) {
            if (n < 0) {
                n *= -1;
            }
            if (n > maxValue) {
                maxValue = n;
            }
        }
        while (maxValue != 0) {
            maxValue /= 10;
            iterations++;
        }
        int bucket;
        int digitfactor = 1;
        for (int i = 1; i <= iterations; i++) {
            for (int j = 0; j < length; j++) {
                int modified = arr[j] / digitfactor;
                if (modified < 0) {
                    modified *= -1;
                }
                bucket = modified % 10;
                if (arr[j] >= 0) {
                    bucket += 10;
                } else {
                    bucket = 9 - bucket;
                }
                buckets.get(bucket).add(arr[j]);
            }
            int index = 0;
            for (bucket = 0; bucket < 20; bucket++) {
                while (buckets.get(bucket).size() != 0) {
                    arr[index] = buckets.get(bucket).remove(0);
                    index++;
                }
            }
            digitfactor *= 10;
        }
    }
}
