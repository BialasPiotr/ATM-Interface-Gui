import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATMInterface extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton depositButton, withdrawButton, balanceButton, exitButton, clearHistoryButton;
    private double balance = 0;

    public ATMInterface() {
        super("ATM Interface");

        // Input field
        inputField = new JTextField(10);
        inputField.addActionListener(this);

        // Output area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Buttons
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        balanceButton = new JButton("Balance");
        balanceButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.addActionListener(this);

        // Layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter amount:"));
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(clearHistoryButton);
        buttonPanel.add(exitButton);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.EAST);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String inputText = inputField.getText();

        if (cmd.equals("Deposit")) {
            try {
                double amount = Double.parseDouble(inputText);
                balance += amount;
                outputArea.append(String.format("Deposited $%.2f\n", amount));
                inputField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Enter how much you want to Deposit\n");
            }
        } else if (cmd.equals("Withdraw")) {
            try {
                double amount = Double.parseDouble(inputText);
                if (amount > balance) {
                    outputArea.append("You do not have any funds in your account\n");
                } else {
                    balance -= amount;
                    outputArea.append(String.format("Withdrew $%.2f\n", amount));
                    inputField.setText("");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Enter how much you want to withdraw\n");
            }
        } else if (cmd.equals("Balance")) {
            outputArea.append(String.format("Current balance: $%.2f\n", balance));
        } else if (cmd.equals("Clear History")) {
            outputArea.setText("");
        } else if (cmd.equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}