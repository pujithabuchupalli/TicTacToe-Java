import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setVisible(true);
        getContentPane().setBackground(new Color(245, 245, 220));  // Cream background for the frame
    }

    private void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setBackground(new Color(53, 94, 59));  // Hunter Green for buttons
                buttons[i][j].setForeground(Color.WHITE);  // White text for contrast
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
                board[i][j] = '-';
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clicked == buttons[i][j] && buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText(String.valueOf(currentPlayer));
                    board[i][j] = currentPlayer;
                    if (checkWin(currentPlayer)) {
                        JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                        resetGame();
                        return;
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(this, "It's a draw!");
                        resetGame();
                        return;
                    }
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) return true;

            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player) return true;
        }
        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) ||
               (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '-') return false;
        return true;
    }

    private void resetGame() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(new Color(53, 94, 59));  // Reset to Hunter Green color
                board[i][j] = '-';
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
