package Day25;

import Utils.Grid;

import java.util.List;

public class Day25Solver {
    Grid<String> map;

    public Day25Solver(List<String> input) {
        this.map = new Grid<>(input, "");
    }

    public int solveDay25a() {
        boolean moved = true;
        int cycle = 0;
        while (moved) {
            System.out.println(cycle);
            cycle++;
            moved = move();
        }
        return cycle;
    }

    private boolean move() {
        boolean moved = false;
        Grid<String> newGrid = new Grid<>();
        for (int y = 0; y < map.getNumRows(); y++) {
            for (int x = 0; x < map.getNumCols(); x++) {
                String element = map.getElement(x, y);
                if (element.equals(">")) { //east
                    boolean crossing = x == map.getNumCols() - 1;
                    String east = crossing ? map.getElement(0, y) : map.getElement(x + 1, y);
                    if (east == null || east.equals(".")) {
                        moved = true;
                        newGrid.setElement(x, y, ".");
                        newGrid.setElement(crossing ? 0 : (x + 1), y, ">");
                    } else {
                        newGrid.setElement(x, y, ">");
                    }
                } else if (element.equals("v")) {
                    newGrid.setElement(x, y, "v");
                } else if (newGrid.getElement(x, y) == null || newGrid.getElement(x, y).equals(".")){
                    newGrid.setElement(x, y, ".");
                }
            }
        }
        map = newGrid;
        newGrid = new Grid<>();
        for (int y = 0; y < map.getNumRows(); y++) {
            for (int x = 0; x < map.getNumCols(); x++) {
                String element = map.getElement(x, y);
                if (element.equals("v")){ //south
                    boolean crossing = y == map.getNumRows() - 1;
                    String south = crossing ? map.getElement(x, 0) : map.getElement(x, y + 1);
                    if (south == null || south.equals(".")) {
                        moved = true;
                        newGrid.setElement(x, y, ".");
                        newGrid.setElement(x, crossing ? 0 : (y + 1), "v");
                    } else {
                        newGrid.setElement(x, y, "v");
                    }
                } else if (element.equals(">")) {
                    newGrid.setElement(x, y, ">");
                } else if (newGrid.getElement(x, y) == null || newGrid.getElement(x, y).equals(".")){
                    newGrid.setElement(x, y, ".");
                }
            }
        }
        map = newGrid;
//        System.out.println(map);
        return moved;
    }
}
