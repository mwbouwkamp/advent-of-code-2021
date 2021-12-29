package Day20;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pixel {
    private final Pixel[] neighbours;
    private final int[] values;
    private final Point coordinate;

    public Pixel(String coordinate, int value, int cycle) {
        this.coordinate = new Point(Integer.parseInt(coordinate.split(",")[0]), Integer.parseInt(coordinate.split(",")[1]));
        this.values = new int[2];
        this.values[cycle % 2] = value;
        this.neighbours = new Pixel[9];
    }

    public void setNeighbours(Map<String, Pixel> image) {
        int counter = 0;
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                neighbours[counter] = image.get((coordinate.x + x) + "," + (coordinate.y + y));
                counter++;
            }
        }
    }

    public Map<String, Pixel> enhance(List<Integer> algorithm, int cycle, int defaultColor) {
        int factor = 1;
        int index = 8;
        int binary = 0;
        Map<String, Pixel> newPixels = new HashMap<>();
        for (int y = 1; y > -2; y--) {
            for (int x = 1; x > -2; x--) {
                Pixel neighbour = neighbours[index];
                binary += neighbour != null
                        ? factor * neighbours[index].getValue(cycle)
                        : factor * defaultColor;
                index--;
                factor *= 2;
                if (neighbour == null) {
                    String newCoordinate = (coordinate.x + x) + "," + (coordinate.y + y);
                    newPixels.put(newCoordinate, new Pixel(newCoordinate, defaultColor, cycle + 1));
                }
            }
        }
        values[(cycle + 1) % 2] = algorithm.get(binary);
        return newPixels;
    }

    public int getValue(int cycle) {
        return values[cycle % 2];
    }
}
