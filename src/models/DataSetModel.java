package models;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class DataSetModel implements Iterable<Integer> {
    private int highestNumber;
    private LinkedList<Integer> data;

    /**
     * Initialize the data set with numbers from 1 to N in a random order.
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
     * Returns an iterator for all numbers in the data set.
     * @return An iterator for all numbers in the data set.
     */
    @Override
    public Iterator<Integer> iterator() {
        return data.iterator();
    }

    public int getHighestNumber() {
        return highestNumber;
    }
}
