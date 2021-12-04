package Day03;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day03 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input03.txt");

        Day03Solver solver = new Day03Solver(input);

        System.out.println(solver.solveDay03a());
        System.out.println(solver.solveDay03b());
    }
}
