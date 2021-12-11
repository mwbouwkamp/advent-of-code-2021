package Day08;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day08 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input08.txt");

        Day08Solver solver = new Day08Solver(input);
        System.out.println(solver.solveDay08a());

        solver = new Day08Solver(input);
        System.out.println(solver.solveDay08b());



    }
}
