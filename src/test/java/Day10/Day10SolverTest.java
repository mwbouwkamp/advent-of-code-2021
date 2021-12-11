package Day10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10SolverTest {

    @Test
    void solveDay10() {
        List<String> input = new ArrayList<>();
        input.add("[({(<(())[]>[[{[]{<()<>>");
        input.add("[(()[<>])]({[<{<<[]>>(");
        input.add("{([(<{}[<>[]}>{[]{[(<()>");
        input.add("(((({<>}<{<{<>}{[]{[]{}");
        input.add("[[<[([]))<([[{}[[()]]]");
        input.add("[{[{({}]{}}([{[{{{}}([]");
        input.add("{<[[]]>}<{[{[{[]{()[[[]");
        input.add("[<(<(<(<{}))><([]([]()");
        input.add("<{([([[(<>()){}]>(<<{{");
        input.add("<{([{{}}[<[[[<>{}]]]>[]]");

        Day10Solver solver = new Day10Solver(input);

        assertEquals(26397, solver.solveDay10a());
        assertEquals(288957, solver.solveDay10b());
    }
}