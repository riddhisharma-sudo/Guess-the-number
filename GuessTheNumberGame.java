import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {

    private int randomNumber;
    private int attemptsLeft = 5;

    private JLabel titleLabel;
    private JLabel promptLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JLabel resultLabel;

    public GuessTheNumberGame() {
        setTitle("Guess the Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        startNewGame();
    }

    private void initComponents() {
        titleLabel = new JLabel("Guess the Number Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        promptLabel = new JLabel("Enter your guess (1-100):");
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.add(titleLabel);
        panel.add(promptLabel);
        panel.add(inputField);
        panel.add(guessButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(resultLabel, BorderLayout.SOUTH);
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attemptsLeft = 5;
        resultLabel.setText("");
        inputField.setText("");
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());

            if (guess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed the number.");
                guessButton.setEnabled(false);
            } else if (guess < randomNumber) {
                resultLabel.setText("Try a higher number. Attempts left: " + (--attemptsLeft));
            } else {
                resultLabel.setText("Try a lower number. Attempts left: " + (--attemptsLeft));
            }

            if (attemptsLeft == 0) {
                resultLabel.setText("Sorry, you're out of attempts. The number was: " + randomNumber);
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGame().setVisible(true);
            }
        });
    }
}

