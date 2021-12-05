package Day05;

import java.awt.*;

public class Line {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public boolean isStraight() {
        return isHorizontal() || isVertical();
    }

    public boolean isDiagonal() {
        return Math.abs(start.x - end.x) == Math.abs(start.y - end.y);
    }

    public boolean isAscending() {
        return start.x < end.x
                ? end.y > start.y
                : start.y > end.y;
    }

    public boolean isHorizontal() {
        return start.y == end.y;
    }

    public boolean isVertical() {
        return start.x == end.x;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}
