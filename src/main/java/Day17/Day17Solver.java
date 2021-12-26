package Day17;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day17Solver {
    private final int targetXMin;
    private final int targetXMax;
    private final int targetyMin;
    private final int targetyMax;

    public Day17Solver(int targetXMin, int targetXMax, int targetyMin, int targetyMax) {
        this.targetXMin = targetXMin;
        this.targetXMax = targetXMax;
        this.targetyMin = targetyMin;
        this.targetyMax = targetyMax;
    }

    public int solveDay17a() {
        Hit highestY = getHighestY();
        return calculateTopForDeltaY(highestY.deltaY, 0);
    }

    private Hit getHighestY() {
        List<Hit> hitsY = getYHits();
        return hitsY.stream()
                .reduce((a, b) -> a.deltaY > b.deltaY ? a : b)
                .orElse(null);
    }

    // low 1870
    // high 1920
    public long solveDay17b() {
        List<Hit> hitsX = getXHits();
        System.out.println(hitsX);

        List<Hit> hitsY = getYHits();
        System.out.println(hitsY);

        long directHits = 0;

        int maxSteps = hitsX.stream()
                .mapToInt(hit -> hit.steps)
                .max()
                .orElse(-1);




        List<Hit> xyMatches = new ArrayList<>();

        for (int i = 1; i <= maxSteps; i ++) {
            int finalI = i;
            List<Hit> xWithSteps = hitsX.stream()
                    .filter(hit -> hit.steps == finalI)
                    .collect(Collectors.toList());
            List<Hit> newXyMatches = hitsY.stream()
                    .filter(hit -> hit.steps == finalI)
                    .flatMap(hitY -> xWithSteps.stream()
                            .map(hitX -> new Hit(hitX.deltaX, hitY.deltaY, hitX.hitX, hitY.hitY, finalI, false)))
                    .collect(Collectors.toList());
            xyMatches.addAll(newXyMatches.stream()
                    .filter(hit -> xyMatches.stream()
                            .noneMatch(existingHit -> hit.deltaX == existingHit.deltaX && hit.deltaY == existingHit.deltaY))
                    .collect(Collectors.toList()));


//            directHits += numWithCount(hitsX, i) * numWithCount(hitsY, i);
        }

        List<Hit> hitsYWithMoreSteps = hitsY.stream()
                .filter(hit -> hit.steps >= maxSteps)
                .collect(Collectors.toList());

        List<Hit> perpendicular = hitsX.stream()
                .filter(hit -> hit.isPerpendicular)
                .collect(Collectors.toList());

        List<Hit> perpendicularHits = hitsYWithMoreSteps.stream()
                .flatMap(hitY -> perpendicular.stream()
                        .map(hitX -> new Hit(hitX.deltaX, hitY.deltaY, hitX.hitX, hitY.hitY, 0, false)))
                .collect(Collectors.toList());

        xyMatches.addAll(perpendicularHits.stream()
                .filter(hit -> xyMatches.stream()
                        .noneMatch(existingHit -> hit.deltaX == existingHit.deltaX && hit.deltaY == existingHit.deltaY))
                .collect(Collectors.toList()));



//        long numPerpedicular = hitsX.stream()
//                .filter(hit -> hit.isPerpendicular)
//                .count();
//
//        Hit highestY = getHighestY();
//
//        long numHigherY = hitsY.stream()
//                .filter(hit -> hit.steps >= maxSteps)
//                .count();

        System.out.println(xyMatches);
        return xyMatches.size();
    }

    private long numWithCount(List<Hit> hitList, int count) {
        return hitList.stream()
                .filter(hit -> hit.steps == count)
                .count();
    }

    private List<Hit> getYHits() {
        return IntStream.range(targetyMin, targetyMax + 1)
                .mapToObj(n -> {
                    List<Hit> newHits = new ArrayList<>();
                    int y = targetyMin;
                    while (y < 1000) {
                        Hit solution = isYSolution(y, n, 1);
                        if (solution != null) {
                            solution.hitY = n;
                            newHits.add(solution);
                        }
                        y++;
                    }
                    return newHits;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Hit> getXHits() {
        return IntStream.range(targetXMin, targetXMax + 1)
                .mapToObj(n -> {
                    List<Hit> newHits = new ArrayList<>();
                    int x = n;
                    while (x > 0) {
                        Hit solution = isXSolution(x, n, 1);
                        if (solution != null) {
                            solution.hitX = n;
                            newHits.add(solution);
                        }
                        x--;
                    }
                    return newHits;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public int calculateTopForDeltaY(int deltaY, int height) {
        if (deltaY > 0) {
            return (calculateTopForDeltaY(deltaY - 1, height + deltaY));
        } else {
            return height;
        }
    }

    public int calculateTopForSteps(int steps, int height) {
        if (steps > 0) {
            return calculateTopForSteps(steps - 1, height + steps);
        }
        else {
            return height;
        }
    }

    public int calculateMinNumberOfYSteps() {
        int deltaY = 0;
        int step = 1;
        while (deltaY > targetyMax) {
            deltaY -= step;
            step++;
        }
        return step - 1;
    }

    public Hit isXSolution(int x, int n, int step) {
        if (x == n) {
            return new Hit(x, -1, -1, -1, step, x == step);
        }
        if (step > x) {
            return null;
        }
        return isXSolution(x, n - x + step,  step + 1 );
    }

    public Hit isYSolution(int y, int n, int step) {
        if (y == n) {
            return new Hit(-1, y, -1, -1, step, false);
        }
        if (n > y) {
            return null;
        }
        return isYSolution(y, n - y + step,  step + 1 );
    }

    public class Hit {
        private int deltaX;
        private int deltaY;
        private int hitX;
        private int hitY;
        private int steps;
        private boolean isPerpendicular;

        public Hit(int deltaX, int deltaY, int hitX, int hitY, int steps, boolean isPerpendicular) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.hitX = hitX;
            this.hitY = hitY;
            this.steps = steps;
            this.isPerpendicular = isPerpendicular;
        }

        @Override
        public String toString() {
            return "[" + deltaX + "," + deltaY + "] -> [" + hitX + "," + hitY + "]  (" + steps + ")" + (isPerpendicular ? " P" : "");
        }
    }
}
