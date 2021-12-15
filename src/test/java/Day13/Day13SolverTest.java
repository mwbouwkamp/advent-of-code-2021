package Day13;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day13SolverTest {

    @Test
    void solveDay13a() {
        List<String> coordinates = new ArrayList<>();
        List<String> instructions = new ArrayList<>();

        coordinates.add("6,10");
        coordinates.add("0,14");
        coordinates.add("9,10");
        coordinates.add("0,3");
        coordinates.add("10,4");
        coordinates.add("4,11");
        coordinates.add("6,0");
        coordinates.add("6,12");
        coordinates.add("4,1");
        coordinates.add("0,13");
        coordinates.add("10,12");
        coordinates.add("3,4");
        coordinates.add("3,0");
        coordinates.add("8,4");
        coordinates.add("1,10");
        coordinates.add("2,14");
        coordinates.add("8,10");
        coordinates.add("9,0");

        instructions.add("fold along y=7");
        instructions.add("fold along x=5");

        Day13Solver solver = new Day13Solver(coordinates, instructions);
        assertEquals(17, solver.solveDay13a());
    }

    @Test
    void solveDay13b() {
        List<String> coordinates = new ArrayList<>();
        List<String> instructions = new ArrayList<>();

        coordinates.add("6,10");
        coordinates.add("0,14");
        coordinates.add("9,10");
        coordinates.add("0,3");
        coordinates.add("10,4");
        coordinates.add("4,11");
        coordinates.add("6,0");
        coordinates.add("6,12");
        coordinates.add("4,1");
        coordinates.add("0,13");
        coordinates.add("10,12");
        coordinates.add("3,4");
        coordinates.add("3,0");
        coordinates.add("8,4");
        coordinates.add("1,10");
        coordinates.add("2,14");
        coordinates.add("8,10");
        coordinates.add("9,0");

        instructions.add("fold along y=7");
        instructions.add("fold along x=5");

        Day13Solver solver = new Day13Solver(coordinates, instructions);
        assertEquals(17, solver.solveDay13b());
    }

}