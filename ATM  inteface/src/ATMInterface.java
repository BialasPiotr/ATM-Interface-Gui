import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ATMInterface extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton depositButton;
    private JButton balanceButton;
    private JButton exitButton;
    private JButton clearHistoryButton;
    private double balance = 0;
    private ArrayList<Transaction> transactionList = new ArrayList<>();

    public ATMInterface() {
        super("ATM Interface");
        LoginWindow loginWindow = new LoginWindow(this);
        inputField = new JTextField(10);
        inputField.addActionListener(this);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        balanceButton = new JButton("Balance");
        balanceButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.addActionListener(this);

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
        if (e.getSource() == depositButton) {
            double amount = Double.parseDouble(inputField.getText());
            balance += amount;
            Transaction transaction = new Transaction("Deposit", amount, balance);
            transactionList.add(transaction);
            outputArea.append(transaction.toString() + "\n");
            inputField.setText("");
        } else if (e.getActionCommand().equals("Withdraw")) {
            double amount = Double.parseDouble(inputField.getText());
            if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
            } else {
                balance -= amount;
                Transaction transaction = new Transaction("Withdrawal", amount, balance);
                transactionList.add(transaction);
                outputArea.append(transaction.toString() + "\n");
            }
            inputField.setText("");
        } else if (e.getSource() == balanceButton) {
            JOptionPane.showMessageDialog(this, String.format("Your current balance is: $%.2f", balance));
        } else if (e.getSource() == clearHistoryButton) {
            transactionList.clear();
            outputArea.setText("");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
