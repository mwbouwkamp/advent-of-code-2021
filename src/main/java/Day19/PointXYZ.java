package Day19;

public class PointXYZ {
    private int x;
    private int y;
    private int z;

    public PointXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PointXYZ(String point) {
        String[] coordinates = point.split(",");
        this.x = Integer.parseInt(coordinates[0]);
        this.y = Integer.parseInt(coordinates[1]);
        this.z = Integer.parseInt(coordinates[2]);
    }

    public PointXYZ getOrientation(String orientation) {
        String[] orientationComponents = orientation.split(" ");
        int x = getCoordinateComponent(orientationComponents[0]);
        int y = getCoordinateComponent(orientationComponents[1]);
        int z = getCoordinateComponent(orientationComponents[2]);
        return new PointXYZ(x, y, z);
    }

    private int getCoordinateComponent(String orientationComponent) {
        switch (orientationComponent) {
            case "x":
                return this.x;
            case "-x":
                return - this.x;
            case "y":
                return this.y;
            case "-y":
                return - this.y;
            case "z":
                return this.z;
            case "-z":
                return - this.z;
            default:
                throw new IllegalArgumentException("orientation not supported: " + orientationComponent);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.z + "]";
    }
}
