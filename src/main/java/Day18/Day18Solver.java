package Day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day18Solver {
    private List<List<String>> lines;

    public Day18Solver(List<String> input) {
        this.lines = input.stream()
                .map(line -> Arrays.asList(line.replace(",", "").split("")))
                .collect(Collectors.toList());
    }

    public int solveDay18b() {
        int maxValue = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                ArrayList<String> sum = add(lines.get(i), lines.get(j));
                solveAddition(sum);
                int value = calcMagnitude(sum);
                if (value > maxValue) {
                    maxValue = value;
                }
                sum = add(lines.get(j), lines.get(i));
                solveAddition(sum);
                value = calcMagnitude(sum);
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }
    public int solveDay18a() {
        ArrayList<String> sum = solveAdditions();
        System.out.println(sum);
        return calcMagnitude(sum);
    }

    public int calcMagnitude(ArrayList<String> sum) {
        boolean pairRemoved = true;
        while (pairRemoved) {
            pairRemoved = removePair(sum);
        }
        return Integer.parseInt(sum.get(0));
    }

    public boolean removePair(List<String> sum) {
        for (int i = 0; i < sum.size() - 1; i++) {
            if (sum.get(i).matches("\\d+") && sum.get(i + 1).matches("\\d+")) {
                long magnitude = Long.parseLong(sum.get(i)) * 3 + Long.parseLong(sum.get(i + 1)) * 2;
                sum.set(i, magnitude + "");
                sum.remove(i + 1);
                sum.remove(i + 1);
                if (i - 1 >= 0) {
                    sum.remove(i - 1);
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> solveAdditions() {
        ArrayList<String> addition = add(lines.get(0), lines.get(1));
        solveAddition(addition);
        for (int i = 2; i < lines.size(); i++) {
            addition = add(addition, lines.get(i));
            solveAddition(addition);
        }
        return addition;
    }

    public void solveAddition(ArrayList<String> addition) {
        boolean lastSplit = true;
        int availableActionCount = 2;
        while (availableActionCount >= 0) {
            if (lastSplit) {
                int positionExplode = posExplode(addition);
                if (positionExplode != -1) {
                    explode(addition, positionExplode);
                    availableActionCount = 2;
                } else {
                    lastSplit = false;
                }
            } else {
                int positionSplit = posSplit(addition);
                if (positionSplit > -1) {
                    split(addition, positionSplit);
                    availableActionCount = 2;
                }
                lastSplit = true;
            }
            availableActionCount--;
        }
    }

    private ArrayList<String> add(List<String> first, List<String> second) {
        ArrayList<String> addition = new ArrayList<>();
        addition.add("[");
        addition.addAll(first);
        addition.addAll(second);
        addition.add("]");
        return addition;
    }
    public void split(ArrayList<String> addition, int positionSplit) {
        int number = Integer.parseInt(addition.get(positionSplit));
        addition.set(positionSplit, "]");
        addition.add(positionSplit, String.valueOf((number + 1) / 2));
        addition.add(positionSplit, String.valueOf(number / 2));
        addition.add(positionSplit, "[");
    }

    public void explode(ArrayList<String> addition, int position) {
        int positionNumAfter = findNumber(addition, position + 3, addition.size() - 1, true);
        if (positionNumAfter != -1) {
            String newValue = String.valueOf(Integer.parseInt(addition.get(position + 1)) + Integer.parseInt(addition.get(positionNumAfter)));
            addition.set(positionNumAfter, newValue);
            addition.remove(position + 1);
            addition.remove(position + 1);
        } else {
            addition.set(position + 1, "0");
            addition.remove(position + 2);
        }
        int positionNumBefore = findNumber(addition, 0, position - 1, false);
        if (positionNumBefore != -1) {
            String newValue = String.valueOf(Integer.parseInt(addition.get(position)) + Integer.parseInt(addition.get(positionNumBefore)));
            addition.set(positionNumBefore, newValue);
            addition.remove(position);
            addition.remove(position - 1);
            if (positionNumAfter != -1) {
                addition.add(position - 1, "0");
            }
        } else {
            addition.set(position, "0");
            addition.remove(position - 1);
        }
    }

    private int findNumber(List<String> addition, int startPosition, int endPosition, boolean findAfter) {
        if (findAfter) {
            for (int i = startPosition; i <= endPosition; i++) {
                if (addition.get(i).matches("\\d+")) {
                    return i;
                }
            }
        } else {
            for (int i = endPosition; i >= startPosition; i--) {
                if (addition.get(i).matches("\\d+")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int posExplode(List<String> line) {
        int bracketCount = 0;
        int position = 0;
        while (position < line.size() - 1) {
            if (line.get(position).matches("\\d+") && line.get(position + 1).matches("\\d+") && bracketCount > 4) {
                return position;
            }
            if (line.get(position).equals("[")) {
                bracketCount++;
            } else if (line.get(position).equals("]")) {
                bracketCount--;
            }
            position++;
        }
        return -1;
    }

    private int posSplit(List<String> line) {
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).matches("\\d+") && Integer.parseInt(line.get(i)) > 9) {
                return i;
            }
        }
        return -1;
    }
}
