package Day05;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day05Solver {
    private List<Line> lines;
    private Map<Point, Integer> heatMap;

    public Day05Solver(List<Line> lines) {
        this.lines = lines;
    }

    public long solveDay05a() {
        return solveDay05(false);
    }

    public long solveDay05b() {
        return solveDay05(true);
    }

    private long solveDay05(boolean withDiagonal) {
        if (!withDiagonal)  {
            lines = lines.stream()
                    .filter(Line::isStraight)
                    .collect(Collectors.toList());
        } else {
            lines = lines.stream()
                    .filter(line -> line.isStraight() || line.isDiagonal())
                    .collect(Collectors.toList());
        }
        heatMap = new HashMap<>();
        lines.forEach(line -> {
            boolean isHorizontal = line.isHorizontal();
            boolean isVertical = line.isVertical();
            boolean isAscending = !line.isStraight() && line.isAscending();

            int minX = Math.min(line.getStart().x, line.getEnd().x);
            int minY = Math.min(line.getStart().y, line.getEnd().y);
            int maxX = Math.max(line.getStart().x, line.getEnd().x);
            int maxY = Math.max(line.getStart().y, line.getEnd().y);
            int range = isHorizontal ? maxX - minX : maxY - minY;
            for (int i = 0; i <= range; i++) {
                updateHeatMap(isHorizontal
                        ? new Point(minX + i, minY)
                        : isVertical
                        ? new Point(minX, minY + i)
                        : isAscending
                        ? new Point(minX + i, minY + i)
                        : new Point(minX + i, maxY - i));
            }
        });
        return heatMap.keySet().stream()
                .filter(key -> heatMap.get(key) >= 2)
                .count();
    }

    private void updateHeatMap(Point coordinate) {
        if (heatMap.containsKey(coordinate)) {
            heatMap.put(coordinate, heatMap.get(coordinate) + 1);
        } else {
            heatMap.put(coordinate, 1);
        }
    }
}
