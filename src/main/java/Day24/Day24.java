package Day24;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day24 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input24.txt");
        Day24Solver solver = new Day24Solver(input);
        System.out.println(solver.solveDay24a());
//        System.out.println(solver.alternativeSolveDay2a());
    }
}
