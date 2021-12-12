package Day12;

import java.util.*;
import java.util.stream.Collectors;

public class Day12Solver {

    public Day12Solver() {
    }

    public int SolveDay12a(List<String> input) {
        return solveCageSystem(input, false);
    }

    public int SolveDay12b(List<String> input) {
        return solveCageSystem(input, true);
    }

    public int solveCageSystem(List<String> input, boolean day12b) {
        List<String> paths = createPaths(input);
        List<State> fringe = paths.stream()
                .filter(path -> path.startsWith("start"))
                .map(path -> new State(path))
                .collect(Collectors.toList());
        List<State> solutions = new ArrayList<>();
        while (fringe.size() > 0) {
            State toCheck = fringe.remove(0);
            if (toCheck.getLastCave().equals("***")) {
                solutions.add(toCheck);
            } else {
                fringe.addAll(paths.stream()
                        .filter(path -> path.split("-")[0].equals(toCheck.getLastCave()))
                        .filter(path ->
                                (day12b && !toCheck.visitedSmallCageTwice) ||
                                        path.split("-")[1].toUpperCase().equals(path.split("-")[1]) ||
                                        !toCheck.visitedCave(path.split("-")[1]))
                        .map(path -> new State(toCheck, path))
                        .collect(Collectors.toList()));
            }
        }
        return solutions.size();
    }

    private List<String> createPaths(List<String> input) {
        List<String> paths = new ArrayList<>();
        input.forEach(path -> {
            paths.add(path);
            paths.add(path.split("-")[1] + "-" + path.split("-")[0]);
        });
        return paths;
    }

    private class State {
        private List<String> paths;
        private boolean visitedSmallCageTwice = false;

        public State(String path) {
            this.paths = new ArrayList<>();
            addPath(path);
        }

        private void addPath(String path) {
            if (path.startsWith("start")) {
                this.paths.add("###-" + path.split("-")[1]);
            } else if (path.contains("start")) {
                this.paths.add(path.split("-")[0] + "-###");
            } else if (path.startsWith("end")) {
                this.paths.add("***-" + path.split("-")[1]);
            } else if (path.contains("end")) {
                this.paths.add(path.split("-")[0] + "-***");
            } else {
                this.paths.add(path);
            }
        }

        public State(State oldState, String path) {
            this.paths = new ArrayList<>();
            this.paths.addAll(oldState.getPaths());
            if (oldState.visitedSmallCageTwice ||
                    (!path.split("-")[1].equals("###") && paths.stream().filter(pth -> pth.contains(path.split("-")[1].toLowerCase(Locale.ROOT)))
                            .findAny()
                            .orElse(null) != null)) {
                visitedSmallCageTwice = true;
            }
            addPath(path);
        }

        public String getLastCave() {
            return paths.get(paths.size() - 1).split("-")[1];
        }

        public List<String> getPaths() {
            return this.paths;
        }

        public boolean visitedCave(String cave) {
            return paths.stream()
                    .filter(path -> path.contains(cave))
                    .findAny()
                    .orElse(null) != null;
        }

        public boolean isVisitedSmallCageTwice() {
            return visitedSmallCageTwice;
        }
    }
}
