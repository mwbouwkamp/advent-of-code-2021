package Day07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day07SolverTest {

    @Test
    void solveDay07a() {
        List<Integer> input = Arrays.stream("16,1,2,0,4,2,7,1,2,14".split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Day07Solver solver = new Day07Solver(input);

        assertEquals(37, solver.solveDay07a());
    }

    @Test
    void solveDay07b() {
        List<Integer> input = Arrays.stream("16,1,2,0,4,2,7,1,2,14".split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        Day07Solver solver = new Day07Solver(input);

        assertEquals(168, solver.solveDay07b());
    }

    @Test
    void calcFuel() {
        Day07Solver solver = new Day07Solver(new ArrayList<>());
        assertEquals(10, solver.calcFuel(4));
    }
}