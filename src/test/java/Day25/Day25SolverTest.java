package Day25;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day25SolverTest {

    @Test
    void solveDay25a() {
        List<String> input = new ArrayList<>();

        input.add("v...>>.vv>");
        input.add(".vv>>.vv..");
        input.add(">>.>v>...v");
        input.add(">>v>>.>.v.");
        input.add("v>v.vv.v..");
        input.add(">.>>..v...");
        input.add(".vv..>.>v.");
        input.add("v.v..>>v.v");
        input.add("....v..v.>");
//        input.add("...>...");
//        input.add(".......");
//        input.add("......>");
//        input.add("v.....>");
//        input.add("......>");
//        input.add(".......");
//        input.add("..vvv..");

        Day25Solver solver = new Day25Solver(input);
        assertEquals(58, solver.solveDay25a());

    }
}