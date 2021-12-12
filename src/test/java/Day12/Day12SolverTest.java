package Day12;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12SolverTest {

    @Test
    void solveDay12a() {
        List<String> input = new ArrayList<>();
        input.add("fs-end");
        input.add("he-DX");
        input.add("fs-he");
        input.add("start-DX");
        input.add("pj-DX");
        input.add("end-zg");
        input.add("zg-sl");
        input.add("zg-pj");
        input.add("pj-he");
        input.add("RW-he");
        input.add("fs-DX");
        input.add("pj-RW");
        input.add("zg-RW");
        input.add("start-pj");
        input.add("he-WI");
        input.add("zg-he");
        input.add("pj-fs");
        input.add("start-RW");

        Day12Solver solver = new Day12Solver();
        assertEquals(226, solver.SolveDay12a(input));
    }

    @Test
    void solveDay12b() {
        List<String> input = new ArrayList<>();
        input.add("start-A");
        input.add("start-b");
        input.add("A-c");
        input.add("A-b");
        input.add("b-d");
        input.add("A-end");
        input.add("b-end");

        Day12Solver solver = new Day12Solver();
        assertEquals(36, solver.SolveDay12b(input));
    }
}