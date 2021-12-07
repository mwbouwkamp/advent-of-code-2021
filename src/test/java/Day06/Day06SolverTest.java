package Day06;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day06SolverTest {

    @Test
    void solveDay06a() {
        List<Long> input = new ArrayList<>();
        input.add(3L);
        input.add(4L);
        input.add(3L);
        input.add(1L);
        input.add(2L);

        Day06Solver solver = new Day06Solver(input);

        assertEquals(5934, solver.solveDay06a());
    }
}