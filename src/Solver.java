package src;

import java.util.*;


public class Solver implements SudokuSolver {
    private int[][] matrix;

    public Solver(){
        matrix = new int[9][9];
    }

    @Override
    public boolean solve() {    
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean legal(int digit, int row, int col) { 
        // TODO Auto-generated method stub
        return false;
    }

    /**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
    @Override
    public void set(int row, int col, int digit) {
        if(row > 8 || row < 0) throw new IllegalArgumentException("Invalid row");
        if(col > 8 || col < 0) throw new IllegalArgumentException("Invalid column");
        if(digit > 9 || digit < 1) throw new IllegalArgumentException("Invalid digit, should be 1-9");  
        matrix[row][col] = digit;
        
    }

    /**
	 * Removes the digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
    @Override
    public void remove(int row, int col) {
        if(row > 8 || row < 0) throw new IllegalArgumentException("Invalid row");
        if(col > 8 || col < 0) throw new IllegalArgumentException("Invalid column");
        matrix[row][col] = 0;
        
    }

    /**
     * Empties the grid.
      */
    @Override
    public void clear() {
        Arrays.fill(getMatrix(), 0);
    }

    /**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
    @Override
    public void setMatrix(int[][] matrix) {
        if (matrix == null || matrix.length != 9 || matrix[0].length != 9) {
            throw new IllegalArgumentException("Invalid matrix.");
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(matrix[i][j] < 0 || matrix[i][j] > 9){
                    System.out.println("ERROR at \nRow: " + i + "\n" + "Col: " + j);
                    throw new IllegalArgumentException("Invalid value inside matrix.");
                }
            }
        }
        this.matrix = matrix; 
    }

    /**
	 * Returns a matrix with the current values.
	 * 
	 * @return integer matrix with current values
	 */
    @Override
    public int[][] getMatrix() {
        return matrix;
    } 
    

}
