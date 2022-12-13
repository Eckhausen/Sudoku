package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class GUI extends JFrame {
    private Solver s;
    private int[][] tempMatrix; //Denna vi uppdaterar hela tiden.
    private JTextField[][] matrix;
    
    private int[][] examplePuzzle = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    
    public GUI(Solver sudokuSolver) { 
        createWindow("SudokuSolver", 500, 500);
        this.s = sudokuSolver;

    }
    
    public void createWindow(String title, int width, int height) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        
        JPanel matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(9,9));
        JTextField[][] matrix = new JTextField[9][9];
        this.matrix = matrix;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField tf = new JTextField(1);
                tf.setFont(new Font("SansSerif", Font.BOLD, 30));
                tf.setHorizontalAlignment(JTextField.CENTER);
                
                if ((i / 3 + j / 3) % 2 == 0) {
                    tf.setBackground(Color.decode("#779556"));
                } else {
                    tf.setBackground(Color.decode("#ebecd0"));
                }

                tf.addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (tf.getText().length()>=1){
                            e.consume();
                        }
                    }
                });
                matrix[i][j] = tf;
                matrixPanel.add(matrix[i][j]);
            }
        }
        
        JButton solveButton = new JButton("Solve"); //Ej implementerad, kan just nu mata in fel värden.
        solveButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean shouldBeSolved = true;
                for(int row = 0; row < 9; row++){
                    for(int col = 0; col < 9; col++){
                        if(matrix[row][col].getText().isEmpty()){
                            s.set(row, col, 0);
                        } else if(allowedValue(matrix[row][col].getText())){
                            s.set(row, col, Integer.parseInt(matrix[row][col].getText()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Illegal character has been identified at:\n" + "Row: " + row + "\n" + "Col: " + col);
                            shouldBeSolved = false;
                        }
                    }
                }
                if(s.solve() && shouldBeSolved){
                    tempMatrix = s.getMatrix();
                    updateWindow(matrix);
                } else if(shouldBeSolved){
                    JOptionPane.showMessageDialog(null, "A solution could not be found!");
                }      
            }
        });
        
        JButton clearButton = new JButton("Clear"); 
        clearButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                s.clear();
                tempMatrix = s.getMatrix();
                updateWindow(matrix);
            }
            
        });

        JButton setExampleMatrix = new JButton("Set example Matrix");
        setExampleMatrix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tempMatrix = examplePuzzle;
                updateWindow(matrix);
            }
            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(setExampleMatrix);
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        
        add(matrixPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

    }
    public void updateWindow(JTextField[][] newMatrix){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(tempMatrix[row][col] == 0){
                    matrix[row][col].setText(null);
                } else {
                    matrix[row][col].setText(Integer.toString(tempMatrix[row][col]));
                }
            }
        }
    }
    protected boolean allowedValue(String text) {
        if(text.matches ("1") || text.matches("2") || text.matches("3") || text.matches("4") || 
         text.matches("5") || text.matches("6") || text.matches("7") || text.matches("8") || text.matches ("9")){
             return true;
         }
         return false;
    }
}