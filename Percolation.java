/**
 * Created by Mike on 12.05.2019.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation implements PercolationInterface {

    private int[][] array;
    private int numb;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        numb = n;
        array = new int[n][n];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                array[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
    // open site (row, col) if it is not open already
    @Override
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            array[row][col] = row * col;
        }
    }
    // is site (row, col) open?
    @Override
    public boolean isOpen(int row, int col) {
        check(row);
        check(col);
        return array[row][col] != 0;
    }
    // is site (row, col) full?
    @Override
    public boolean isFull(int row, int col) {
        check(row);
        check(col);
        return false;
    }
    // number of open sites
    @Override
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= numb; i++) {
            for (int j = 1; j <= numb ; j++) {
                if (array[i][j] == 1){
                    count++;
                }
            }
        }
        return count;
    }
    // does the system percolate?
    @Override
    public boolean percolates() {
        for (int i = 1; i <= numb ; i++) {
            if (array[numb][i] == 1){
                if (isFull(numb, i)){
                    return true;
                }
            }
        }
        return false;
    }

    private void check(int n){
        if (n > numb || n <= 0){
            throw new IllegalArgumentException();
        }
    }
}
