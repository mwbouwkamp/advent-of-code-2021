package Day12;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day12 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input12.txt");

        Day12Solver solver = new Day12Solver();
        System.out.println(solver.SolveDay12a(input));
        System.out.println(solver.SolveDay12b(input));
    }
}
