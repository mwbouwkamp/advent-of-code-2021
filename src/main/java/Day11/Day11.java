package Day11;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day11 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input11.txt");

        Day11Solver solver = new Day11Solver(input);
        System.out.println(solver.solveDay11());

    }
}
