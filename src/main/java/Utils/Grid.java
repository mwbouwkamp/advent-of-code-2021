package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid<T> {
    private final List<List<T>> grid;
    private int numRows;
    private int numCols;
    private int xOffset;
    private int yOffset;

    public Grid() {
        this.grid = new ArrayList<>();
    }
    public Grid(ArrayList<List<T>> grid) {
        this.grid = grid;
        setNumRowsNumCols();
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public Grid(List<String> gridLines, String spliterator) {
        this.grid = gridLines.stream()
                .map(line -> Arrays.stream(line.split(spliterator))
                        .map(element -> (T) element)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        setNumRowsNumCols();
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public ArrayList<List<T>> getGrid() {
        return (ArrayList<List<T>>) grid;
    }
    public T getElement(int x, int y) {
        try {
            return grid.get(y + yOffset).get(x + xOffset);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void setElement(int x, int y, T element) {
        addRequiredRowsBefore(y);
        if (-y > yOffset) {
            yOffset = -y;
        }
        addRequiredRowsAfter(y + 1);
        if (x < xOffset) {
            List<T> toAdd = getEmptyListOfSize(xOffset - x);
            grid.forEach(line -> addRequiredElementBefore(line, toAdd));
            xOffset = -x;
        }
        List<T> line = grid.get(y + yOffset);
        addRequiredElementsAfter(x + 1, line);
        line.set(x + xOffset, element);
        setNumRowsNumCols();
    }

    public void insertRow(int y, List<T> row) {
        addRow(y, row, true);
    }

    public void setRow(int y, List<T> row) {
        addRow(y, row, false);
    }

    public void insertColumn(int x, List<T> column) {
        addColumn(x, column, true);
    }

    public void setColumn(int x, List<T> column) {
        addColumn(x, column, false);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    private void addRow(int y, List<T> row, boolean isInsert) {
        addRequiredRowsBefore(y);
        if (-y > yOffset) {
            yOffset = -y;
        }
        if (isInsert) {
            addRequiredRowsAfter(y);
        } else {
            addRequiredRowsAfter(y + 1);
        }
        if (isInsert) {
            grid.add(y + yOffset, row);
        } else {
            grid.set(y + yOffset, row);
        }
        setNumRowsNumCols();
    }

    private void addColumn(int x, List<T> column, boolean isInsert) {
        int numMissingInGrid = - (x - xOffset);
        if (numMissingInGrid > 0) {
            xOffset = -x;
        }
        List<T> elementsToAdd = getEmptyListOfSize(numMissingInGrid);
        for (int i = 0; i < column.size() - grid.size(); i++) {
            if (isInsert) {
                grid.add(getEmptyListOfSize(x));
            } else {
                grid.add(getEmptyListOfSize(x + 1));
            }
        }
        for (int i = 0; i < grid.size(); i++) {
            List<T> line = grid.get(i);
            if (elementsToAdd.size() > 0) {
                addRequiredElementBefore(line, elementsToAdd);
            }
            addRequiredElementsAfter(x, line);
            if (i >= column.size()) {
                if (isInsert) {
                    line.add(x + xOffset, null);
                } else {
                    line.set(x + xOffset, null);
                }
            } else {
                if (isInsert) {
                    line.add(x + xOffset, column.get(i));
                } else {
                    line.set(x + xOffset, column.get(i));
                }
            }
        }
        setNumRowsNumCols();
    }

    private void addRequiredRowsBefore(int y) {
        int numMissingInGrid = - (y - yOffset);
        if (numMissingInGrid > 0) {
            for (int i = 0; i < numMissingInGrid; i++) {
                grid.add(0, new ArrayList<>());
            }
        }
    }

    private void addRequiredRowsAfter(int y) {
        int numMissingInGrid;
        numMissingInGrid = y - grid.size();
        for (int i = 0; i < numMissingInGrid; i++) {
            grid.add(new ArrayList<>());
        }
    }

    private void addRequiredElementsAfter(int x, List<T> line) {
        int numMissingInLine;
        numMissingInLine = x - line.size();
        for (int i = 0; i < numMissingInLine; i++) {
            line.add(null);
        }
    }

    private void addRequiredElementBefore(List<T> line, List<T> toAdd) {
        line.addAll(0, toAdd);
    }

    @Override
    public String toString() {
        return grid.stream()
                .map(line -> line.stream()
                        .map(element -> {
                            if (element == null) {
                                return " ";
                            }
                            return element.toString();
                        })
                        .reduce((a, b) -> a + " " + b)
                        .orElse(""))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");
    }

    private List<T> getEmptyListOfSize(int size) {
        List<T> toReturn = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            toReturn.add(null);
        }
        return toReturn;
    }

    private void setNumRowsNumCols() {
        numRows = grid.size();
        numCols = grid.stream()
                .mapToInt(List::size)
                .boxed()
                .max(Integer::compareTo)
                .orElse(0);
    }
}
