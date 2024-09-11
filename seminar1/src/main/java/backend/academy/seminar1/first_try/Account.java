package backend.academy.seminar1.first_try;

public abstract class Account {
    protected Long accountNumber;
    protected Client client;
    protected double balance;

    public Account(Long accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
    }

    public void plusAmount(double amount) {
        balance += amount;
    }

    public void minusAmount(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new RuntimeException("Not enough money on the account!");
        }
    }

    public double getBalance() {
        return balance;
    }
}
