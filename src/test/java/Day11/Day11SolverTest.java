package Day11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11SolverTest {

    @Test
    void solveDay11a() {
        List<String> input = new ArrayList<>();
        input.add("5483143223");
        input.add("2745854711");
        input.add("5264556173");
        input.add("6141336146");
        input.add("6357385478");
        input.add("4167524645");
        input.add("2176841721");
        input.add("6882881134");
        input.add("4846848554");
        input.add("5283751526");

        Day11Solver solver = new Day11Solver(input);

        assertEquals("1656 195", solver.solveDay11());
    }
}