package Day17;

public class Day17 {

    public static void main(String[] args) {
        int targetXMin = 241;
        int targetXMax = 273;
        int targetyMin = -97;
        int targetyMax = -63;

        Day17Solver solver = new Day17Solver(241, 273, -97, -63);
        System.out.println(solver.solveDay17a());
        System.out.println(solver.solveDay17b());
    }
}
