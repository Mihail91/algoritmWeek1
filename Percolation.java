import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Mike on 12.05.2019.
 */

public class Percolation {

    private int[] array;
    private int[] size;
    private int numb;
    private int count;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        numb = n;
        array = new int[n*n+1];
        size = new int[n*n+1];
        for (int i = 1; i < n*n + 1; i++) {
            array[i] = 0;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation per = new Percolation(n);
        per.toString();
        per.open(1,1);
        per.toString();
        per.open(2,2);
        per.toString();
        per.open(3,1);
        per.toString();
        System.out.println(per.isFull(3, 1));

    }
    // open site (row, col) if it is not open already
    
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int position = (row - 1) * numb + col;
            array[position] = position;
            count++;
            if (row - 1 > 0 && isOpen(row - 1, col)) union(row ,col, row - 1, col);
            if (col - 1 > 0 && isOpen(row, col - 1)) union(row ,col, row, col - 1);
            if (row + 1 <= numb && isOpen(row + 1, col)) union(row ,col, row + 1, col);
            if (col + 1 <= numb && isOpen(row, col + 1)) union(row ,col, row, col + 1);
        }
    }
    // is site (row, col) open?
    
    public boolean isOpen(int row, int col) {
        check(row);
        check(col);
        int position = (row - 1) * numb + col;
        return array[position] != 0;
    }
    // is site (row, col) full?
    
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
    
    public int numberOfOpenSites() {
        return count;
    }
    // does the system percolate?
    
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
        if (i > j) {
            array[i] = j;
            size[j] += size[i];
        }
        else {
            array[j] = i;
            size[i] += size[j];
        }

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

    
    public String toString() {
        for (int i = 1; i <= numb*numb; i++) {
            System.out.print(array[i] > 0 ? "1 " : "0 ");
            if (i % numb == 0) System.out.println();
        }
        return "";
    }
}
