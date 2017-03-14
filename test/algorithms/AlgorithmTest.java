package algorithms;

import models.DataSetModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {
    /**
     * Test the Bubble Sort Algorithm.
     */
    @Test
    void bubbleSortAlgorithmTest() {
        algorithmTest(BubbleSortAlgorithm::new, 26);
    }

    /**
     * Test the Insertion Sort Algorithm.
     */
    @Test
    void insertionSortAlgorithmTest() {
        algorithmTest(InsertionSortAlgorithm::new, 15);
    }

    /**
     * Test the Quick Sort Algorithm.
     */
    @Test
    void quickSortAlgorithmTest() {
        algorithmTest(QuickSortAlgorithm::new, 13);
    }

    /**
     * Test an algorithm using the given algorithm factory and the given expected number of steps.
     *
     * @param algorithmFactory Factory for the algorithm to test.
     * @param expectedSteps Expected number of steps for the algorithm.
     */
    private void algorithmTest(Function<DataSetModel, Algorithm> algorithmFactory, int expectedSteps) {
        LinkedList<Integer> data = new LinkedList<>(Arrays.asList(4, 5, 6, 3, 1, 2));
        DataSetModel dataSet = new DataSetModel(data);

        int stepCount = 0;
        Algorithm algorithm = algorithmFactory.apply(dataSet);
        while (algorithm.nextStep())
            stepCount++;

        LinkedList<Integer> resultData = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(data, resultData, "Set has not been sorted correctly");
        assertEquals(stepCount, expectedSteps, "Sorting took invalid number of steps");
    }
}