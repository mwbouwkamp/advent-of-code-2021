package Day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day18SolverTest {

    @Test
    void solveDay18a() {
        List<String> input = new ArrayList<>();

        input.add("[[[0[58]][[17][96]]][[4[12]][[14]2]]]");
        input.add("[[[5[28]]4][5[[99]0]]]");
        input.add("[6[[[62][56]][[76][47]]]]");
        input.add("[[[6[07]][09]][4[9[90]]]]");
        input.add("[[[7[64]][3[13]]][[[55]1]9]]");
        input.add("[[6[[73][32]]][[[38][57]]4]]");
        input.add("[[[[54][77]]8][[83]8]]");
        input.add("[[93][[99][6[49]]]]");
        input.add("[[2[[77]7]][[58][[93][02]]]]");
        input.add("[[[[52]5][8[37]]][[5[75]][44]]]");

//        input.add("[[[0[45]][00]][[[45][26]][95]]]");
//        input.add("[7[[[37][43]][[63][88]]]]");

//= [[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]

        Day18Solver solver = new Day18Solver(input);
        assertEquals(4140, solver.solveDay18a());

    }
    @Test
    void explode() {
        Day18Solver solver = new Day18Solver(new ArrayList<>());

        ArrayList<String> input = new ArrayList<>(Arrays.asList("[[[[[98]1]2]3]4]".split("")));
        String expected = Arrays.asList("[[[[09]2]3]4]".split("")).toString();
        solver.explode(input, 5);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[7[6[5[4[32]]]]]".split("")));
        expected = Arrays.asList("[7[6[5[70]]]]".split("")).toString();
        solver.explode(input, 9);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[6[5[4[32]]]]1]".split("")));
        expected = Arrays.asList("[[6[5[70]]]3]".split("")).toString();
        solver.explode(input, 8);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[3[2[1[73]]]][6[5[4[32]]]]]".split("")));
        expected = Arrays.asList("[[3[2[80]]][9[5[4[32]]]]]".split("")).toString();
        solver.explode(input, 8);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[3[2[80]]][9[5[4[32]]]]]".split("")));
        expected = Arrays.asList("[[3[2[80]]][9[5[70]]]]".split("")).toString();
        solver.explode(input, 18);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[[[[43]4]4][7[[84]9]]][11]]".split("")));
        expected = Arrays.asList("[[[[07]4][7[[84]9]]][11]]".split("")).toString();
        solver.explode(input, 5);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[[[07]4][7[[84]9]]][11]]".split("")));
        expected = Arrays.asList("[,[,[,[,0,7,],4,],[,15,[,0,13,],],],[,1,1,],]".split(",")).toString();
        solver.explode(input, 13);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("[[[[07]4][[78][0[67]]]][11]]".split("")));
        expected = Arrays.asList("[[[[07]4][[78][60]]][81]]".split("")).toString();
        solver.explode(input, 17);
        assertEquals(expected, input.toString());


    }

    @Test
    public void split() {
        Day18Solver solver = new Day18Solver(new ArrayList<>());

        ArrayList<String> input = new ArrayList<>(Arrays.asList("1,16,1".split(",")));
        String expected = Arrays.asList("1[88]1".split("")).toString();
        solver.split(input, 1);
        assertEquals(expected, input.toString());

        input = new ArrayList<>(Arrays.asList("1,17,1".split(",")));
        expected = Arrays.asList("1[89]1".split("")).toString();
        solver.split(input, 1);
        assertEquals(expected, input.toString());
    }

    @Test
    void solveAdditions() {
        List<String> input = new ArrayList<>();
        input.add("[1,1]");
        input.add("[2,2]");
        input.add("[3,3]");
        input.add("[4,4]");
        Day18Solver solver = new Day18Solver(input);
        String expected = Arrays.asList("[[[[11][22]][33]][44]]".split("")).toString();
        assertEquals(expected, solver.solveAdditions().toString());

        input = new ArrayList<>();
        input.add("[1,1]");
        input.add("[2,2]");
        input.add("[3,3]");
        input.add("[4,4]");
        input.add("[5,5]");
        solver = new Day18Solver(input);
        expected = Arrays.asList("[[[[30][53]][44]][55]]".split("")).toString();
        assertEquals(expected, solver.solveAdditions().toString());

        input = new ArrayList<>();
        input.add("[1,1]");
        input.add("[2,2]");
        input.add("[3,3]");
        input.add("[4,4]");
        input.add("[5,5]");
        input.add("[6,6]");
        solver = new Day18Solver(input);
        expected = Arrays.asList("[[[[50][74]][55]][66]]".split("")).toString();
        assertEquals(expected, solver.solveAdditions().toString());

        input = new ArrayList<>();
        input.add("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]");
        input.add("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]");
        input.add("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]");
        input.add("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]");
        input.add("[7,[5,[[3,8],[1,4]]]]");
        input.add("[[2,[2,2]],[8,[8,1]]]");
        input.add("[2,9]");
        input.add("[1,[[[9,3],9],[[9,0],[0,7]]]]");
        input.add("[[[5,[7,4]],7],1]");
        input.add("[[[[4,2],2],6],[8,7]]");
        solver = new Day18Solver(input);
        expected = Arrays.asList("[[[[87][77]][[86][77]]][[[07][66]][87]]]".split("")).toString();
        assertEquals(expected, solver.solveAdditions().toString());
    }

    @Test
    public void solveAddition() {
        Day18Solver solver = new Day18Solver(new ArrayList<>());

        ArrayList<String> input = new ArrayList<>(Arrays.asList("[[[[[43]4]4][7[[84]9]]][11]]".split("")));
        String expected = Arrays.asList("[[[[07]4][[78][60]]][81]]".split("")).toString();
        solver.solveAddition(input);
        assertEquals(expected, input.toString());
    }

    @Test
    public void calcMagnitude() {
        Day18Solver solver = new Day18Solver(new ArrayList<>());

        ArrayList<String> input = new ArrayList<>(Arrays.asList("[[12][[34]5]]".split("")));
        int expected = 143;
        assertEquals(expected,  solver.calcMagnitude(input));

        input = new ArrayList<>(Arrays.asList("[[[[07]4][[78][60]]][81]]".split("")));
        expected = 1384;
        assertEquals(expected,  solver.calcMagnitude(input));

        input = new ArrayList<>(Arrays.asList("[[[[87][77]][[86][77]]][[[07][66]][87]]]".split("")));
        expected = 3488;
        assertEquals(expected,  solver.calcMagnitude(input));

        input = new ArrayList<>(Arrays.asList("[[[[66][76]][[77][70]]][[[77][77]][[78][99]]]]".split("")));
        expected = 4140;
        assertEquals(expected,  solver.calcMagnitude(input));

    }
}
