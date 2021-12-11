package Day10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10Solver {
    private final List<String> incompleteLines;
    private final List<String> incorrectLines;

    public Day10Solver(List<String> lines) {
        lines = lines.stream()
                .map(this::removeBrackets)
                .collect(Collectors.toList());
        this.incompleteLines = lines.stream()
                .filter(line -> Stream.of(line.indexOf(')'), line.indexOf(']'), line.indexOf('}'), line.indexOf('>'))
                        .map(n -> n == -1 ? Integer.MAX_VALUE : n)
                        .min(Integer::compareTo).orElse(-1) == Integer.MAX_VALUE)
                .collect(Collectors.toList());
        this.incorrectLines = lines.stream()
                .filter(line -> !incompleteLines.contains(line))
                .collect(Collectors.toList());
    }

    //364389
    public int solveDay10a() {
        return incorrectLines.stream()
                .map(line ->
                    Stream.of(line.indexOf(')'), line.indexOf(']'), line.indexOf('}'), line.indexOf('>'))
                            .map(n -> n == -1 ? Integer.MAX_VALUE : n)
                            .collect(Collectors.toList()))
                .map(list -> {
                    int min = list.stream().min(Integer::compareTo).orElse(-1);
                    if (min == Integer.MAX_VALUE) {
                        return 0;
                    }
                    return list.get(0) == min
                            ? 3
                            : list.get(1) == min
                            ? 57
                            : list.get(2) == min
                            ? 1197
                            : 25137;
                })
                .reduce(Integer::sum)
                .orElse(-1);
    }

    // 1592 low
    // 368705073472 high
    // 327786421968 incorrect
    // 69744813 incorrect
    public long solveDay10b() {
        String brackets = " ([{<";
        List<String> cleanedup = incompleteLines.stream()
                .map(line -> line.replaceAll("X", ""))
                .peek(System.out::println)
                .map(line -> {
                    StringBuilder reversed = new StringBuilder();
                    for (int i = 0; i < line.length(); i++) {
                        reversed.append(line.charAt(line.length() - 1 - i));
                    }
                    return reversed.toString();
                })
                .peek(System.out::println)
                .collect(Collectors.toList());
        List<Long> scores = cleanedup.stream()
                .mapToLong(line -> Arrays.stream(line.split(""))
                        .mapToLong(brackets::indexOf)
                        .reduce(0, (a, b) -> a * 5 + b))
                .boxed()
                .sorted(Long::compareTo)
                .collect(Collectors.toList());
        return scores.get(scores.size() / 2);
    }

    private String removeBrackets(String line) {
        for (int i = 0; i < line.length(); i += 2) {
            String searchString = IntStream.range(0, i)
                    .mapToObj(n -> "X")
                    .reduce((a, b) -> a + b)
                    .orElse("");
            String replaceString = searchString + "XX";
            String regexA = "\\{("+ searchString +")}";
            String regexB = "\\[("+ searchString +")]";
            String regexC = "\\(("+ searchString +")\\)";
            String regexD = "<("+ searchString +")>";
            line = line.replaceAll(regexA, replaceString)
                    .replaceAll(regexB, replaceString)
                    .replaceAll(regexC, replaceString)
                    .replaceAll(regexD, replaceString);
        }
        return line;
    }
}
