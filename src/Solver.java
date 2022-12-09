package src;

import java.util.*;


public class Solver implements SudokuSolver {

    public Solver(){

    }

    @Override
    public boolean solve() {    //svår
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean legal(int digit, int row, int col) { //svår
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void set(int row, int col, int digit) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(int row, int col) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clear() {
        Arrays.fill(getMatrix(), 0);

       /** int rows = matrix.length;
        int cols = matrix[0].length;

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    matrix[i][j] = 0;
                }
            }
            */
    }

    @Override
    public void setMatrix(int[][] matrix) {
        matrix = new int[9][9];
        
    }

    @Override
    public int[][] getMatrix() {
        // TODO Auto-generated method stub
        return null;
    } 
    

}
