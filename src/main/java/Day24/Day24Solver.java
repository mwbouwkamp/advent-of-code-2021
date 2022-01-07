package Day24;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Day24Solver {
    private Map<Integer, ArrayList<String>> instructionsMap;
    private Map<String, long[]> partialsMap;
//    private Map<String, long[]> solutionsMap;
    private Solutions solutions;
    private List<String> instructions;

    public Day24Solver(List<String> instructions) {
        this.instructions = instructions;
        this.instructionsMap = new TreeMap<>();
        this.partialsMap = new TreeMap<>();
        this.solutions = new Solutions();
        for (int i = 0; i < 14; i++) {
            instructionsMap.put(i, new ArrayList<>());
        }
        int inpCounter = -1;
        while (instructions.size() > 0) {
            String instruction = instructions.remove(0);
            if (instruction.startsWith("inp")) {
                inpCounter++;
            }
            instructionsMap.get(inpCounter).add(instruction);
        }
    }

    public String alternativeSolveDay2a() {
        String inputs = "abcdefghijklmn";
        int inputCoutner = 0;
        String w = "0";
        String x = "0";
        String y = "0";
        String z = "0";
        for (String instruction: instructions) {
//            System.out.println(instruction + "  |  " + z);
            String[] instructionsParts = instruction.split("\\s");
            String toComp = getToComp(w, x, y, z, instructionsParts);
            switch (instructionsParts[0]) {
                case "inp":
                    switch (instructionsParts[1]) {
                        case "w":
                            w = inputs.charAt(inputCoutner) + "";
                            inputCoutner++;
                            break;
                        case "x":
                            x = inputs.charAt(inputCoutner) + "";
                            inputCoutner++;
                            break;
                        case "y":
                            y = inputs.charAt(inputCoutner) + "";
                            inputCoutner++;
                            break;
                        case "z":
                            z = inputs.charAt(inputCoutner) + "";
                            inputCoutner++;
                            break;
                        default:
                            break;

                    }
                    break;
                case "add":
                    if (!toComp.equals("0")) {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "(" + w + "+" + toComp + ")";
                                break;
                            case "x":
                                x = "(" + x + "+" + toComp + ")";
                                break;
                            case "y":
                                y = "(" + y + "+" + toComp + ")";
                                break;
                            case "z":
                                z = "(" + z + "+" + toComp + ")";
                                break;
                            default:
                                break;
                        }
                        break;
                    } else {
                        break;
                    }
                case "mul":
                    if (toComp.equals("0")) {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "0";
                                break;
                            case "x":
                                x = "0";
                                break;
                            case "y":
                                y = "0";
                                break;
                            case "z":
                                z = "0";
                                break;
                            default:
                                break;
                        }
                        break;
                    } else {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "(" + w + "*" + toComp + ")";
                                break;
                            case "x":
                                x = "(" + x + "*" + toComp + ")";
                                break;
                            case "y":
                                y = "(" + y + "*" + toComp + ")";
                                break;
                            case "z":
                                z = "(" + z + "*" + toComp + ")";
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                case "div":
                    if (!toComp.equals("1")) {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "(" + w + "/" + toComp + ")";
                                break;
                            case "x":
                                x = "(" + x + "/" + toComp + ")";
                                break;
                            case "y":
                                y = "(" + y + "/" + toComp + ")";
                                break;
                            case "z":
                                z = "(" + z + "/" + toComp + ")";
                                break;
                            default:
                                break;
                        }
                        break;
                    } else {
                        break;
                    }
                case "mod":
                    switch (instructionsParts[1]) {
                        case "w":
                            w = "(" + w + "%" + toComp + ")";
                            break;
                        case "x":
                            x = "(" + x + "%" + toComp + ")";
                            break;
                        case "y":
                            y = "(" + y + "%" + toComp + ")";
                            break;
                        case "z":
                            z = "(" + z + "%" + toComp + ")";
                            break;
                        default:
                            break;
                    }
                    break;
                case "eql":
                    if (instructionsParts[1].equals(toComp)) {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "1";
                                break;
                            case "x":
                                x = "1";
                                break;
                            case "y":
                                y = "1";
                                break;
                            case "z":
                                z = "1";
                                break;
                            default:
                                break;
                        }
                        break;
                    } else {
                        switch (instructionsParts[1]) {
                            case "w":
                                w = "(" + w + "==" + toComp + ")";
                                break;
                            case "x":
                                x = "(" + x + "==" + toComp + ")";
                                break;
                            case "y":
                                y = "(" + y + "==" + toComp + ")";
                                break;
                            case "z":
                                z = "(" + z + "==" + toComp + ")";
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                default:
                    break;
            }
        }
        return z
                .replaceAll("\\(.*?\\)", "n")
//                .replace("(0)", "0")
//                .replace("(a)", "a")
//                .replace("(b)", "b")
//                .replace("(c)", "c")
//                .replace("(d)", "d")
//                .replace("(e)", "e")
//                .replace("(f)", "f")
//                .replace("(g)", "g")
//                .replace("(h)", "h")
//                .replace("(i)", "i")
//                .replace("(j)", "j")
//                .replace("(k)", "k")
//                .replace("(l)", "l")
//                .replace("(m)", "m")
//                .replace("(n)", "n")
//                .replace("(0+a)", "a")
//                .replace("(0+b)", "b")
//                .replace("(0+c)", "c")
//                .replace("(0+d)", "d")
//                .replace("(0+e)", "e")
//                .replace("(0+f)", "f")
//                .replace("(0+g)", "g")
//                .replace("(0+h)", "h")
//                .replace("(0+i)", "i")
//                .replace("(0+j)", "j")
//                .replace("(0+k)", "k")
//                .replace("(0+l)", "l")
//                .replace("(0+m)", "m")
//                .replace("(0+n)", "n")
                ;
    }

    private String getToComp(String w, String x, String y, String z, String[] instructionsParts) {
        if (instructionsParts.length < 3) {
            return "";
        }
        String toComp = instructionsParts[2];
        switch (instructionsParts[2]) {
            case "w":
                toComp = w;
                break;
            case "x":
                toComp = x;
                break;
            case "y":
                toComp = y;
                break;
            case "z":
                toComp = z;
                break;
        }
        return toComp;
    }

    public long solveDay24a() {
        long[] startPoint = new long[] {0, 0, 0, 0};
        List<State> fringe = new ArrayList<>();
        fringe.add(new State(startPoint, "", instructionsMap, partialsMap));
        boolean buildPhase = false;
        while (fringe.size() > 0) {
            State stateToCheck;
            if (buildPhase) {
                stateToCheck = fringe.remove(0);
                if (stateToCheck.digits.length() > 6) {
                    buildPhase = false;
                    fringe = new ArrayList<>();
                    stateToCheck = new State(startPoint, "", instructionsMap, partialsMap);
                }
            } else {
                stateToCheck = fringe.remove(fringe.size() - 1);
            }
            System.out.println(stateToCheck.digits);
            if (stateToCheck.point[3] == 0 && stateToCheck.digits.length() == 14) {
                return Long.parseLong(stateToCheck.digits);
            }
            if (stateToCheck.digits.length() < 14) {
                fringe.addAll(stateToCheck.getChildren(buildPhase));
            }
        }
        return -1L;
    }

    private class State {
        final long[] point;
        final String digits;
        final Map<Integer, ArrayList<String>> instructionsMap;
        final Map<String, long[]> partialsMap;

        State(long[] point, String digits, Map<Integer, ArrayList<String>> instructionsMap, Map<String, long[]> partialsMap) {
            this.point = point;
            this.digits = digits;
            this.instructionsMap = instructionsMap;
            this.partialsMap = partialsMap;
        }

        List<State> getChildren(boolean addToPartials) {
            List<State> children = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                String key = point[0] + "," + point[1] + "," + point[2] + "," + point[3];
                long[] solutionPoint = solutions.getSolution(digits.length(), i, key);
                long[] newPoint;
                if (solutionPoint != null) {
                    newPoint = Arrays.copyOf(solutionPoint, solutionPoint.length);
//                    System.out.println("Solution");
                } else {
                    long[] calculatedPoint = performInstructions(point, i + "", instructionsMap.get(digits.length()));
                    newPoint = Arrays.copyOf(calculatedPoint, calculatedPoint.length);
                    solutions.setSolution(digits.length(), i, key, newPoint);
                }
//                if (!solutionsMap.keySet().contains(key)) {
//                    newPoint = performInstructions(point, i + "", instructionsMap.get(digits.length()));
//                    solutionsMap.put(key, Arrays.copyOf(newPoint, newPoint.length));
//                } else {
////                    System.out.println("solution used");
//                    long[] solutionsPoint = solutionsMap.get(key);
//                    newPoint = Arrays.copyOf(solutionsPoint, solutionsPoint.length);
//                }
                if (addToPartials) {
                    partialsMap.put(digits + i, newPoint);
                }
                children.add(new State(newPoint, digits + i, instructionsMap, partialsMap));
            }
            return children;
        }
    }

    class Solutions {
        final Map<Integer, Map<Integer, Map<String, long[]>>> solutions; // length, digit, point

        Solutions() {
            solutions = new TreeMap<>();
            for (int len = 0; len < 14; len++) {
                Map digitMap = new TreeMap<>();
                for (int digit = 1; digit < 10; digit++) {
                    digitMap.put(digit, new TreeMap<>());
                }
                solutions.put(len, digitMap);
            }
        }

        long[] getSolution(int len, int digit, String pointString) {
            return solutions.get(len).get(digit).get(pointString);
        }

        void setSolution(int len, int digit, String pointString, long[] point) {
            Map<Integer, Map<String, long[]>> lenMap = solutions.get(len);
            Map<String, long[]> digitMap = lenMap.get(digit);
            if (!digitMap.containsKey(pointString)) {
                digitMap.put(pointString, point);
            }
        }
    }

    private long[] performInstructions(long[] point, String digit, List<String> instructions) {
        long[] newPoint = Arrays.copyOf(point, point.length);
        if (instructions == null) {
            System.out.println();
        }
        instructions.forEach(instruction -> {
            String[] instructionParts = instruction.split("\\s");
            instructionParts[1] = instructionParts[1]
                    .replace('w', '0')
                    .replace('x', '1')
                    .replace('y', '2')
                    .replace('z', '3');
            boolean byReference = instructionParts.length == 3 && (instructionParts[2].equals("w") || instructionParts[2].equals("x") || instructionParts[2].equals("y") || instructionParts[2].equals("z"));
            if (byReference) {
                instructionParts[2] = instructionParts[2]
                        .replace('w', '0')
                        .replace('x', '1')
                        .replace('y', '2')
                        .replace('z', '3');
            }
            switch (instructionParts[0]) {
                case "inp":
                    int dimension = Integer.parseInt(instructionParts[1]);
                    point[dimension] = Integer.parseInt(digit  + "");
                    break;
                case "add":
                    int dimension1 = Integer.parseInt(instructionParts[1]);
                    int dimension2 = Integer.parseInt(instructionParts[2]);
                    point[dimension1] = point[dimension1] + (byReference ? point[dimension2] : Integer.parseInt(instructionParts[2]));
                    break;
                case "mul":
                    dimension1 = Integer.parseInt(instructionParts[1]);
                    dimension2 = Integer.parseInt(instructionParts[2]);
                    point[dimension1] = point[dimension1] * (byReference ? point[dimension2] : Integer.parseInt(instructionParts[2]));
                    break;
                case "div":
                    dimension1 = Integer.parseInt(instructionParts[1]);
                    dimension2 = Integer.parseInt(instructionParts[2]);
                    point[dimension1] = point[dimension1] / (byReference ? point[dimension2] : Integer.parseInt(instructionParts[2]));
                    break;
                case "mod":
                    dimension1 = Integer.parseInt(instructionParts[1]);
                    dimension2 = Integer.parseInt(instructionParts[2]);
                    point[dimension1] = point[dimension1] % (byReference ? point[dimension2] : Integer.parseInt(instructionParts[2]));
                    break;
                case "eql":
                    dimension1 = Integer.parseInt(instructionParts[1]);
                    dimension2 = Integer.parseInt(instructionParts[2]);
                    point[dimension1] = byReference
                            ? point[dimension1] == point[dimension2] ? 1 : 0
                            : point[dimension1] == Integer.parseInt(instructionParts[2]) ? 1 : 0;
                    break;
                default:
                    throw new UnsupportedOperationException("Instruction type not allowed: " + instructionParts[0]);
            }
        });
        return newPoint;
    }
}
