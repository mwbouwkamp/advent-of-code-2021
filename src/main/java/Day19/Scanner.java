package Day19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scanner {
    private final List<PointXYZ> beacons;
    private final List<Integer> beaconHit;
    private final List<PointXYZ> vectors;

    public Scanner(List<String> beaconList) {
        beacons = beaconList.stream()
                .map(beacon -> new PointXYZ(beacon))
                .collect(Collectors.toList());
        beaconHit = beaconList.stream()
                .map(beacon -> 0)
                .collect(Collectors.toList());
        vectors = new ArrayList<>();
        for (int i = 0; i < beacons.size(); i ++) {
            for (int j = i + 1; j < beacons.size(); j++) {
                vectors.add(new PointXYZ(beacons.get(i).getX() - beacons.get(j).getX(),
                        beacons.get(i).getY() - beacons.get(j).getY(),
                        beacons.get(i).getZ() - beacons.get(j).getZ()));
            }
        }
    }

    public List<PointXYZ> getVectorsForOrientation(String orientation) {
        return vectors.stream()
                .map(vector -> vector.getOrientation(orientation))
                .collect(Collectors.toList());
    }

    public List<PointXYZ> getBeacons() {
        return beacons;
    }

    public PointXYZ betBeacon(int index) {
        return beacons.get(index);
    }

    public List<PointXYZ> getVectors() {
        return vectors;
    }

    public void hitBeacon(int index) {
        beaconHit.set(index, beaconHit.get(index) + 1);
    }

}
