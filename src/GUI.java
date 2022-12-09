package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private int[][] exampleSudokuGrid = {
        {0, 0, 8, 0, 0, 9, 0, 6, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 5},
        {1, 0, 2, 5, 0, 0, 0, 0, 0},
        {0, 0, 0, 2, 1, 0, 0, 9, 0},
        {0, 5, 0, 0, 0, 0, 6, 0, 0},
        {6, 0, 0, 0, 0, 0, 0, 2, 8},
        {4, 1, 0, 6, 0, 8, 0, 0, 0},
        {8, 6, 0, 0, 3, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 4, 0, 0}
    };
    
    public GUI(Solver SudokuSolver) { 
        createWindow("SudokuSolver", 500, 500);
    }
    
    private void createWindow(String title, int width, int height) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        
        JPanel matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(9,9));
        JTextField[][] matrix = new JTextField[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = new JTextField(1);
                
                matrix[i][j].setFont(new Font("SansSerif", Font.BOLD, 30));
                matrix[i][j].setHorizontalAlignment(JTextField.CENTER);
                
                if ((i / 3 + j / 3) % 2 == 0) {
                    matrix[i][j].setBackground(Color.decode("#779556"));
                } else {
                    matrix[i][j].setBackground(Color.decode("#ebecd0"));
                }
                matrixPanel.add(matrix[i][j]);
            }
        }
        
        JButton solveButton = new JButton("Solve"); //Ej implementerad, kan just nu mata in fel värden.
        solveButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //SudokuSolver 
                System.out.println("Solve");       
            }
        });
        
        
        JButton clearButton = new JButton("Clear"); //Ej implementerad
        clearButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //Solvern set matrix
                //Sen uppdatera panelen med nya brädet.
                System.out.println("Clear");
            }
            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        
        add(matrixPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Solver s = new Solver();
        GUI GUI = new GUI(s);
        GUI.setVisible(true);
        
    }
}