package Day04;

import Utils.Grid;

import java.util.ArrayList;
import java.util.List;

public class BingoBoard extends Grid<Integer> {

    public BingoBoard(ArrayList<List<Integer>> grid) {
        super(grid);
    }

    public void markNumber(int number) {
        mainLoop:
        for (int x = 0; x < getNumCols(); x++) {
            for (int y = 0; y < getNumRows(); y++) {
                if (getElement(x, y) == number) {
                    setElement(x, y, 0);
                    break mainLoop;
                }
            }
        }
    }

    public boolean isWinner() {
        for (int i = 0; i < getNumRows(); i++) {
            if (getGrid().get(i).stream().noneMatch(number -> number != 0)) {
                return true;
            }
        }
        for (int i = 0; i < getNumCols(); i++) {
            int finalI = i;
            if (getGrid().stream().map(line -> line.get(finalI)).noneMatch(number -> number != 0)) {
                return true;
            }
        }
        return false;
    }

    public int getSumRemaining() {
        return getGrid().stream()
                .map(list -> list.stream()
                        .reduce(Integer::sum)
                        .orElse(-1))
                .reduce(Integer::sum)
                .orElse(-1);
    }
}
