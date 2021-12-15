package Day13;

import Utils.Grid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13Solver {
    private Set<String> points;
    private final List<String> instructions;
    private Grid<Character> grid;

    public Day13Solver(List<String> coordinates, List<String> instructions) {
        this.points = new HashSet<>(coordinates);
        this.instructions = instructions.stream()
                .map(line -> line.replace("fold along ", ""))
                .collect(Collectors.toList());
    }

    public int solveDay13a() {
        String direction = instructions.get(0).split("=")[0];
        int position = Integer.parseInt(instructions.get(0).split("=")[1]);
        fold(position, direction.equals("x"));
        return points.size();
    }

    public String solveDay13b() {
        instructions.forEach(instruction -> {
            String direction = instructions.get(instructions.indexOf(instruction)).split("=")[0];
            int position = Integer.parseInt(instructions.get(instructions.indexOf(instruction)).split("=")[1]);
            fold(position, direction.equals("x"));
        });
        grid = new Grid<>();
        points.forEach(point -> grid.setElement(Integer.parseInt(point.split(",")[0]), Integer.parseInt(point.split(",")[1]), 'X'));
        return grid.toString();
    }

    private void fold(int position, boolean isX) {
        Set<String> pointsToAdd = new HashSet<>();
        Set<String> pointsToRemove = new HashSet<>();
        points.forEach(point -> {
                    int pointX = Integer.parseInt(point.split(",")[0]);
                    int pointY = Integer.parseInt(point.split(",")[1]);
                    if ((isX && pointX > position) || (!isX && pointY > position)) {
                        int delta = isX ? Math.abs(position - pointX) : Math.abs(position - pointY);
                        if (position - delta >= 0) {
                            if (isX) {
                                pointsToAdd.add((position - delta) + "," + pointY);
                            } else {
                                pointsToAdd.add(pointX + "," + (position - delta));
                            }
                            pointsToRemove.add(point);
                        }
                    }
                });
        points.addAll(pointsToAdd);
        points.removeAll(pointsToRemove);
    }
}
