package Day16;

import java.util.*;
import java.util.stream.Collectors;

public class Day16Solver {
    
    Map<String, String> binaries;
    List<List<String>> digits;
    
    public Day16Solver(List<String> input) {
        binaries = new HashMap<>();
        binaries.put("0", "0000");
        binaries.put("1", "0001");
        binaries.put("2", "0010");
        binaries.put("3", "0011");
        binaries.put("4", "0100");
        binaries.put("5", "0101");
        binaries.put("6", "0110");
        binaries.put("7", "0111");
        binaries.put("8", "1000");
        binaries.put("9", "1001");
        binaries.put("A", "1010");
        binaries.put("B", "1011");
        binaries.put("C", "1100");
        binaries.put("D", "1101");
        binaries.put("E", "1110");
        binaries.put("F", "1111");

        digits = input.stream().map(line -> Arrays.stream(line.split(""))
                        .map(s -> Arrays.asList(binaries.get(s).split("")))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
