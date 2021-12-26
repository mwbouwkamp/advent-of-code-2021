package Day16;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.List;

public class Day16 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input16.txt");

        Day16Solver solver = new Day16Solver(input);


    }
}
