package models;

import java.awt.*;
import java.util.*;

public class DataSetModel implements Iterable<Integer> {
    private boolean isSorted = false;
    private final int highestNumber;
    private final LinkedList<Integer> data;
    private final Set<Integer> comparedNumbers;

    /**
     * Initialize the data set with numbers from 1 to N in a random order.
     * @param n Number of numbers in the data set.
     */
    public DataSetModel(int n) {
        highestNumber = n;
        data = new LinkedList<>();
        comparedNumbers = new HashSet<>();

        Random random = new Random();
        for (int i = 1; i <= n; i++)
            data.add(random.nextInt(i), i);
    }

    /**
     * Returns an iterator for all numbers in the data set.
     * @return An iterator for all numbers in the data set.
     */
    @Override
    public Iterator<Integer> iterator() {
        return data.iterator();
    }

    public void setIsSorted() {
        this.isSorted = true;
    }

    public int getHighestNumber() {
        return highestNumber;
    }

    public Color getColor(int number) {
        if (isSorted)
            return new Color(27, 94, 32);

        if (comparedNumbers.contains(number))
            return new Color(213, 0, 0);

        return new Color(41, 98, 255);
    }

    public LinkedList<Integer> getData() {
        return data;
    }

    public void swap(int firstIndex, int secondIndex) {
        Collections.swap(data, firstIndex, secondIndex);
    }

    public void markComparedNumbers(int firstIndex, int secondIndex) {
        comparedNumbers.clear();
        comparedNumbers.add(data.get(firstIndex));
        comparedNumbers.add(data.get(secondIndex));
    }
}
