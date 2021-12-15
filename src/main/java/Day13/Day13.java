package Day13;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input13.txt");

        List<String> coordinates = input.stream()
                .filter(line -> !line.startsWith("fold") && line.length() != 0)
                .collect(Collectors.toList());

        List<String> instructions = input.stream()
                .filter(line -> line.startsWith("fold"))
                .collect(Collectors.toList());

        Day13Solver solver = new Day13Solver(coordinates, instructions);
        System.out.println(solver.solveDay13a());

        solver = new Day13Solver(coordinates, instructions);
        System.out.println(solver.solveDay13b());
    }
}
