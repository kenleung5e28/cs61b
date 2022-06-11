package hw4.puzzle;

import java.util.ArrayList;

public class Board implements WorldState {
    private int[][] tiles;
    private int N;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.N = tiles.length;
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

    public int hamming() {
        // TODO
        return 0;
    }

    public int manhattan() {
        // TODO
        return 0;
    }

    public boolean equals(Object y) {
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
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
