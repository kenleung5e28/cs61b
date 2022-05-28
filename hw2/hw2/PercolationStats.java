package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private final double mean;
    private final double stddev;
    private final double confidenceLow;
    private final double confidenceHigh;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be positive.");
        }
        double[] fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            int[] indices = new int[N * N];
            for (int j = 0; j < N * N; j++) {
                indices[j] = j;
            }
            StdRandom.shuffle(indices);
            int j = 0;
            while (!perc.percolates()) {
                perc.open(indices[j] / N, indices[j] % N);
                j += 1;
            }
            fractions[i] = (double) perc.numberOfOpenSites() / (double) (N * N);
        }
        this.mean = StdStats.mean(fractions);
        if (T == 1) {
            this.stddev = Double.NaN;
        } else {
            this.stddev = StdStats.stddev(fractions);
        }
        double halfLength = 1.96 * this.stddev / Math.sqrt(T);
        this.confidenceHigh = this.mean + halfLength;
        this.confidenceLow = this.mean - halfLength;
    }
    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLow;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHigh;
    }

//    public static void main(String[] args) {
//        PercolationStats stats = new PercolationStats(2, 5, new PercolationFactory());
//        stats = new PercolationStats(2, 5, new PercolationFactory());
//    }
}
