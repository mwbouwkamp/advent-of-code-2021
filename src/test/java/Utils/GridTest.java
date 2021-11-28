package Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GridTest {

    private Grid<String> grid;

    @BeforeEach
    void setUpGrid() {
        List<String> input = new ArrayList<>();
        input.add("11 12 13");
        input.add("21 22 23");

        grid = new Grid<>(input, " ");
    }

    @Test
    void gridCreation() {
        String expected = "" +
                "11 12 13\n" +
                "21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(2, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void insertRowAtMinus1() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.insertRow(-1, newRow);
        String expected = "" +
                "r1 r2\n" +
                "\n" +
                "11 12 13\n" +
                "21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals("13", grid.getElement(2, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(1, grid.getyOffset());
    }

    @Test
    void insertRowAt1() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.insertRow(1, newRow);
        String expected = "" +
                "11 12 13\n" +
                "r1 r2\n" +
                "21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals("r2", grid.getElement(1, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void insertRowAt3() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.insertRow(3, newRow);
        String expected = "" +
                "11 12 13\n" +
                "21 22 23\n" +
                "\n" +
                "r1 r2";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void insertColumnAtMinus1() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.insertColumn(-1, newCol);
        String expected = "" +
                "c1 null 11 12 13\n" +
                "c2 null 21 22 23\n" +
                "c3 null";
        assertEquals(expected, grid.toString());
        assertEquals("22", grid.getElement(2, 1));
        assertEquals(5, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(1, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void insertColumnAt1() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.insertColumn(1, newCol);
        String expected = "" +
                "11 c1 12 13\n" +
                "21 c2 22 23\n" +
                "null c3";
        assertEquals(expected, grid.toString());
        assertEquals("22", grid.getElement(2, 1));
        assertEquals(4, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void insertColumnAt5() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.insertColumn(5, newCol);
        String expected = "" +
                "11 12 13 null null c1\n" +
                "21 22 23 null null c2\n" +
                "null null null null null c3";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(6, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setRowAtMinus1() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.setRow(-1, newRow);
        String expected = "" +
                "r1 r2\n" +
                "11 12 13\n" +
                "21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals(grid.getElement(2, 1), "23");
        assertEquals(3, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(1, grid.getyOffset());
    }

    @Test
    void setRowAt1() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.setRow(1, newRow);
        String expected = "" +
                "11 12 13\n" +
                "r1 r2";
        assertEquals(expected, grid.toString());
        assertEquals(grid.getElement(1, 1), "r2");
        assertEquals(3, grid.getNumCols());
        assertEquals(2, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setRowAt3() {
        List<String> newRow = new ArrayList<>(Arrays.asList("r1", "r2"));
        grid.setRow(3, newRow);
        String expected = "" +
                "11 12 13\n" +
                "21 22 23\n" +
                "\n" +
                "r1 r2";
        assertEquals(expected, grid.toString());
        assertEquals(grid.getElement(2, 1), "23");
        assertEquals(3, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setColumnAtMinus1() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.setColumn(-1, newCol);
        String expected = "" +
                "c1 11 12 13\n" +
                "c2 21 22 23\n" +
                "c3";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(4, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(1, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setColumnAt1() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.setColumn(1, newCol);
        String expected = "" +
                "11 c1 13\n" +
                "21 c2 23\n" +
                "null c3";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setColumnAt5() {
        List<String> newCol = new ArrayList<>(Arrays.asList("c1", "c2", "c3"));
        grid.insertColumn(5, newCol);
        String expected = "" +
                "11 12 13 null null c1\n" +
                "21 22 23 null null c2\n" +
                "null null null null null c3";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(6, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setElementInGrid() {
        grid.setElement(2, 1, "nn");
        String expected = "" +
                "11 12 13\n" +
                "21 22 nn";
        assertEquals(expected, grid.toString());
        assertEquals("nn", grid.getElement(2, 1));
        assertEquals(3, grid.getNumCols());
        assertEquals(2, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setElementTopRightGrid() {
        grid.setElement(5, -1, "nn");
        String expected = "" +
                "null null null null null nn\n" +
                "11 12 13\n" +
                "21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(6, grid.getNumCols());
        assertEquals(3, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(1, grid.getyOffset());
    }

    @Test
    void setElementBottomRightGrid() {
        grid.setElement(5, 3, "nn");
        String expected = "" +
                "11 12 13\n" +
                "21 22 23\n" +
                "\n" +
                "null null null null null nn";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(6, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(0, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setElementBottomLeftGrid() {
        grid.setElement(-2, 3, "nn");
        String expected = "" +
                "null null 11 12 13\n" +
                "null null 21 22 23\n" +
                "null null\n" +
                "nn null";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(5, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(2, grid.getxOffset());
        assertEquals(0, grid.getyOffset());
    }

    @Test
    void setElementTopLeftGrid() {
        grid.setElement(-2, -2, "nn");
        String expected = "" +
                "nn null\n" +
                "null null\n" +
                "null null 11 12 13\n" +
                "null null 21 22 23";
        assertEquals(expected, grid.toString());
        assertEquals("23", grid.getElement(2, 1));
        assertEquals(5, grid.getNumCols());
        assertEquals(4, grid.getNumRows());
        assertEquals(2, grid.getxOffset());
        assertEquals(2, grid.getyOffset());
    }
}