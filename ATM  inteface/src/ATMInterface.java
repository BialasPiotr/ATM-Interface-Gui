import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATMInterface extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton balanceButton;
    private JButton clearHistoryButton;
    private JButton exitButton;
    private JLabel accountBalanceLabel;
    private double balance = 0;
    private DefaultListModel<String> transactionListModel = new DefaultListModel<String>();

    public ATMInterface() {
        super("ATM Interface");

        // create and set up components
        JLabel amountLabel = new JLabel("Enter amount:");
        inputField = new JTextField(10);
        inputField.addActionListener(this);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);

        balanceButton = new JButton("Balance");
        balanceButton.addActionListener(this);

        clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        accountBalanceLabel = new JLabel(String.format("Account Balance: $%.2f", balance));

        JList<String> transactionList = new JList<String>(transactionListModel);
        JScrollPane transactionListScrollPane = new JScrollPane(transactionList);

        // set up layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(amountLabel);
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(clearHistoryButton);
        buttonPanel.add(exitButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(accountBalanceLabel, BorderLayout.NORTH);
        rightPanel.add(transactionListScrollPane, BorderLayout.CENTER);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.EAST);

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
            transactionListModel.addElement(transaction.toString());
            outputArea.append(transaction.toString() + "\n");
            accountBalanceLabel.setText(String.format("Account Balance: $%.2f", balance));
            inputField.setText("");
        } else if (e.getSource() == withdrawButton) {
            double amount = Double.parseDouble(inputField.getText());
            if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
            } else {
                balance -= amount;
                Transaction transaction = new Transaction("Withdrawal", amount, balance);
                transactionListModel.addElement(transaction.toString());
                outputArea.append(transaction.toString() + "\n");
                accountBalanceLabel.setText(String.format("Account Balance: $%.2f", balance));
            }
            inputField.setText("");
        } else if (e.getSource() == balanceButton) {
            JOptionPane.showMessageDialog(this, String.format("Your current balance is: $%.2f", balance));
        } else if (e.getSource() == clearHistoryButton) {
            transactionListModel.clear();
            outputArea.setText("");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
