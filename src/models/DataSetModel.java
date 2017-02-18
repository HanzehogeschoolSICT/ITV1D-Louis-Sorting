package models;

import java.awt.*;
import java.util.*;

public class DataSetModel implements Iterable<Integer> {
    private boolean isSorted = false;
    private final int highestNumber;
    private final LinkedList<Integer> data;
    private final Set<Integer> latestChanges;

    /**
     * Initialize the data set with numbers from 1 to N in a random order.
     * @param n Number of numbers in the data set.
     */
    public DataSetModel(int n) {
        highestNumber = n;
        data = new LinkedList<>();
        latestChanges = new HashSet<>();

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

    public void setLatestChanges(int... numbers) {
        latestChanges.clear();
        for (int number : numbers)
            latestChanges.add(number);
    }

    public Color getColor(int number) {
        if (isSorted)
            return new Color(27, 94, 32);

        if (latestChanges.contains(number))
            return new Color(213, 0, 0);

        return new Color(41, 98, 255);
    }

    public LinkedList<Integer> getData() {
        return data;
    }

    public int getMaxIndex() {
        return data.size() - 1;
    }
}
