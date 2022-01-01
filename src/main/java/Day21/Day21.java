package Day21;

public class Day21 {
    public static void main(String[] args) {
        Day21Solver solver = new Day21Solver(4, 9, 1000);
        System.out.println(solver.solveDay21a());

        solver = new Day21Solver(4, 8, 21);
        System.out.println(solver.solveDay21b());
    }
}
