package Day01;

import java.util.List;

public class Day01Solver {
    private final List<Integer> depths;
    private final int windowSize;

    public Day01Solver(List<Integer> input, int windowSize) {
        this.depths = input;
        this.windowSize = windowSize;
    }

    public int solveDay01a() {
        int increases = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) {
                increases++;
            }
        }
        return increases;
    }

    public int solveDay01b() {
        int increases = 0;
        for (int i = windowSize; i < depths.size(); i++) {
            int first = calculateWindow(depths, i - 1, windowSize);
            int second = calculateWindow(depths, i, windowSize);
            if (second > first) {
                increases++;
            }
        }
        return increases;
    }

    private int calculateWindow(List<Integer> depths, int index, int windowSize) {
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += depths.get(index - i);
        }
        return windowSum;
    }
}
