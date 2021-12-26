package Day15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15SolverTest {

    @Test
    void solveDay15a() {
        List<String> input = new ArrayList<>();
        input.add("1163751742");
        input.add("1381373672");
        input.add("2136511328");
        input.add("3694931569");
        input.add("7463417111");
        input.add("1319128137");
        input.add("1359912421");
        input.add("3125421639");
        input.add("1293138521");
        input.add("2311944581");

        Day15Solver solver = new Day15Solver(input);
        assertEquals(40, solver.solveDay15a());
    }
}