import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentNumber;
    private double result;
    private char operator;

    public calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        currentNumber = "";
        result = 0;
        operator = ' ';

        display = new JTextField(20);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(display, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            currentNumber += command;
            display.setText(currentNumber);
        } else if (command.equals("=")) {
            calculateResult();
            currentNumber = "";
            operator = ' ';
        } else {
            calculateResult();
            currentNumber = "";
            operator = command.charAt(0);
        }
    }

    private void calculateResult() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            switch (operator) {
                case '+':
                    result += number;
                    break;
                case '-':
                    result -= number;
                    break;
                case '*':
                    result *= number;
                    break;
                case '/':
                    if (number != 0) {
                        result /= number;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    result = number;
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                calculator frame = new calculator();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
