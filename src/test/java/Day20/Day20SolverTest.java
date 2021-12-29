package Day20;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day20SolverTest {

    @Test
    void solveDay20a() {
        List<String> input = new ArrayList<>();
        input.add("..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#");
        input.add("");
        input.add("#..#.");
        input.add("#....");
        input.add("##..#");
        input.add("..#..");
        input.add("..###");

        Day20Solver solver = new Day20Solver(input, 4);
        assertEquals(1, solver.solveDay20a(false));
    }
}