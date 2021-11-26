package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadPuzzleInput {

    private static final String PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\";

    public List<String> readPuzzleLines(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH + fileName));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        return lines;
    }

    public String readPuzzleSingleLine(String fileName) throws IOException {
        return readPuzzleLines(fileName).get(0);
    }

    public List<Integer> readPuzzleIntegers(String fileName, String spliterator) throws IOException {
        return Arrays.stream(readPuzzleSingleLine(fileName).split(spliterator))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Long> readPuzzleLongs(String fileName, String spliterator) throws IOException {
        return Arrays.stream(readPuzzleSingleLine(fileName).split(spliterator))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Double> readPuzzleDoubles(String fileName, String spliterator) throws IOException {
        return Arrays.stream(readPuzzleSingleLine(fileName).split(spliterator))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<String> readPuzzleStrings(String fileName, String spliterator) throws IOException {
        return new ArrayList<>(Arrays.asList(readPuzzleSingleLine(fileName).split(spliterator)));
    }
}
