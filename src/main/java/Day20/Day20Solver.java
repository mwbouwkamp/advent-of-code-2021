package Day20;

import Utils.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20Solver {
    private Grid<Integer> image;
    private final List<Integer> algorithm;
    private final int cycles;

    public Day20Solver(List<String> input, int cycles) {
        this.cycles = cycles;
        input = input.stream()
                .map(s -> s.replace("#", "1").replace(".", "0"))
                .collect(Collectors.toList());
        algorithm = Arrays.stream(input.remove(0).split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        input.remove(0);
        List<Integer> emptyRow = IntStream.range(0, input.get(0).length() + 2 * cycles + 2)
                .map(i -> 0)
                .boxed()
                .collect(Collectors.toList());
        List<List<Integer>> paddingRows = IntStream.range(0, cycles + 1)
                .mapToObj(i -> new ArrayList<>(emptyRow))
                .collect(Collectors.toList());
        List<Integer> paddingRow = IntStream.range(0, cycles + 1)
                .map(i -> 0)
                .boxed()
                .collect(Collectors.toList());

        List extendedInput = new ArrayList(new ArrayList(paddingRows));
        input.forEach(row -> {
            List<Integer> extendedRow = new ArrayList<>();
            extendedRow.addAll(new ArrayList<>(paddingRow));
            extendedRow.addAll(Arrays.stream(row.split(""))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList()));
            extendedRow.addAll(new ArrayList<>(paddingRow));
            extendedInput.add(extendedRow);
        });
        extendedInput.addAll(new ArrayList(paddingRows));
        image = new Grid<>((ArrayList<List<Integer>>) extendedInput);
    }

    public int solveDay20a(boolean flipBorderColor) {
        for (int i = 0; i < cycles; i++) {
            image = cycle();
            if (flipBorderColor) {
                flipBorderColors();
            }
            System.out.println(i);
        }
        return image.getGrid().stream()
                .flatMap(Collection::stream)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private void flipBorderColors() {
        for (int x = 0; x < image.getNumCols(); x++) {
            image.setElement(x, 0, (image.getElement(x, 0) + 1) % 2);
            image.setElement(x, image.getNumRows() - 1, (image.getElement(x, image.getNumRows() - 1) + 1) % 2);
        }
        for (int y = 1; y < image.getNumRows() - 1; y++) {
            image.setElement(0, y, (image.getElement(0, y) + 1) % 2);
            image.setElement(image.getNumCols() - 1, y, (image.getElement(image.getNumCols() - 1, y) + 1) % 2);
        }
    }

    private Grid cycle() {
        ArrayList<List<Integer>> newImage = new ArrayList<>();
        for (int y = 0; y < image.getNumRows(); y++) {
            List<Integer> newImageRow = new ArrayList<>();
            for (int x = 0; x < image.getNumCols(); x++) {
                if (x == 0 || y == 0 || x == image.getNumCols() - 1 || y == image.getNumRows() - 1) {
                    newImageRow.add(image.getElement(x, y));
                } else {
                    StringBuilder binary = new StringBuilder();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            binary.append(image.getElement(x + j, y + i));
                        }
                    }
                    newImageRow.add(algorithm.get(Integer.parseInt(binary.toString(), 2)));
                }
            }
            newImage.add(newImageRow);
        }
        return new Grid(newImage);
    }
}
