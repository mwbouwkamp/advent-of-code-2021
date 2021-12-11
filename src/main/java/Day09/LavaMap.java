package Day09;

import Utils.Grid;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class LavaMap extends Grid<Integer> {
    public LavaMap(ArrayList<List<Integer>> input) {
        super(input);
    }

    public boolean isMinimum(int x, int y) {
        if (y != 0) {
            if (getElement(x, y - 1) <= getElement(x, y)) {
                return false;
            }
        }
        if (y != getNumRows() - 1) {
            if (getElement(x, y + 1) <= getElement(x, y)) {
                return false;
            }
        }
        if (x != 0) {
            if (getElement(x - 1, y) <= getElement(x, y)) {
                return false;
            }
        }
        if (x != getNumCols() - 1) {
            if (getElement(x + 1, y) <= getElement(x, y)) {
                return false;
            }
        }
        return true;
    }

    public int getBasinSize(int x, int y) {
        Set<Point> inBasin = new HashSet<>();
        List<Point> fringe = new ArrayList<>();
        fringe.add(new Point(x, y));
        while (fringe.size() > 0) {
            System.out.println(fringe.size());
            Point coordinate = fringe.remove(0);
            inBasin.add(coordinate);
            List<Point> neighbouringPoints = new ArrayList<>();
            if (coordinate.x > 0) {
                neighbouringPoints.add(new Point(coordinate.x - 1, coordinate.y));
            }
            if (coordinate.x < getNumCols() - 1) {
                neighbouringPoints.add(new Point(coordinate.x + 1, coordinate.y));
            }
            if (coordinate.y > 0) {
                neighbouringPoints.add(new Point(coordinate.x, coordinate.y - 1));
            }
            if (coordinate.y < getNumRows() - 1) {
                neighbouringPoints.add(new Point(coordinate.x, coordinate.y + 1));
            }
            fringe.addAll(neighbouringPoints.stream()
                            .filter(point -> getElement(point.x, point.y) != 9)
                    .filter(point -> !inBasin.contains(point))
                    .collect(Collectors.toList()));
            }
        return inBasin.size();
    }
}
