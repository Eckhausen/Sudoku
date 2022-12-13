package src;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSolver {
    private Solver s;
    private int[][] defaultMatrix = {
        {6, 0, 0, 0, 0, 0, 1, 8, 7},
        {5, 1, 0, 0, 0, 8, 6, 3, 4},
        {8, 3, 0, 0, 0, 4, 2, 9, 5},
        {1, 4, 0, 0, 0, 5, 7, 2, 0},
        {9, 5, 0, 0, 0, 7, 3, 6, 1},
        {7, 6, 0, 0, 0, 1, 4, 5, 8},
        {3, 7, 1, 0, 0, 6, 8, 4, 2},
        {4, 9, 6, 0, 0, 2, 5, 0, 3},
        {2, 8, 5, 0, 0, 3, 9, 1, 6}
    };

    @BeforeEach
    void setUp(){
        s = new Solver();
    }


    @AfterEach
    void tearDown(){
        s = null;
    }


    @Test
    void solveSolveablePuzzle(){

    }

    @Test
    void solveEmptyPuzzle(){
        int[][] emptyMatrix = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    s.setMatrix(emptyMatrix);
    int [][] matrix = s.getMatrix();
    for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(0, matrix[i][j], "The values has not been changed properly in the matrix.");
            }
        }
    
    assertTrue(s.solve(), "Method returns true when sudoku is solved.");

    for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertTrue(s.legal(matrix[i][j], i, j), "The matrix is not solved correctly.");
            }
        }
    }
    
    @Test
    void unsolveablePuzzle(){
        
    }

    @Test
    void legal(){

        // assertTrue(s.legal(1,0,1), "Element is legal but not allowed to be placed.");
        // assertTrue(s.legal(2,0,3), "Element is legal but not allowed to be placed.");
        // assertTrue(s.legal(3,0,6), "Element is legal but not allowed to be placed.");
        // assertTrue(s.legal(1,3,0), "Element is legal but not allowed to be placed.");
        // assertTrue(s.legal(2,3,3), "Element is legal but not allowed to be placed.");
        // assertTrue(s.legal(3,6,6), "Element is legal but not allowed to be placed.");

        // assertFalse(s.legal(5,0,1), "Element is not legal but allowed to be placed.");
        // assertFalse(s.legal(3,0,1), "Element is not legal but allowed to be placed.");
        // assertFalse(s.legal(8,8,8), "Element is not legal but allowed to be placed.");
    }

    @Test
    void set(){
        //Sets all values for the row position in each column of current row (All columns in Row 1 = 1, Row 2 = 2, etc.)
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                s.set(i, j, i+1);
            }
        }

        //Get current matrix, check that each value has been set correctly.
        int[][] matrix = s.getMatrix();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(i+1, matrix[i][j], "The value was not set properly for this position.");
            }
        }

        //Error thrown when incorrect value is set.
        assertThrows(IllegalArgumentException.class, () -> s.set(10, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> s.set(0, 10, 0));
        assertThrows(IllegalArgumentException.class, () -> s.set(0, 0, 10)); 
    }

    @Test
    void testRemoveValue(){
    s.setMatrix(defaultMatrix);
        //Remove each value in puzzle.
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                s.remove(i, j);
            }
        }
        //Assure each value is 0.
    int [][] matrix = s.getMatrix();
       for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(0, matrix[i][j], "The value has not been removed properly in the matrix.");
            }
        }
        //Ensure an error is thrown when trying to remove outside puzzle dimensions.
        assertThrows(IllegalArgumentException.class, () -> s.remove(10, 0));
        assertThrows(IllegalArgumentException.class, () -> s.remove(0, 10));
    }

    @Test
    void clear(){
    s.setMatrix(defaultMatrix);  
    int[][] matrix = s.getMatrix();
       
       for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(matrix[i][j], defaultMatrix[i][j], "Incorrect values after getMatrix.");
            }
        }

       s.clear();
       
       for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(0, defaultMatrix[i][j], "The puzzle is not empty.");
            }
       }
    }

    @Test
    void setMatrix() {
        int[][] puzzle = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        s.setMatrix(puzzle);
        int[][] matrix = s.getMatrix();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(matrix[i][j], puzzle[i][j]);
            }
        }

    }

    @Test
    void testGetMatrix(){
        // int [][] matrixFromSolver = s.getMatrix();

        // for(int i = 0; i < 9; i++){
        //     for(int j = 0; j < 9; j++){
        //         assertEquals(defaultMatrix[i][j], matrixFromSolver[i][j]);
        //     }
        // }

        // for(int i = 0; i < 9; i++){
        //     for(int j = 0; j < 9; j++){
        //         s.set(i, j, 1);
        //     }
        // }
        // matrixFromSolver = s.getMatrix();
        
        // for(int i = 0; i < 9; i++){
        //     for(int j = 0; j < 9; j++){
        //         assertEquals(1, matrixFromSolver[i][j]);
        //     }
        // }


    }
    
}
