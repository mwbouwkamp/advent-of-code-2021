package Day21;

public class Day21Solver {
    private Scores scores;
    private int target;

    public Day21Solver(int positionA, int positionB, int target) {
        this.scores = new Scores(0, positionA, 0, positionB);
        this.target = target;
    }

    public int solveDay21a() {
        for (int i = 1; i < 1001; i++) {
            if ((i - 1) % 6 < 3) {
                scores.positionA = (scores.positionA + i) % 10;
                if (scores.positionA == 0) {
                    scores.positionA = 10;
                }
                if (i % 3 == 0) {
                    scores.scoreA += scores.positionA;
                    if (scores.scoreA >= target) {
                        return scores.scoreB * i;
                    }
                }
            } else {
                scores.positionB = (scores.positionB + i) % 10;
                if (scores.positionB == 0) {
                    scores.positionB = 10;
                }
                if (i % 6 == 0) {
                    scores.scoreB += scores.positionB;
                    if (scores.scoreB >= target) {
                        return scores.scoreA * i;
                    }
                }
            }
        }
        return -1;
    }

    public long solveDay21b() {
        int cycle = 0;
        return solve(scores, 1, cycle) + solve(scores, 2, cycle) + solve(scores, 3, cycle);
    }

    public long solve(Scores oldScores, int dieValue, int cycle) {
        cycle++;
        Scores scores = new Scores(oldScores);
        if ((cycle - 1) % 6 < 3) {
            scores.positionA = (scores.positionA + dieValue) % 10;
            if (scores.positionA == 0) {
                scores.positionA = 10;
            }
            if (cycle % 3 == 0) {
                scores.scoreA += scores.positionA;
                if (scores.scoreA >= target) {
                    return 1L;
                }
            }
        } else {
            scores.positionB = (scores.positionB + dieValue) % 10;
            if (scores.positionB == 0) {
                scores.positionB = 10;
            }
            if (cycle % 6 == 0) {
                scores.scoreB += scores.positionB;
                if (scores.scoreB >= target) {
                    return 0L;
                }
            }
        }
        return solve(scores, 1, cycle) + solve(scores, 2, cycle) + solve(scores, 3, cycle);
    }

    public class Scores {
        private int scoreA, positionA;
        private int scoreB, positionB;

        public Scores(int scoreA, int positionA, int scoreB, int positionB) {
            this.scoreA = scoreA;
            this.positionA = positionA;
            this.scoreB = scoreB;
            this.positionB = positionB;
        }

        public Scores(Scores scores) {
            this.scoreA = scores.scoreA;
            this.positionA = scores.positionA;
            this.scoreB = scores.scoreB;
            this.positionB = scores.positionB;
        }

    }

}
