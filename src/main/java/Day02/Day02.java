package Day02;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input02.txt");

        Day02Solver solver = new Day02Solver(input);

        System.out.println(solver.solveDay02a());
        System.out.println(solver.solveDay02b());

    }
}
