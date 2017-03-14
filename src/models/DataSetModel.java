package models;

import java.util.*;

/**
 * Model to hold the data set itself.
 */
public class DataSetModel implements Iterable<Integer> {
    private final int highestNumber;
    private final LinkedList<Integer> data;
    private final Set<Integer> comparedNumbers = new HashSet<>();
    private boolean isSorted = false;

    /**
     * Initialize the data set with numbers from 1 to N in a random order.
     *
     * @param n Number of numbers in the data set.
     */
    public DataSetModel(int n) {
        highestNumber = n;
        data = new LinkedList<>();

        Random random = new Random();
        for (int i = 1; i <= n; i++)
            data.add(random.nextInt(i), i);
    }

    /**
     * Initialize the data set with the given numbers.
     *
     * @param data Data to use.
     */
    public DataSetModel(LinkedList<Integer> data) {
        highestNumber = Collections.max(data);
        this.data = data;
    }

    /**
     * Returns an iterator for all numbers in the data set.
     *
     * @return An iterator for all numbers in the data set.
     */
    @Override
    public Iterator<Integer> iterator() {
        return data.iterator();
    }

    /**
     * Check if a number has been compared in the latest algorithm step.
     *
     * @param number Number to check.
     * @return True if the number has been compared, false otherwise.
     */
    public boolean isNumberCompared(int number) {
        return comparedNumbers.contains(number);
    }

    /**
     * Get a value indicating if the data set is fully sorted.
     *
     * @return A value indicating if the dat set is fully sorted.
     */
    public boolean getIsSorted() {
        return isSorted;
    }

    /**
     * Mark the data set as fully sorted.
     */
    public void setIsSorted() {
        this.isSorted = true;
    }

    /**
     * Get the highest number of the data set.
     *
     * @return Highest number of the data set.
     */
    public int getHighestNumber() {
        return highestNumber;
    }

    /**
     * Get the raw data of this data set.
     *
     * @return Raw data of this data set.
     */
    public LinkedList<Integer> getData() {
        return data;
    }

    /**
     * Swap 2 items in this data set.
     *
     * @param firstIndex  Index of the first item to swap.
     * @param secondIndex Index of the second item to swap.
     */
    public void swap(int firstIndex, int secondIndex) {
        Collections.swap(data, firstIndex, secondIndex);
    }

    /**
     * Mark a single item as compared during the latest algorithm step.
     *
     * @param index Index of the item to mark.
     */
    public void markComparedNumber(int index) {
        comparedNumbers.clear();
        comparedNumbers.add(data.get(index));
    }

    /**
     * Mark 2 items as compared during the latest algorithm step.
     *
     * @param firstIndex  Index of the first item to mark.
     * @param secondIndex Index of the second item to mark.
     */
    public void markComparedNumbers(int firstIndex, int secondIndex) {
        comparedNumbers.clear();
        comparedNumbers.add(data.get(firstIndex));
        comparedNumbers.add(data.get(secondIndex));
    }
}
