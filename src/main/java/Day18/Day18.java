package Day18;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day18 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input18.txt");

        Day18Solver solver = new Day18Solver(input);
        System.out.println(solver.solveDay18a());

        solver = new Day18Solver(input);
        System.out.println(solver.solveDay18b());

    }
}
