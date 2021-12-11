package Day08;

import java.util.*;
import java.util.stream.Collectors;

public class Day08Solver {
    private final List<InputSequence> inputSequenceList;

    public Day08Solver(List<String> input) {
        this.inputSequenceList = input.stream()
                .map(line -> new InputSequence(line))
                .collect(Collectors.toList());
    }

    public long solveDay08a() {
        return inputSequenceList.stream()
                .map(inputSequence -> inputSequence.getOutputValues())
                .flatMap(Collection::stream)
                .filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7)
                .count();
    }

    public long solveDay08b() {
        return inputSequenceList.stream()
                .mapToInt(inputSequence -> inputSequence.solveInputSequence())
                .sum();
    }
}
