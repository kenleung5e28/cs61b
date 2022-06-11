package hw4.puzzle;

import java.util.ArrayList;

public class Board implements WorldState {
    private int[][] tiles;
    private int N;

    public Board(int[][] tiles) {
        N = tiles.length;
        int[][] copy = new int[N][];
        for (int i = 0; i < N; i++) {
            copy[i] = new int[N];
            for (int j = 0; j < N; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        this.tiles = copy;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= N || j >= N) {
            throw new IndexOutOfBoundsException("i and j must be between 0 and " + (N - 1));
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    private int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private int[] goalPosition(int value) {
        if (value == 0) {
            return new int[]{N - 1, N - 1};
        }
        int x = value - 1;
        return new int[]{x / N, x % N};
    }

    public int hamming() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = tiles[i][j];
                if (value == 0) {
                    continue;
                }
                int[] goal = goalPosition(value);
                if (i != goal[0] || j != goal[1]) {
                    distance += 1;
                }
            }
        }
        return distance;
    }

    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = tiles[i][j];
                if (value == 0) {
                    continue;
                }
                int[] goal = goalPosition(value);
                distance += manhattanDistance(i, j, goal[0], goal[1]);
            }
        }
        return distance;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y == this) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (other.size() != this.size()) {
            return false;
        }
        int n = this.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (other.tileAt(i, j) != this.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int code = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                code += 31 * code + tiles[i][j];
            }
        }
        return code;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        int x0 = Integer.MIN_VALUE;
        int y0 = Integer.MIN_VALUE;
        outermost:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                    break outermost;
                }
            }
        }
        ArrayList<WorldState> neighbors = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (Math.abs(dx) + Math.abs(dy) != 1) {
                    continue;
                }
                int x = x0 + dx;
                int y = y0 + dy;
                if (x < 0 || x >= N || y < 0 || y >= N) {
                    continue;
                }
                int[][] swapped = new int[N][];
                for (int i = 0; i < N; i++) {
                    swapped[i] = new int[N];
                    for (int j = 0; j < N; j++) {
                        if (i == x && j == y) {
                            swapped[i][j] = tiles[x0][y0];
                        } else if (i == x0 && j == y0) {
                            swapped[i][j] = tiles[x][y];
                        } else {
                            swapped[i][j] = tiles[i][j];
                        }
                    }
                }
                neighbors.add(new Board(swapped));
            }
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int size = size();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
