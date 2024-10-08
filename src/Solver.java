package src;

public class Solver implements SudokuSolver {
    private int[][] matrix;

    public Solver(){
        matrix = new int[9][9];
    }
    
    /** 
     * Solves the Sudoku puzzle.
     * @return boolean
     */
    @Override
    public boolean solve() {    
        return sourceSudoku(0, 0); //Basfall. 
    }

    private boolean sourceSudoku(int row, int col){
        if(row >= matrix.length || col >= matrix.length){ //Ifall vi hamnar utanför intervallet utan problem så är det löst.
            return true; 
        } else {
        if(matrix[row][col] == 0){ //Om platsen är tom.
            for(int value = 1; value <= 9; value++){ //Testa alla värden 1-9
                if(legal(value, row, col)){ 
                    matrix[row][col] = value; //Om laglig sätt nya värdet.
                    if(col<matrix.length-1){ 
                        if (sourceSudoku(row, col+1)){
                            return true;
                        }
                    } else if (row<matrix[0].length-1){ 
                        if(sourceSudoku(row+1, 0)){ 
                            return true;
                        }
                        
                    } else{
                        return true;
                    }
                }
            }
            matrix[row][col] = 0; //Inget värde kunde placeras på platsen. 
            return false; 
        } else { //Om platsen INTE är tom.
            if(legal(matrix[row][col], row, col)){
                if(col<matrix.length-1){ 
                    return sourceSudoku(row, col+1);
                } else {
                    return sourceSudoku(row+1, 0);
                }
            } else { 
                return false;
            }
        } 
        }
    }

    
    /** 
     * Checks if the current digit on the specified row, column is legal.
     * @param digit Specify digit
     * @param row Specify row
     * @param col Specify column
     * @return boolean
     */
    @Override
    public boolean legal(int digit, int row, int col) { 
        for(int i = 0; i < 9; i++){ //Kolla horisontellt
            if(i != col){
                if(matrix[row][i] == digit){
                    return false;
                }
            }
            if(i != row){ //Kolla vertikalt
                if(matrix[i][col] == digit){
                    return false;
                }
            }
        }
        int startRow = row - (row % 3); //Vart börjar vår region
        int startCol = col - (col % 3); 

        row = row % 3; //Position vi kollar just nu
        col = col % 3; 

        for(int i = 0; i < 3; i++){ //Kollar inom samma region.
            for(int j = 0; j < 3; j++){
                if(i != row || j != col){
                    if(matrix[startRow + i][startCol + j] == digit){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException If row, col or digit is outside the range
	 *                                  [0..9]
	 */
    @Override
    public void set(int row, int col, int digit) {
        if(row < 0 || row > 9) throw new IllegalArgumentException("Invalid row");
        if(col < 0 || col > 9) throw new IllegalArgumentException("Invalid column");
        if(digit < 0 || digit > 9) throw new IllegalArgumentException("Invalid digit, should be 1-9");  
        matrix[row][col] = digit;
        
    }

    /**
	 * Removes the digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row, col is outside the range
	 *                                  [0..9]
	 */
    @Override
    public void remove(int row, int col) {
        if(row < 0 || row > 9) throw new IllegalArgumentException("Invalid row");
        if(col < 0 || col > 9) throw new IllegalArgumentException("Invalid column");
        matrix[row][col] = 0;
        
    }

    /**
     * Empties the matrix.
      */
    @Override
    public void clear() {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = 0;
            }
        }

    }

    /**
	 * Fills the puzzle with the digits in matrix. The digit 0 represents an empty box.
	 * 
	 * @param matrix the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
    @Override
    public void setMatrix(int[][] matrix) {
        if (matrix == null || matrix.length != 9 || matrix[0].length != 9) { //Kollar dimensionerna
            throw new IllegalArgumentException("Invalid matrix.");
        }
        for(int i = 0; i < 8; i++){ //Kollar så att alla värden är giltiga.
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
        //for loopar skicka kopian ist för org.
        return matrix;
    } 
    

}
