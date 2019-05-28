/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public interface PercolationStatsInterface {
    public double mean();                          // sample mean of percolation threshold
    public double stddev();                        // sample standard deviation of percolation threshold
    public double confidenceLo();                  // low  endpoint of 95% confidence interval
    public double confidenceHi();                 // high endpoint of 95% confidence interval

}
