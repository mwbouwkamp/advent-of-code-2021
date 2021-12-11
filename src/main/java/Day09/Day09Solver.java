package Day09;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day09Solver {
    private final LavaMap lavaMap;
    private final int maxX, maxY;
    private final List<Point> basinMinima;

    public Day09Solver(List<String> input) {
        ArrayList<List<Integer>> convertedInput = input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toCollection(ArrayList::new));
        this.maxX = convertedInput.get(0).size();
        this.maxY = convertedInput.size();
        this.lavaMap = new LavaMap(convertedInput);
        this.basinMinima = new ArrayList<>();
    }

    public int solveDay09a() {
        int result = 0;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (lavaMap.isMinimum(x, y)) {
                    result += (lavaMap.getElement(x, y) + 1);
                    basinMinima.add(new Point(x, y));
                }
            }
        }
        return result;
    }

    public long solveDay09b() {
        List<Integer> basinSizes = basinMinima.stream()
                .mapToInt(basin -> lavaMap.getBasinSize(basin.x, basin.y))
                .boxed()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        long result = 1;
        for (int i = 0; i < 3; i++) {
            result *= basinSizes.get(basinSizes.size() - 1 - i);
        }
        return result;
    }

}
