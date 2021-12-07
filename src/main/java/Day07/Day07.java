package Day07;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day07 {

    public static void main(String[] args) throws IOException {
        List<Integer> input = new ReadPuzzleInput().readPuzzleIntegers("input07.txt", ",");

        Day07Solver solver = new Day07Solver(input);
        System.out.println(solver.solveDay07a());
        System.out.println(solver.solveDay07b());

    }
}
