package Day04;

import java.util.ArrayList;
import java.util.List;

public class Day04Solver {
    private final List<Integer> numbers;
    private final List<BingoBoard> boards;

    public Day04Solver(List<Integer> numbers, List<BingoBoard> boards) {
        this.numbers = numbers;
        this.boards = boards;
    }

    public int solveDay04a() {
        for (int number: numbers) {
            for (BingoBoard board: boards) {
                board.markNumber(number);
                if (board.isWinner()) {
                    return board.getSumRemaining() * number;
                }
            }
        }
        return -1;
    }

    public int solveDay04b() {
        List<BingoBoard> solvedBoards = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        for (int number: numbers) {
            for (BingoBoard board: boards) {
                if (!solvedBoards.contains(board)) {
                    board.markNumber(number);
                    if (board.isWinner()) {
                        solvedBoards.add(board);
                        results.add(board.getSumRemaining() * number);
                    }
                }
            }
        }
        return results.get(results.size() - 1);

    }

}
