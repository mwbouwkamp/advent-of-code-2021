package Day04;

import Utils.ReadPuzzleInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 {

    public static void main(String[] args) throws IOException {
        List<String> input = new ReadPuzzleInput().readPuzzleLines("input04.txt");
        List<Integer> numbers = Arrays.stream(input.remove(0).split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        List<List<List<Integer>>> boardInputs = new ArrayList<>();
        while (input.size() > 0) {
            input.remove(0);
            List<List<Integer>> boardToAdd = IntStream.range(0, 5)
                    .boxed()
                    .map(s -> {
                        String lineString = input.remove(0);
                        return Arrays.stream(lineString.trim().split("\\s+"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                    })
                    .collect(Collectors.toList());
            boardInputs.add(boardToAdd);
        }
        List<BingoBoard> bingoBoards = boardInputs.stream()
                .map(board -> new BingoBoard((ArrayList<List<Integer>>) board))
                .collect(Collectors.toList());

        Day04Solver solver = new Day04Solver(numbers, bingoBoards);

        System.out.println(solver.solveDay04a());
        System.out.println(solver.solveDay04b());


    }
}
