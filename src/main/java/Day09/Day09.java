package Day09;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day09 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input09.txt");

        Day09Solver solver = new Day09Solver(input);
        System.out.println(solver.solveDay09a());
        System.out.println(solver.solveDay09b());

    }
}
