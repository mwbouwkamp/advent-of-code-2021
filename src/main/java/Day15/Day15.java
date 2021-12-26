package Day15;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day15 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input15.txt");

        Day15Solver solver = new Day15Solver(input);
        System.out.println(solver.solveDay15a());
    }
}
