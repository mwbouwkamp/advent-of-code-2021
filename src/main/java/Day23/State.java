package Day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class State {
    private final Burrow burrow;
    private final int cost;
    private List<Integer> indexes = Arrays.asList(2, 4, 6, 8);

    public State(Burrow burrow, int cost) {
        this.burrow = burrow;
        this.cost = cost;
    }

    public boolean solved() {
        return burrow.getRooms(0).equals("##2#4#6#8##") && burrow.getRooms(1).equals("##2#4#6#8##");
    }

    public List<State> getChildren() {
        List<State> children = new ArrayList<>();
        indexes.forEach(i -> {
            // Rooms one
            if (burrow.getRooms(0).charAt(i) != '.' && !isCorrectPosition(i, 0)) {
                getStartToHallwayChildren(children, 0, i, 0);
            }
            // Rooms two
            if (burrow.getRooms(1).charAt(i) != '.' && burrow.getRooms(0).charAt(i) == '.' && !isCorrectPosition(i, 1)) {
                getStartToHallwayChildren(children, 1, i, 1);
            }
        });
        // Hallway
        IntStream.range(0, 11).forEach(i -> {
            char amphipod = burrow.getHallway().charAt(i);
            if (amphipod == '.') {
                return;
            }
            int amphipodValue = Character.getNumericValue(amphipod);
            if (i == amphipodValue) {
                return;
            }
            if (burrow.getRooms(0).charAt(amphipodValue) != '.') {
                return;
            }
            int pathStart = Math.min(i, amphipodValue) + 1;
            int pathEnd = Math.max(i, amphipodValue);
            String path = burrow.getHallway().substring(pathStart, pathEnd);
            if (Arrays.stream(path.split("")).anyMatch(s -> !s.equals("."))) {
                return;
            }
            if (burrow.getRooms(1).charAt(amphipodValue) == '.') {
                // Rooms two
                getHallWayToEndChild(children, amphipodValue, amphipod, path, 1);

            } else {
                // Rooms one
                getHallWayToEndChild(children, amphipodValue, amphipod, path, 0);
            }
        });
        return children;
    }

    private void getHallWayToEndChild(List<State> children, int i, char amphipod, String path, int depth) {
        String hallWay = changeCharInPosition(burrow.getHallway(), i, '.');
        String roomsOne = depth == 0 ? changeCharInPosition(burrow.getRooms(depth), i, amphipod) : burrow.getRooms(0);
        String roomsTwo = depth == 1 ? changeCharInPosition(burrow.getRooms(depth), i, amphipod) : burrow.getRooms(1);
        children.add(
                new State(
                        new Burrow(hallWay, roomsOne, roomsTwo),
                        cost + (path.length() + depth + 1) * costToAdd(amphipod)
                )
        );
    }

    public int heuristic() {
        int minimumCost = 0;
        for (int index: indexes) {
            for (int i = 0; i < 2; i++) {
                char amphipod = burrow.getRooms(i).charAt(index);
                if (amphipod != '.') {
                    int value = Character.getNumericValue(amphipod);
                    if (value != index) {
                        minimumCost += (Math.abs(value - index) + 2) * costToAdd(amphipod);
                    }
                }
            }
        }
        for (int i = 0; i < 11; i++) {
            if (burrow.getHallway().charAt(i) != '.') {
                char amphipod = burrow.getHallway().charAt(i);
                int value = Character.getNumericValue(amphipod);
                minimumCost += (Math.abs(value - i) + 1) * costToAdd(amphipod);
            }
        }
        return minimumCost + getCost();
    }

    private void getStartToHallwayChildren(List<State> children, int rooms, int i, int depth) {
        int position = i;
        while(position >= 0 && burrow.getHallway().charAt(position) == '.') {
            if (position != i) {
                String hallWay = changeCharInPosition(burrow.getHallway(), position, burrow.getRooms(rooms).charAt(i));
                String roomsOne = rooms == 0 ? changeCharInPosition(burrow.getRooms(rooms), i, '.') : burrow.getRooms(0);
                String roomsTwo = rooms == 1 ? changeCharInPosition(burrow.getRooms(rooms), i, '.') : burrow.getRooms(1);
                children.add(
                        new State(
                                new Burrow(hallWay, roomsOne, roomsTwo),
                                cost + (Math.abs(position - i) + depth + 1) * costToAdd(burrow.getRooms(rooms).charAt(i))
                        )
                );
            }
            position--;
        }
        position = i;
        while (position < 11 && burrow.getHallway().charAt(position) == '.') {
            if (position != i) {
                String hallWay = changeCharInPosition(burrow.getHallway(), position, burrow.getRooms(rooms).charAt(i));
                String roomsOne = rooms == 0 ? changeCharInPosition(burrow.getRooms(rooms), i, '.') : burrow.getRooms(0);
                String roomsTwo = rooms == 1 ? changeCharInPosition(burrow.getRooms(rooms), i, '.') : burrow.getRooms(1);
                children.add(
                        new State(
                                new Burrow(hallWay, roomsOne, roomsTwo),
                                cost + (Math.abs(position - i) + depth + 1) * costToAdd(burrow.getRooms(rooms).charAt(i))
                        )
                );
            }
            position++;
        }
    }

    private boolean isCorrectPosition(int position, int depth) {
        return depth == 0
                ? burrow.getRooms(depth).equals(String.valueOf(position))
                : burrow.getRooms(depth).equals(String.valueOf(position)) && burrow.getRooms(depth- 1).equals(String.valueOf(position));
    }

    private int costToAdd(char chr) {
        switch (chr) {
            case '2':
                return 1;
            case '4':
                return 10;
            case '6':
                return 100;
            case '8':
                return 1000;
            default:
                throw new IllegalArgumentException("Char not allowed: " + chr);
        }
    }
    private String changeCharInPosition(String str, int position, char ch){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }

    public Burrow getBurrow() {
        return burrow;
    }

    public int getCost() {
        return cost;
    }
}
