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
            while (!perc.percolates()) {

            }
        }
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
}
