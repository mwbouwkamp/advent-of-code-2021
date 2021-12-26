package Day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17SolverTest {

    @Test
    void isXSolution() {
        Day17Solver solver = new Day17Solver(0, 0, 0, 0);
        assertNotEquals(null, solver.isXSolution(5, 5, 1));
        assertNull(solver.isXSolution(5, 6, 1));
        assertNull(solver.isXSolution(5, 7, 1));
        assertNull(solver.isXSolution(5, 8, 1));
        assertNotEquals(null, solver.isXSolution(5, 9, 1));
        assertNull(solver.isXSolution(5, 10, 1));
        assertNull(solver.isXSolution(5, 11, 1));
        assertNotEquals(null, solver.isXSolution(5, 12, 1));
        assertNull(solver.isXSolution(5, 13, 1));
        assertNotEquals(null, solver.isXSolution(5, 14, 1));
        assertNotEquals(null, solver.isXSolution(5, 15, 1));
        assertNull(solver.isXSolution(5, 16, 1));

        assertNotEquals(null, solver.isXSolution(6, 21, 1));
    }

    @Test
    void calculateMinNumberOfYSteps() {
        Day17Solver solver = new Day17Solver(0, 0, 0, -1);
        assertEquals(1, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -2);
        assertEquals(2, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -3);
        assertEquals(2, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -4);
        assertEquals(3, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -5);
        assertEquals(3, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -6);
        assertEquals(3, solver.calculateMinNumberOfYSteps());

        solver = new Day17Solver(0, 0, 0, -7);
        assertEquals(4, solver.calculateMinNumberOfYSteps());
    }

    @Test
    void isYSolution() {
        Day17Solver solver = new Day17Solver(0, 0, 0, 0);
        assertNotNull(solver.isYSolution(5, -6, 1));
        assertNotNull(solver.isYSolution(5, -13, 1));
        assertNotNull(solver.isYSolution(5, -21, 1));
        assertNotNull(solver.isYSolution(5, -30, 1));
        assertNotNull(solver.isYSolution(5, -40, 1));

        assertNotNull(solver.isYSolution(45, -40, 1));
    }

    @Test
    void solveDay17a() {
        Day17Solver solver = new Day17Solver(20, 30, -10, -5);
        assertEquals(45, solver.solveDay17a());
    }

    @Test
    void solveDay17b() {
        Day17Solver solver = new Day17Solver(20, 30, -10, -5);
        assertEquals(112, solver.solveDay17b());
    }

    @Test
    void calculateTop() {
        Day17Solver solver = new Day17Solver(0, 0, 0, 0);
        assertEquals(0, solver.calculateTopForDeltaY(0, 0));
        assertEquals(1, solver.calculateTopForDeltaY(1, 0));
        assertEquals(3, solver.calculateTopForDeltaY(2, 0));
        assertEquals(6, solver.calculateTopForDeltaY(3, 0));
        assertEquals(10, solver.calculateTopForDeltaY(4, 0));
        assertEquals(15, solver.calculateTopForDeltaY(5, 0));
    }
}