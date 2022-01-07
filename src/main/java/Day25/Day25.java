package Day25;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day25 {
    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input25.txt");
        Day25Solver solver = new Day25Solver(input);
        System.out.println(solver.solveDay25a());

    }
}
