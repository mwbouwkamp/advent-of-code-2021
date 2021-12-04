package Day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day03Solver {
    private final List<String> binaries;

    public Day03Solver(List<String> binaries) {
        this.binaries = binaries;
    }

    public int solveDay03a() {
        int half = binaries.size() / 2;
        String ones = IntStream.range(0, binaries.get(0).length())
                .map(this::countOnes)
                .boxed()
                .map(i -> i > half ? "1" : "0")
                .reduce((a, b) -> a + b)
                .orElse("");
        String zeros = Arrays.stream(ones.split(""))
                .map(i -> i.equals("0") ? "1" : "0")
                .reduce((a, b) -> a + b)
                .orElse("");
        return Integer.parseInt(ones, 2) * Integer.parseInt(zeros, 2);
    }

    private int countOnes(int i) {
        return (int) binaries.stream()
                .filter(s -> s.charAt(i) == '1')
                .count();
    }

    public int solveDay03b() {
        String oxygen = getLifeSupport(true);
        String co2 = getLifeSupport(false);
        return Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2);
    }

    private String getLifeSupport(boolean oxygen) {
        List<String> remainingBinaries = new ArrayList<>(binaries);
        for (int i = 0; i < binaries.get(0).length(); i++) {
            int finalI = i;
            int size = remainingBinaries.size();
            boolean filterOnes = remainingBinaries.stream()
                    .map(s -> s.charAt(finalI))
                    .filter(c -> c == '1')
                    .count() * 2 >= size;
            if (!oxygen) {
                filterOnes = !filterOnes;
            }
            boolean finalFilterOnes = filterOnes;
            remainingBinaries = remainingBinaries.stream()
                    .filter(s -> finalFilterOnes ? s.charAt(finalI) == '1' : s.charAt(finalI) == '0')
                    .collect(Collectors.toList());
            if (remainingBinaries.size() == 1) {
                return remainingBinaries.get(0);
            }
        }
        return "";
    }
}
