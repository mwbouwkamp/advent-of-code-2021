package Day06;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day06 {

    public static void main(String[] args) throws IOException {
        List<Long> input = new ReadPuzzleInput().readPuzzleLongs("input06.txt", ",");

        Day06Solver solver = new Day06Solver(input);
        System.out.println(solver.solveDay06a());

        solver = new Day06Solver(input);
        System.out.println(solver.solveDay06b());
    }
}
