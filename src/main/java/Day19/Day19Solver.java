package Day19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day19Solver {
    private final List<Scanner> scanners;
    private List<Overlap> overlaps;

    public Day19Solver(List<String> input) {
        List<List<String>> scannerInputList = new ArrayList();
        List<String> scannerInput = new ArrayList<>();
        while (input.size() > 0) {
            String line = input.remove(0);
            if (line.startsWith("---")) {
                scannerInput = new ArrayList<>();
            } else if (line.length() > 0) {
                scannerInput.add(line);
            } else {
                scannerInputList.add(scannerInput);
            }
        }
        scanners = scannerInputList.stream()
                .map(list -> new Scanner(list))
                .collect(Collectors.toList());
    }

    public int solveDay19a() {
        List<Overlap> overlaps = new ArrayList<>();
        for (int i = 0; i < scanners.size(); i++) {
            for (int j = i + 1; j < scanners.size(); j++) {
                List<Overlap> overlapsCombination = findOverlaps(scanners.get(i), scanners.get(j));
                if (overlapsCombination != null) {
                    overlaps.addAll(overlapsCombination);
                }
            }
        }
        System.out.println(overlaps);
        overlaps.forEach(overlap -> {
            overlap.getScanner1().hitBeacon(overlap.getScanner1Index());
            overlap.getScanner2().hitBeacon(overlap.getScanner2Index());
        });
        return -1;
    }

    public List<Overlap> findOverlaps(Scanner scanner1, Scanner scanner2) {
        List<String> orientatoins = new ArrayList<>();
        orientatoins.add("x y z");
        orientatoins.add("-y x z");
        orientatoins.add("-x -y z");
        orientatoins.add("y -x z");
        orientatoins.add("x y -z");
        orientatoins.add("-y x -z");
        orientatoins.add("-x -y -z");
        orientatoins.add("y -x -z");
        orientatoins.add("z y x");
        orientatoins.add("-y z x");
        orientatoins.add("-z -y x");
        orientatoins.add("y -z x");
        orientatoins.add("z y -x");
        orientatoins.add("-y z -x");
        orientatoins.add("-z -y -x");
        orientatoins.add("y -z -x");
        orientatoins.add("x z y");
        orientatoins.add("-z x y");
        orientatoins.add("-x -z y");
        orientatoins.add("z -x y");
        orientatoins.add("x z -y");
        orientatoins.add("-z x -y");
        orientatoins.add("-x -z -y");
        orientatoins.add("z -x -y");

        List<PointXYZ> vectors1 = scanner1.getVectors();
        for (String orientation: orientatoins) {
            List<PointXYZ> vectors2 = scanner2.getVectorsForOrientation(orientation);
            List<Overlap> overlapsCombination = new ArrayList<>();
            for (int k = 0; k < vectors1.size(); k++) {
                for (int l = 0; l < vectors2.size(); l++) {
                    if ((vectors1.get(k).getX() == vectors2.get(l).getX() || (vectors1.get(k).getX() == - vectors2.get(l).getX()) &&
                            (vectors1.get(k).getY() == vectors2.get(l).getY() || vectors1.get(k).getY() == - vectors2.get(l).getY()) &&
                            (vectors1.get(k).getZ() == vectors2.get(l).getZ() || vectors1.get(k).getZ() == - vectors2.get(l).getZ()))) {
                        overlapsCombination.add(new Overlap(scanner1, k, "x y z", scanner2, l, orientation));
                    }
                }
            }
            if (overlapsCombination.size() >= 66) {
                System.out.println(overlapsCombination.size());
                return overlapsCombination;
            }
        }
        return null;
    }
}
