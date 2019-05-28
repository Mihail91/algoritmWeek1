/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats implements PercolationStatsInterface {
    private int numb;
    private int repeat;
    private double[] means;
    private double meansResult = 0;
    private double stddevResult = 0;

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int r = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, r);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        numb = n;
        repeat = trials;
        means = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!p.isOpen(row, col)){
                    p.open(row, col);
                }
            }
            means[i] = (double) p.numberOfOpenSites()/(n*n);
        }

    }
    // sample mean of percolation threshold
    @Override
    public double mean() {
        if (meansResult == 0) {
            double m = 0;
            for (int i = 0; i < repeat; i++) {
                m += means[i];
            }
            meansResult = m / repeat;
        }
        return meansResult;
    }
    // sample standard deviation of percolation threshold
    @Override
    public double stddev() {
        if (stddevResult == 0){
            double std = 0;
            for (int i = 0; i < repeat; i++) {
                std += Math.pow(means[i] - mean(),2);
            }
            stddevResult = Math.sqrt(std/(repeat - 1));
        }
        return stddevResult;
    }
    // low  endpoint of 95% confidence interval
    @Override
    public double confidenceLo() {
        return mean() - 1.96 * stddev()/Math.sqrt(repeat);
    }
    // high endpoint of 95% confidence interval
    @Override
    public double confidenceHi() {
        return mean() + 1.96 * stddev()/Math.sqrt(repeat);
    }
}
