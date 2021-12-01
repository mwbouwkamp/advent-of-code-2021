package Day01;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {
    public static void main(String[] args) throws IOException {
        List<Integer> depths = new ReadPuzzleInput().readPuzzleLines("input01.txt").stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        int increases = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) {
                increases++;
            }
        }
        System.out.println(increases);

        increases = 0;
        int windowSize = 3;
        for (int i = windowSize; i < depths.size(); i++) {
            int first = calculateWindow(depths, i - 1, windowSize);
            int second = calculateWindow(depths, i, windowSize);
            if (second > first) {
                increases++;
            }
        }

        System.out.println(increases);
    }

    private static int calculateWindow(List<Integer> dephts, int index, int windowSize) {
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += dephts.get(index - i);
        }
        return windowSum;
    }
}
