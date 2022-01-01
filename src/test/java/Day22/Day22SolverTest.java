package Day22;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day22SolverTest {

    @Test
    void solveDay22a() {
        List<String> input = new ArrayList<>();
        input.add("on x=10..12,y=10..12,z=10..12");
        input.add("on x=11..13,y=11..13,z=11..13");
        input.add("off x=9..11,y=9..11,z=9..11");
        input.add("on x=10..10,y=10..10,z=10..10");
        Day22Solver solver = new Day22Solver(input);
        assertEquals(39, solver.solveDay22a(true));
    }
}