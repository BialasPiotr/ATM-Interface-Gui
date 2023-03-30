import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private double amount;
    private double balance;

    public Transaction(String type, double amount, double balance) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%s  %8s  $%.2f  $%.2f", date, type, amount, balance);
    }
}
