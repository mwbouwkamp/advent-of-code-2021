package Day11;

import Utils.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11Solver {
    private final Grid<Integer> energyLevels;
    private int totalFlashes;
    private int synchronous;

    public Day11Solver(List<String> input) {
        List<List<Integer>> gridInput = input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.energyLevels = new Grid<>((ArrayList<List<Integer>>) gridInput);
        this.totalFlashes = 0;
        this.synchronous = -1;
    }

    public String solveDay11() {
        boolean isSynchronous = false;
        for (int i = 0; i < 100; i++) {
            cycle(i);
            if (isSynchronous() && !isSynchronous) {
                synchronous = i;
                isSynchronous = true;
            }
        }
        int i = 100;
        while (!isSynchronous) {
            cycle(i);
            if (isSynchronous()) {
                synchronous = i;
                isSynchronous = true;
            }
            i++;
        }
        synchronous++;
        return totalFlashes + " " + synchronous;
    }

    private int cycle(int i) {
        increaseAll();
        int flashes = -1;
        while (flashes == -1 || flashes > 0) {
            flashes = flash();
            if (i < 100) {
                totalFlashes += flashes;
            }
//            resetFlashes();
        }
        resetFlashes();
        return flashes;
    }

    private boolean isSynchronous() {
        return energyLevels.toString().equals("0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0");
    }
    private void resetFlashes() {
        for (int x = 0; x < energyLevels.getNumCols(); x++) {
            for (int y = 0; y < energyLevels.getNumRows(); y++) {
                if (energyLevels.getElement(x, y) < 0) {
                    energyLevels.setElement(x, y, 0);
                }
            }
        }
    }

    private int flash() {
        int newFlashes = 0;
        int maxX = energyLevels.getNumCols();
        int maxY = energyLevels.getNumRows();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (energyLevels.getElement(x, y) > 9) {
                    energyLevels.setElement(x, y, Integer.MIN_VALUE);
                    newFlashes++;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int targetX = x + j;
                            int targetY = y + i;
                            if (targetX >= 0 && targetX < maxX && targetY >= 0 && targetY < maxY) {
                                energyLevels.setElement(targetX, targetY, energyLevels.getElement(targetX, targetY) + 1);
                            }
                        }
                    }
                }
            }
        }
        return newFlashes;
    }

    private void increaseAll() {
        for (int x = 0; x < energyLevels.getNumCols(); x++) {
            for (int y = 0; y < energyLevels.getNumRows(); y++) {
                energyLevels.setElement(x, y, energyLevels.getElement(x, y) + 1);
            }
        }
    }
}
