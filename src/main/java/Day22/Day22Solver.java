package Day22;

import Day19.PointXYZ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day22Solver {
    private List<Instruction> instructions;

    public Day22Solver(List<String> input) {
        this.instructions = input.stream()
                .map(instruction -> {
                    instruction = instruction.replace("x=", "")
                            .replace("y=", "")
                            .replace("z=", "")
                            .replace("..", "T");
                    String action = instruction.split(" ")[0];
                    String[] ranges = instruction.split(" ")[1].split(",");
                    List<Integer> rangeX = Arrays.stream(ranges[0].split("T"))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList());
                    List<Integer> rangeY = Arrays.stream(ranges[1].split("T"))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList());
                    List<Integer> rangeZ = Arrays.stream(ranges[2].split("T"))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList());
                    return new Instruction(new Box(rangeX.get(0), rangeX.get(1), rangeY.get(0), rangeY.get(1), rangeZ.get(0), rangeZ.get(1)), action);
                })
                .collect(Collectors.toList());
    }

    public long solveDay22a(boolean useRange) {
        List<Box> processedBoxes = new ArrayList<>();
        while (instructions.size() > 0) {
            Instruction instruction = instructions.remove(0);
            System.out.println(instruction.getInstruction() + " " + instruction.getBox());
            List<Box> newProcessedBoxes = new ArrayList<>();
            processedBoxes.forEach(box -> {
                boolean xOverlap = box.getMax().getX() >= instruction.getBox().getMin().getX() || box.getMin().getX() <= instruction.getBox().getMax().getX();
                boolean yOverlap = box.getMax().getY() >= instruction.getBox().getMin().getY() || box.getMin().getY() <= instruction.getBox().getMax().getY();
                boolean zOverlap = box.getMax().getZ() >= instruction.getBox().getMin().getZ() || box.getMin().getZ() <= instruction.getBox().getMax().getZ();
                if (!(xOverlap && yOverlap && zOverlap)) {
                    newProcessedBoxes.add(box);
                }
                List<Box>[] restultingBoxes = box.intersect(instruction.getBox());
                newProcessedBoxes.addAll(restultingBoxes[1]); // no overlap
            });
            if (instruction.getInstruction().equals("on")) {
                newProcessedBoxes.add(instruction.getBox());
            }
            processedBoxes = newProcessedBoxes;
        }
        if (useRange) {
            Box range = new Box(-50, 50, -50, 50, -50, 50);
            List<Box> inRange = processedBoxes.stream()
                    .map(box -> box.intersect(range)[0])
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            return inRange.stream()
                    .mapToLong(box -> box.getVolume())
                    .peek(System.out::println)
                    .reduce(Long::sum)
                    .orElse(-1);
        }
        return processedBoxes.stream()
                .mapToLong(box -> box.getVolume())
                .peek(System.out::println)
                .reduce(Long::sum)
                .orElse(-1);
    }
}



//public class Day22Solver {
//    private final List<String> instructions;
//    private List<PointXYZ> on;
//
//    public Day22Solver(List<String> instructions) {
//        this.instructions = instructions;
//        this.on = new ArrayList<>();
//    }
//
//
//    public long solveDay22a(int Xmin, int Xmax, int Ymin, int Ymax, int Zmin, int Zmax) {
//        instructions.forEach(instruction -> {
//            System.out.println(instruction);
//            instruction = instruction.replace("x=", "")
//                    .replace("y=", "")
//                    .replace("z=", "")
//                    .replace("..", "T");
//            String action = instruction.split(" ")[0];
//            String[] ranges = instruction.split(" ")[1].split(",");
//            List<Integer> rangeX = Arrays.stream(ranges[0].split("T"))
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//            List<Integer> rangeY = Arrays.stream(ranges[1].split("T"))
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//            List<Integer> rangeZ = Arrays.stream(ranges[2].split("T"))
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//            for (int x = rangeX.get(0); x <= rangeX.get(1); x++) {
//                for (int y = rangeY.get(0); y <= rangeY.get(1); y++) {
//                    for (int z = rangeZ.get(0); z <= rangeZ.get(1); z++) {
//                        PointXYZ newPoint;
//                        switch (action) {
//                            case "on":
//                                newPoint = new PointXYZ(x, y, z);
//                                if (!containsPoint(newPoint)) {
//                                    on.add(newPoint);
//                                }
//                                break;
//                            case "off":
//                                newPoint = new PointXYZ(x, y, z);
//                                on = on.stream()
//                                        .filter(point -> point.getX() != newPoint.getX() && point.getY() != newPoint.getY() && point.getZ() != newPoint.getZ())
//                                        .collect(Collectors.toList());
//                                break;
//                            default:
//                                throw new UnsupportedOperationException("action not allowed: " + action);
//                        }
//                    }
//                }
//            }
//        });
//        return on.stream()
//                .filter(point -> point.getX() >= Xmin &&
//                        point.getX() <= Xmax &&
//                        point.getX() >= Ymin &&
//                        point.getY() <= Ymax &&
//                        point.getZ() >= Zmax &&
//                        point.getZ() <= Zmax)
//                .count();
//    }
//
//    private boolean containsPoint(PointXYZ pointToCheck) {
//        return on.stream()
//                .anyMatch(point -> point.getX() == pointToCheck.getX() && point.getY() == pointToCheck.getY() && point.getZ() == pointToCheck.getZ());
//
//    }

