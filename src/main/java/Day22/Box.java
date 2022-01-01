package Day22;

import Day19.PointXYZ;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private final PointXYZ min, max;

    public Box(int Xmin, int Xmax, int Ymin, int Ymax, int Zmin, int Zmax) {
        this.min = new PointXYZ(Xmin, Ymin, Zmin);
        this.max = new PointXYZ(Xmax, Ymax, Zmax);
    }

    public long getVolume() {
        return ((max.getX() - min.getX() + 1L) * (max.getY() - min.getY() + 1L) * (max.getZ() - min.getZ() + 1L));
    }

    public PointXYZ getMax() {
        return max;
    }

    public PointXYZ getMin() {
        return min;
    }

    public List<Box>[] intersect(Box otherBox) {
        List<Box> overlap = new ArrayList<>();
        List<Box>[] xIntersects = intersectX(otherBox);
        List<Box> noOverlap = new ArrayList<>(xIntersects[1]); // add no overlaps
        for (Box xIntersect: xIntersects[0]) {
            List<Box>[] yIntersects = xIntersect.intersectY(otherBox);
            noOverlap.addAll(yIntersects[1]); // add no overlaps
            for (Box yIntersect: yIntersects[0]) {
                List<Box>[] zIntersects = yIntersect.intersectZ(otherBox);
                overlap.addAll(zIntersects[0]); // add overlaps
                noOverlap.addAll(zIntersects[1]); // add no overlaps
            }
        }
        List<Box>[] newBoxes = new ArrayList[2];
        newBoxes[0] = overlap;
        newBoxes[1] = noOverlap;
        return newBoxes;
    }

    public List<Box>[] intersectX(Box otherBox) {
        List<Box> overlap = new ArrayList<>();
        List<Box> noOverlap = new ArrayList<>();
        if (otherBox.max.getX() < this.min.getX() || otherBox.min.getX() > this.max.getX()) {
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getX() <= this.min.getX() && otherBox.max.getX() >= this.max.getX()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getX() <= this.min.getX()) {
            overlap.add(new Box(this.min.getX(), otherBox.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(otherBox.max.getX() + 1, this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.max.getX() >= this.max.getX()) {
            overlap.add(new Box(otherBox.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), otherBox.min.getX() - 1, this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getX() > this.min.getX() && otherBox.max.getX() < this.max.getX()) {
            overlap.add(new Box(otherBox.min.getX(), otherBox.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), otherBox.min.getX() - 1, this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(otherBox.max.getX() + 1, this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else {
            System.out.println("ERROR");
        }
        List<Box>[] newBoxes = new ArrayList[2];
        newBoxes[0] = overlap;
        newBoxes[1] = noOverlap;
        return newBoxes;
    }

    public List<Box>[] intersectY(Box otherBox) {
        List<Box> overlap = new ArrayList<>();
        List<Box> noOverlap = new ArrayList<>();
        if (otherBox.max.getY() < this.min.getY() || otherBox.min.getY() > this.max.getY()) {
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getY() <= this.min.getY() && otherBox.max.getY() >= this.max.getY()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getY() <= this.min.getY()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), otherBox.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), otherBox.max.getY() + 1, this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.max.getY() >= this.max.getY()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), otherBox.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), otherBox.min.getY() - 1, this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getY() > this.min.getY() && otherBox.max.getY() < this.max.getY()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), otherBox.min.getY(), otherBox.max.getY(), this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), otherBox.min.getY() - 1, this.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), otherBox.max.getY() + 1, this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else {
            System.out.println("ERROR");
        }
        List<Box>[] newBoxes = new ArrayList[2];
        newBoxes[0] = overlap;
        newBoxes[1] = noOverlap;
        return newBoxes;
    }

    public List<Box>[] intersectZ(Box otherBox) {
        List<Box> overlap = new ArrayList<>();
        List<Box> noOverlap = new ArrayList<>();
        if (otherBox.max.getZ() < this.min.getZ() || otherBox.min.getZ() > this.max.getZ()) {
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getZ() <= this.min.getZ() && otherBox.max.getZ() >= this.max.getZ()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), this.max.getZ()));
        } else if (otherBox.min.getZ() <= this.min.getZ()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), otherBox.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), otherBox.max.getZ() + 1, this.max.getZ()));
        } else if (otherBox.max.getZ() >= this.max.getZ()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), otherBox.min.getZ(), this.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), otherBox.min.getZ() - 1));
        } else if (otherBox.min.getZ() > this.min.getZ() && otherBox.max.getZ() < this.max.getZ()) {
            overlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), otherBox.min.getZ(), otherBox.max.getZ()));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), this.min.getZ(), otherBox.min.getZ() - 1));
            noOverlap.add(new Box(this.min.getX(), this.max.getX(), this.min.getY(), this.max.getY(), otherBox.max.getZ() + 1, this.max.getZ()));
        } else {
            System.out.println("ERROR");
        }
        List<Box>[] newBoxes = new ArrayList[2];
        newBoxes[0] = overlap;
        newBoxes[1] = noOverlap;
        return newBoxes;
    }

    @Override
    public String toString() {
        return min + "-> " + max;
    }
}
