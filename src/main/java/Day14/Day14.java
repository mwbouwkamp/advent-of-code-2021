package Day14;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day14 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input14.txt");
        Day14Solver solver = new Day14Solver(input);
        System.out.println(solver.solveDay14a());

        input = new ReadPuzzleInput().readPuzzleLines("input14.txt");
        solver = new Day14Solver(input);
        System.out.println(solver.solveDay14b());
    }
}
