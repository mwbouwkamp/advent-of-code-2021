package Day20;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day20 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input20.txt");

        Day20Solver solver = new Day20Solver(input, 2);
        System.out.println(solver.solveDay20a(true));

        solver = new Day20Solver(input, 50);
        System.out.println(solver.solveDay20a(true));
    }
}
