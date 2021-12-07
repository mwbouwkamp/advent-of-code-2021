package Day06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day06Solver {
    private List<Long> days;
    private Map<Long, Long> histogram;
    private final int NUMDAYS = 8;

    public Day06Solver(List<Long> days) {
        this.days = days;
        histogram = new HashMap<>();
        for (long i = 0; i <=8; i++) {
            histogram.put(i, 0L);
        }
        days.forEach(day -> histogram.put(day, histogram.get(day) + 1));
    }

    public long solveDay06a() {
        return solveDay06(80);
    }

    public long solveDay06b() {
        return solveDay06(256);
    }

    public long solveDay06(int days) {
        for (int i = 0; i < days; i ++) {
            long numSpawning = histogram.get(0L);
            for (long j = 0; j < 8; j++) {
                histogram.put(j, histogram.get(j + 1));
            }
            histogram.put(6L, histogram.get(6L) + numSpawning);
            histogram.put(8L, numSpawning);
        }
        return histogram.values().stream().reduce(Long::sum).orElse(-1L);
    }
}
