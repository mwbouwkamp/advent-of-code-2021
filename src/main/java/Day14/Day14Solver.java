package Day14;

import java.util.*;
import java.util.stream.Collectors;

public class Day14Solver {
    private String polymer;
    private final Map<String, String> instructions;
    private Map<String, Long> elements;

    public Day14Solver(List<String> input) {
        this.polymer = input.remove(0);
        input.remove(0);
        this.instructions = input.stream()
                .collect(Collectors.toMap(line -> line.split(" -> ")[0], line -> line.split(" -> ")[1]));
        this.elements = new HashSet<>(instructions.values())
                .stream()
                .collect(Collectors.toMap(element -> element, element -> 0L));
    }

    public long solveDay14a() {
        return solveDay14(10);
    }

    public long solveDay14b() {
        return solveDay14(40);
    }

    public long solveDay14(int cycles) {
        for (int i = 0; i < cycles; i++) {
            System.out.println(i);
            polymer = processCycle();
        }
        elements = elements.keySet().stream()
                .collect(Collectors.toMap(element -> element, element -> {
                    long count = 0;
                    for (char chr: polymer.toCharArray()) {
                        if (element.contains(chr + "")) {
                            count++;
                        }
                    }
                    return count;
                }));
        long max = elements.values().stream()
                .max(Long::compareTo)
                .orElse(-1L);
        long min = elements.values().stream()
                .min(Long::compareTo)
                .orElse(-1L);
        return max - min;
    }

    public String processCycle() {
        StringBuilder polymerBuilder = new StringBuilder();
        for (int i = 0; i < polymer.length() - 1; i++) {
            polymerBuilder.append(polymer.charAt(i));
            int finalI = i;
            polymerBuilder.append(instructions.keySet().stream()
                    .filter(key -> key.equals(polymer.substring(finalI, finalI + 2)))
                    .map(instructions::get)
                    .collect(Collectors.toList())
                    .get(0));
        }
        polymerBuilder.append(polymer.charAt(polymer.length() - 1));
        return polymerBuilder.toString();
    }
}
