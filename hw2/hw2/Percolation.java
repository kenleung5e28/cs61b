package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final WeightedQuickUnionUF components;
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
        this.components = new WeightedQuickUnionUF(N * N);
        this.opened = new boolean[N * N];
        this.openedCount = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        // TODO
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
        // TODO
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        // TODO
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO
        return false;
    }

    public static void main(String[] args) {

    }
}
