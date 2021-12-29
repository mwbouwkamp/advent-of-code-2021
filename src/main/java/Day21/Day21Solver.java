package Day21;

public class Day21Solver {
    private int scoreA, positionA;
    private int scoreB, positionB;

    public Day21Solver(int positionA, int positionB) {
        this.positionA = positionA;
        this.scoreA = 0;
        this.positionB = positionB;
        this.scoreB = 0;
    }

    public int solveDay21a() {
        for (int i = 1; i < 1001; i++) {
            if ((i - 1) % 6 < 3) {
                positionA = (positionA + i) % 10;
                if (positionA == 0) {
                    positionA = 10;
                }
                if (i % 3 == 0) {
                    scoreA += positionA;
                    if (scoreA >= 1000) {
                        return scoreB * i;
                    }
                }
            } else {
                positionB = (positionB + i) % 10;
                if (positionB == 0) {
                    positionB = 10;
                }
                if (i % 6 == 0) {
                    scoreB += positionB;
                    if (scoreB >= 1000) {
                        return scoreA * i;
                    }
                }
            }
        }
        return -1;
    }

}
