package models;

import java.util.HashSet;
import java.util.Set;

public class MergeSortModel {
    private final Set<Integer> left = new HashSet<>();
    private final Set<Integer> right = new HashSet<>();

    public void clear() {
        left.clear();
        right.clear();
    }

    public void addLeft(int number) {
        left.add(number);
    }

    public boolean isLeft(int number) {
        return left.contains(number);
    }

    public void addRight(int number) {
        right.add(number);
    }

    public boolean isRight(int number) {
        return right.contains(number);
    }
}
