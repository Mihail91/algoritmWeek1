/**
 * Created by Mike on 12.05.2019.
 */

public class Percolation implements PercolationInterface {

    private int[] array;
    private int numb;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        numb = n;
        array = new int[n*n];
        for (int i = 1; i < n*n + 1; i++) {
            array[i] = 0;
        }
    }

    public static void main(String[] args) {

    }
    // open site (row, col) if it is not open already
    @Override
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int position = (row - 1) * numb + col;
            array[position] = position;
        }
    }
    // is site (row, col) open?
    @Override
    public boolean isOpen(int row, int col) {
        check(row);
        check(col);
        int position = (row - 1) * numb + col;
        return array[position] != 0;
    }
    // is site (row, col) full?
    @Override
    public boolean isFull(int row, int col) {
        check(row);
        check(col);
        if (isOpen(row, col)) {
            int rootNumb = root((row - 1) * numb + col);
            if (rootNumb <= numb){
                return true;
            }
        }
        return false;
    }
    // number of open sites
    @Override
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= numb*numb; i++) {
            if (array[i] != 0){
                count++;
            }
        }
        return count;
    }
    // does the system percolate?
    @Override
    public boolean percolates() {
        for (int i = 0; i < numb ; i++) {
            if (isFull(numb, numb - i)){
                return true;
            }
        }
        return false;
    }

    private void check(int n){
        if (n > numb || n <= 0){
            throw new IllegalArgumentException();
        }
    }

    private void union(int rowOpen, int colOpen, int row, int col){
        check(row);
        check(col);
        int p = (rowOpen-1)*numb+colOpen;
        int q = (row-1)*numb+col;

        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (i > j) { array[i] = j; }
        else { array[j] = i; }

    }

    private int root(int i)
    {
        while (i != array[i])
        {
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }

    private boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

}
