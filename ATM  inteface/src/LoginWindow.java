import javax.swing.*;

public class LoginWindow extends JDialog {
    private int attempts = 0;
    private JLabel attemptsLabel;
    private JPasswordField passwordField;

    public LoginWindow(JFrame parent) {
        super(parent, "Login", true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel passwordLabel = new JLabel("Enter your PIN:");
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            int pin = Integer.parseInt(new String(passwordField.getPassword()));
            if (pin == 1234) {
                dispose();
            } else {
                attempts++;
                attemptsLabel.setText(String.format("Attempts left: %d", 3 - attempts));
                if (attempts == 3) {
                    JOptionPane.showMessageDialog(this, "Your card is blocked.");
                    dispose();
                }
            }
        });

        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        attemptsLabel = new JLabel(String.format("Attempts left: %d", 3 - attempts));
        panel.add(attemptsLabel);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
