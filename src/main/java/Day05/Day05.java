package Day05;

import Utils.ReadPuzzleInput;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) throws IOException {
        List<String> linesString = new ReadPuzzleInput().readPuzzleLines("input05.txt");
        List<Line> lines = linesString.stream()
                .map(line -> {
                    String[] coordinates = line.split(" -> ");
                    String[] start = coordinates[0].split(",");
                    String[] end = coordinates[1].split(",");
                    return new Line(
                            new Point(Integer.parseInt(start[0]), Integer.parseInt(start[1])),
                            new Point(Integer.parseInt(end[0]), Integer.parseInt(end[1]))
                    );
                })
                .collect(Collectors.toList());

        Day05Solver solver = new Day05Solver(lines);
        System.out.println(solver.solveDay05a());

        solver = new Day05Solver(lines);
        System.out.println(solver.solveDay05b());
    }
}
