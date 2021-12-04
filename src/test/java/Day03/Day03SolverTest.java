package Day03;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day03SolverTest {

    @Test
    public void testDay03b() {
        List<String> input = new ArrayList<>();
        input.add("00100");
        input.add("11110");
        input.add("10110");
        input.add("10111");
        input.add("10101");
        input.add("01111");
        input.add("00111");
        input.add("11100");
        input.add("10000");
        input.add("11001");
        input.add("00010");
        input.add("01010");

        Day03Solver solver = new Day03Solver(input);

        assertEquals(230, solver.solveDay03b());
    }

}