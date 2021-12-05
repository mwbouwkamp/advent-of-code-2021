package Day05;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day05SolverTest {

    private Day05Solver solver;

    @BeforeEach
    public void setup() {
        List<String> input = new ArrayList<>();
        input.add("0,9 -> 5,9");
        input.add("8,0 -> 0,8");
        input.add("9,4 -> 3,4");
        input.add("2,2 -> 2,1");
        input.add("7,0 -> 7,4");
        input.add("6,4 -> 2,0");
        input.add("0,9 -> 2,9");
        input.add("3,4 -> 1,4");
        input.add("0,0 -> 8,8");
        input.add("5,5 -> 8,2");

        List<Line> lines = input.stream()
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

        solver = new Day05Solver(lines);
    }

    @Test
    public void testDay05a() {
        long result = solver.solveDay05a();

        assertEquals(5, result);
    }

    @Test
    public void testDay5b() {
        long result = solver.solveDay05b();

        assertEquals(12, result);
    }
}