package Day19;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day19 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input19.txt");
        Day19Solver solver = new Day19Solver(input);

        solver.solveDay19a();

    }
}
