package Day23;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day23Solver {
    private Burrow burrow;

    public Day23Solver(String roomsOne, String roomsTwo) {
        this.burrow = new Burrow(roomsOne, roomsTwo);
        System.out.println(burrow);
    }

    public int solveDay23a() {
        List<State> fringe = new ArrayList<>();
        fringe.add(new State(burrow, 0));
        State solution = null;
        int lowest = 16441;
        while (fringe.size() > 0) {
            fringe.sort(Comparator.comparingInt(s -> s.heuristic()));
            State toCheck = fringe.remove(fringe.size() - 1);
//            System.out.println(fringe.size() + " " + toCheck.numCorrect());
//            System.out.println(fringe.size());
            if (toCheck.solved()) {
                if (toCheck.getCost() < lowest) {
                    solution = toCheck;
                    System.out.println(solution);
                    lowest = toCheck.getCost();
                    int finalLowest = lowest;
                    fringe = fringe.stream()
                            .filter(state -> state.getCost() < finalLowest)
                            .collect(Collectors.toList());
                }
            } else {
                List<State> children = toCheck.getChildren();
                int finalLowest1 = lowest;
                fringe.addAll(children.stream()
                        .filter(child -> child.getCost() < finalLowest1)
                        .collect(Collectors.toList()));
            }

        }
        return solution.getCost();
    }
}
