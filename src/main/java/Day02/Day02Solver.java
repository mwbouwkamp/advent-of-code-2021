package Day02;

import java.util.List;

public class Day02Solver {

    private final List<String> instructions;
    private long x = 0;
    private long y = 0;

    public Day02Solver(List<String> instructions) {
        this.instructions = instructions;
    }

    public long solveDay02a() {
        reSetCoordinates();
        calcX();
        calcYa();
        return x * y;
    }

    private void calcYa() {
        y = instructions.stream()
                .filter(s -> s.startsWith("up") || s.startsWith("down"))
                .mapToInt(s -> s.startsWith("up") ? - Integer.parseInt(s.substring(3)) : Integer.parseInt(s.substring(5)))
                .sum();
    }

    private void calcX() {
        x = instructions.stream()
                .filter(s -> s.startsWith("forward"))
                .mapToInt(s -> Integer.parseInt(s.substring(8)))
                .sum();
    }

    public void reSetCoordinates() {
        x = 0;
        y = 0;
    }

    public long solveDay02b() {
        reSetCoordinates();
        long aim = 0;
        for (String instruction: instructions) {
            String instructionType = instruction.substring(0, 2);
            switch (instructionType) {
                case "up":
                    aim -= Integer.parseInt(instruction.substring(3));
                    break;
                case "do":
                    aim += Integer.parseInt(instruction.substring(5));
                    break;
                case "fo":
                    int value = Integer.parseInt(instruction.substring(8));
                    x += value;
                    y += value * aim;
                    break;
                default:
                    throw new IllegalArgumentException("instructionType not allowed: " + instructionType);
            }
        }
        return x * y;
    }
}
