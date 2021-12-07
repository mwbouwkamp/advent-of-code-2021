package Day07;

import java.util.List;

public class Day07Solver {
    private final List<Integer> positions;

    public Day07Solver(List<Integer> positions) {
        this.positions = positions;
        this.positions.sort(Integer::compareTo);
    }

    public int solveDay07a() {
        int fuelConsumption = Integer.MAX_VALUE;
        for (int i = 0; i < positions.get(positions.size() - 1); i++) {
            int finalI = i;
            int newFuelConsumption = positions.stream()
                    .map(position -> Math.abs(position - finalI))
                    .reduce(Integer::sum)
                    .orElse(-1);
            if (newFuelConsumption <= fuelConsumption) {
                fuelConsumption = newFuelConsumption;
            } else {
                return fuelConsumption;
            }
        }
        return -1;
    }

    public int solveDay07b() {
        int fuelConsumption = Integer.MAX_VALUE;
        for (int i = 0; i < positions.get(positions.size() - 1); i++) {
            int finalI = i;
            int newFuelConsumption = positions.stream()
                    .map(position -> calcFuel(Math.abs(position - finalI)))
                    .reduce(Integer::sum)
                    .orElse(-1);
            if (newFuelConsumption <= fuelConsumption) {
                fuelConsumption = newFuelConsumption;
            } else {
                return fuelConsumption;
            }
        }
        return -1;
    }

    public int calcFuel(int delta) {
        if (delta == 0) {
            return 0;
        }
        if (delta == 1) {
            return 1;
        }
        return delta + calcFuel(delta - 1);
    }
}
