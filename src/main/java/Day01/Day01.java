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
        Day01Solver solver = new Day01Solver(depths, 3);

        System.out.println(solver.solveDay01a());

        System.out.println(solver.solveDay01b());
    }
}
