package Day15;

import Utils.Grid;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day15Solver {
    private final Grid<Integer> map;
    private Map<Point, State> solutions;

    public Day15Solver(List<String> input) {
        ArrayList<List<Integer>> convertedInput = (ArrayList<List<Integer>>) input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.map = new Grid<>(convertedInput);
        this.solutions = new HashMap<>();
    }

    public int solveDay15a() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < map.getNumRows(); i++) {
            for (int j = 0; j <=i; j++) {
                points.add(new Point(map.getNumCols() - 1 - i, map.getNumRows() -1 - j));
                if (i != j) {
                    points.add(new Point(map.getNumCols() -1 - j, map.getNumRows() -1 - i));

                }
            }
        }
//        for (int x = 0; x < map.getNumCols(); x++) {
//            for (int y = 0; y < map.getNumRows(); y++) {
//                points.add(new Point(x, y));
//            }
//        }
//        Collections.sort(points, (o1, o2) -> -(o1.x + o1.y - o2.x + o2.y));
        points.forEach(point -> {
            if (point.x == map.getNumCols() - 1 && point.y == map.getNumRows() - 1) {
                solutions.put(new Point(point.x, point.y), new State(new ArrayList<>(), map.getElement(point.x, point.y), new Position(new Point(point.x, point.y), map.getElement(point.x, point.y))));
                return;
            }
            State solutionFound = runSolution(point.y, point.x);
            List<Position> path = new ArrayList<>(solutionFound.path);
            int cost = solutionFound.cost;
            Position lastPosition = solutionFound.lastPosition;
            while (path.size() > 0) {
                Position firstPosition = path.remove(0);
                cost -= map.getElement(firstPosition.getX(), firstPosition.getY());
                if (solutions.keySet().stream()
                        .filter(key -> key.x == firstPosition.getX() && key.y == firstPosition.getY())
                        .findAny().orElse(null) == null) {
                    solutions.put(new Point(firstPosition.getX(), firstPosition.getY()), new State(new ArrayList<>(path), cost, new Position(new Point(map.getNumCols() - 1, map.getNumRows() - 1), cost)));
                }
            }

        });
//        for (int x = map.getNumCols() - 1; x >= 0; x--) {
//            for (int y = map.getNumRows() - 1; y >=0; y--) {
//                if (x == map.getNumCols() - 1 && y == map.getNumRows() - 1) {
//                    solutions.put(new Point(x, y), new State(new ArrayList<>(), map.getElement(x, y), new Position(new Point(x, y), map.getElement(x, y))));
//                    continue;
//                }
//                State solutionFound = runSolution(y, x);
//                List<Position> path = new ArrayList<>(solutionFound.path);
//                int cost = solutionFound.cost;
//                Position lastPosition = solutionFound.lastPosition;
//                while (path.size() > 0) {
//                    Position firstPosition = path.remove(0);
//                    cost -= map.getElement(firstPosition.getX(), firstPosition.getY());
//                    if (solutions.keySet().stream()
//                            .filter(key -> key.x == firstPosition.getX() && key.y == firstPosition.getY())
//                            .findAny().orElse(null) == null) {
//                        solutions.put(new Point(firstPosition.getX(), firstPosition.getY()), new State(new ArrayList<>(path), cost, new Position(new Point(map.getNumCols() - 1, map.getNumRows() - 1), cost)));
//                    }
//                }
////
////                solutions.put(new Point(y, x), runSolution(y, x));
//            }
//        }
        return solutions.keySet().stream()
                .filter(key -> key.x == 0 && key.y == 0)
                .map(key -> solutions.get(key))
                .findAny()
                .orElse(null).cost;
    }

    private State runSolution(int x, int y) {
        Position firstPosition = new Position(new Point(x,y), map.getElement(x, y));
        State firstState = new State(firstPosition);
        List<State> fringe = new ArrayList<>();
        State minState = null;
        int minCost = (map.getNumCols() + map.getNumRows()) * 10;
        fringe.add(firstState);
        while (fringe.size() > 0) {
            Collections.sort(fringe);
            State toCheck = fringe.remove(fringe.size() - 1);
            State earlierSolution = solutions.get(toCheck.lastPosition.coordinates);
            if (earlierSolution != null) {
//                if (toCheck.path.get(toCheck.path.size() - 1).coordinates.x == 9 &&
//                        toCheck.path.get(toCheck.path.size() - 1).coordinates.y == 8) {
//                    System.out.println();
//                }
//                System.out.println(toCheck.path.get(toCheck.path.size() - 1).coordinates);
                List<Position> newPath = new ArrayList<>(toCheck.path);
                newPath.remove(newPath.size() - 1);
                newPath.addAll(earlierSolution.path);
                toCheck.path = newPath;
//                toCheck.path.remove(toCheck.path.size() - 1);
//                toCheck.path.addAll(earlierSolution.path);
                toCheck.cost += earlierSolution.cost;
                toCheck.lastPosition = earlierSolution.lastPosition;
            }
            if (toCheck.cost >= minCost) {
                continue;
            }
            if (toCheck.lastPosition.coordinates.equals(new Point(map.getNumCols() - 1, map.getNumRows() - 1))) {
                minState = toCheck;
                minCost = toCheck.getCost();
                int finalMinCost1 = minCost;
                fringe = fringe.stream()
                        .filter(state -> state.cost < finalMinCost1)
                        .collect(Collectors.toList());
                System.out.println("[" + x + "," + y + "] " + fringe.size() + " " + minCost);
            } else {
                List<Position> children = new ArrayList<>();
                int lastX = toCheck.lastPosition.getX();
                int lastY = toCheck.lastPosition.getY();
                if (lastY > 0) {
                    children.add(new Position(new Point(lastX, lastY - 1), map.getElement(lastX, lastY - 1)));
                }
                if (lastX > 0) {
                    children.add(new Position(new Point(lastX - 1, lastY), map.getElement(lastX - 1, lastY)));
                }
                if (lastX < map.getNumCols() - 1) {
                    children.add(new Position(new Point(lastX + 1, lastY), map.getElement(lastX + 1, lastY)));
                }
                if (lastY < map.getNumRows() - 1) {
                    children.add(new Position(new Point(lastX, lastY + 1), map.getElement(lastX, lastY + 1)));
                }
                int finalMinCost = minCost;
                children = children.stream()
                        .filter(child -> toCheck.cost + child.cost < finalMinCost)
                        .filter(child -> toCheck.path.stream()
                                .noneMatch(position -> position.coordinates.equals(child.coordinates)))
                        .collect(Collectors.toList());
                fringe.addAll(children.stream()
                        .map(child -> new State(toCheck, child))
                        .collect(Collectors.toList()));
            }
        }
        return minState;
    }

    public class State implements Comparable<State>{
        private List<Position> path;
        private int cost;
        private Position lastPosition;

        public State(List<Position> path, int cost, Position lastPosition) {
            this.path = path;
            this.cost = cost;
            this.lastPosition = lastPosition;
        }
        public State(Position newPosition) {
            this.path = List.of(newPosition);
            this.cost = newPosition.cost;
            this.lastPosition = newPosition;
        }

        public State(State oldState, Position newPosition) {
            this.path = new ArrayList<>(oldState.getPath());
            this.path.add(newPosition);
            this.cost = oldState.getCost() + newPosition.getCost();
            this.lastPosition = newPosition;
        }


        public List<Position> getPath() {
            return path;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(State otherState) {
//            return this.lastPosition.getX() * this.lastPosition.getX() + this.lastPosition.getY() * this.lastPosition.getY() -
//                    otherState.lastPosition.getX() * otherState.lastPosition.getX() + otherState.lastPosition.getY() * otherState.lastPosition.getY()
//                    + (this.cost - otherState.cost) * 100;
//            return -(this.lastPosition.getX() - otherState.lastPosition.getX() + this.lastPosition.getY() - otherState.lastPosition.getY());
//            return this.lastPosition.getY() != otherState.lastPosition.getY()
//                    ? - this.lastPosition.getY() + otherState.lastPosition.getY()
//                    : - this.lastPosition.getX() + otherState.lastPosition.getX();
            return - this.cost + otherState.cost;
        }
    }

    public class Position {
        private final Point coordinates;
        private final int cost;


        public Position(Point coordinates, int cost) {
            this.coordinates = coordinates;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public int getX() {
            return coordinates.x;
        }

        public int getY() {
            return coordinates.y;
        }
    }
}
