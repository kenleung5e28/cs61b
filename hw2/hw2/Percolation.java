package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // add two special sites that connects to all sites on the top row and
    // all sites on the bottom row respectively, so that only one connected()
    // call is needed to test for percolation
    private final int N;
    private final WeightedQuickUnionUF components;
    private final WeightedQuickUnionUF noSinkComponents;
    private final boolean[] opened;
    private int openedCount;

    private int rowColToIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        return row * N + col;
    }
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive.");
        }
        this.N = N;
        // The second-to-last site is the extra top site
        // and the last site is the extra bottom site
        this.components = new WeightedQuickUnionUF(N * N + 2);
        this.noSinkComponents = new WeightedQuickUnionUF(N * N + 1);
        this.opened = new boolean[N * N];
        this.openedCount = 0;
        for (int i = 0; i < N; i++) {
            components.union(N * N, rowColToIndex(0, i));
            noSinkComponents.union(N * N, rowColToIndex(0, i));
            components.union(N * N + 1, rowColToIndex(N - 1, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        int i = rowColToIndex(row, col);
        if (!opened[i]) {
            opened[i] = true;
            openedCount += 1;
        }
        if (row > 0 && isOpen(row - 1, col)) {
            components.union(i, rowColToIndex(row - 1, col));
            noSinkComponents.union(i, rowColToIndex(row - 1, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            components.union(i, rowColToIndex(row + 1, col));
            noSinkComponents.union(i, rowColToIndex(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            components.union(i, rowColToIndex(row, col - 1));
            noSinkComponents.union(i, rowColToIndex(row, col - 1));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            components.union(i, rowColToIndex(row, col + 1));
            noSinkComponents.union(i, rowColToIndex(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        return opened[rowColToIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        return isOpen(row, col) && noSinkComponents.connected(N * N, rowColToIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1) {
            return isOpen(0, 0);
        }
        return components.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) {

    }
}
