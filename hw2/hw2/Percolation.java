package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // During initialization, we connect all the sites on the (N - 1)th row in the union-find structure,
    // so that percolation can be checked by asking only whether (N - 1, 0) is full.
    private final int N;
    private final WeightedQuickUnionUF components;
    private final boolean[] opened;
    private int openedCount;
    private boolean someBottomSiteOpened;

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
        this.someBottomSiteOpened = false;
        for (int i = 1; i < N; i++) {
            components.union(rowColToIndex(N - 1, 0), rowColToIndex(N - 1, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1.");
        }
        opened[rowColToIndex(row, col)] = true;
        openedCount += 1;
        if (row == N - 1) {
            someBottomSiteOpened = true;
        }
        if (row > 0 && isOpen(row - 1, col)) {
            components.union(rowColToIndex(row, col), rowColToIndex(row - 1, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            components.union(rowColToIndex(row, col), rowColToIndex(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            components.union(rowColToIndex(row, col), rowColToIndex(row, col - 1));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            components.union(rowColToIndex(row, col), rowColToIndex(row, col + 1));
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
        return isOpen(row, col) && components.find(rowColToIndex(row, col)) < N;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return someBottomSiteOpened && components.find(rowColToIndex(N - 1, 0)) < N;
    }

    public static void main(String[] args) {

    }
}
