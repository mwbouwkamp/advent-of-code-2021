package Day10;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day10 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input10.txt");

        Day10Solver solver = new Day10Solver(input);

        System.out.println(solver.solveDay10a());
        System.out.println(solver.solveDay10b());

    }
}
