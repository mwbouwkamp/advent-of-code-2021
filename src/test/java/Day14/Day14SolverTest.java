package Day14;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14SolverTest {

    @Test
    void solveDay14a() {
        List<String> input = new ArrayList<>();
        input.add("NNCB");
        input.add("");
        input.add("CH -> B");
        input.add("HH -> N");
        input.add("CB -> H");
        input.add("NH -> C");
        input.add("HB -> C");
        input.add("HC -> B");
        input.add("HN -> C");
        input.add("NN -> C");
        input.add("BH -> H");
        input.add("NC -> B");
        input.add("NB -> B");
        input.add("BN -> B");
        input.add("BB -> N");
        input.add("BC -> B");
        input.add("CC -> N");
        input.add("CN -> C");

        Day14Solver solver = new Day14Solver(input);
        assertEquals(1588, solver.solveDay14a());
    }

    @Test
    void solveDay14b() {
        List<String> input = new ArrayList<>();
        input.add("NNCB");
        input.add("");
        input.add("CH -> B");
        input.add("HH -> N");
        input.add("CB -> H");
        input.add("NH -> C");
        input.add("HB -> C");
        input.add("HC -> B");
        input.add("HN -> C");
        input.add("NN -> C");
        input.add("BH -> H");
        input.add("NC -> B");
        input.add("NB -> B");
        input.add("BN -> B");
        input.add("BB -> N");
        input.add("BC -> B");
        input.add("CC -> N");
        input.add("CN -> C");

        Day14Solver solver = new Day14Solver(input);
        Long expected = 2188189693529L;
        assertEquals(expected, solver.solveDay14b());
    }

}