package Utils;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadPuzzleInputTest {

    @org.junit.jupiter.api.Test
    void readPuzzleLines() throws IOException {
        List<String> result = new ReadPuzzleInput().readPuzzleLines("testInputLines.txt");
        assertEquals(3, result.size());
        assertEquals("abc", result.toArray()[0]);
        assertEquals("def", result.toArray()[1]);
        assertEquals("ghi", result.toArray()[2]);
    }

    @org.junit.jupiter.api.Test
    void readPuzzleSingleLine() throws IOException {
        String result = new ReadPuzzleInput().readPuzzleSingleLine("testInputSingleLine.txt");
        assertEquals("abc", result);
    }

    @org.junit.jupiter.api.Test
    void readPuzzleIntegers() throws IOException {
        List<Integer> result = new ReadPuzzleInput().readPuzzleIntegers("testInputIntegers.txt", " ");
        assertEquals(4, result.size());
        assertEquals(0, result.toArray()[0]);
        assertEquals(1, result.toArray()[1]);
        assertEquals(-1, result.toArray()[2]);
        assertEquals(123, result.toArray()[3]);
    }

    @org.junit.jupiter.api.Test
    void readPuzzleLongs() throws IOException {
        List<Long> result = new ReadPuzzleInput().readPuzzleLongs("testInputLongs.txt", ",");
        assertEquals(3, result.size());
        assertEquals(111111111111L, result.toArray()[0]);
        assertEquals(0L, result.toArray()[1]);
        assertEquals(-111111111111L, result.toArray()[2]);
    }

    @org.junit.jupiter.api.Test
    void readPuzzleDoubles() throws IOException {
        List<Double> result = new ReadPuzzleInput().readPuzzleDoubles("testInputDoubles.txt", ";");
        assertEquals(3, result.size());
        assertEquals(0.1, result.toArray()[0]);
        assertEquals(1.0, result.toArray()[1]);
        assertEquals(-1.1, result.toArray()[2]);
    }
    @org.junit.jupiter.api.Test
    void readPuzzleStrings() throws IOException {
        List<String> result = new ReadPuzzleInput().readPuzzleStrings("testInputStrings.txt", ",");
        assertEquals(3, result.size());
        assertEquals("abc", result.toArray()[0]);
        assertEquals("def", result.toArray()[1]);
        assertEquals("ghi", result.toArray()[2]);
    }
}