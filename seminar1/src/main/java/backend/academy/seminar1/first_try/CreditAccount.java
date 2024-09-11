package backend.academy.seminar1.first_try;

public class CreditAccount extends Account {

    public CreditAccount(Long accountNumber, Client client) {
        super(accountNumber, client);
    }

    @Override
    public void minusAmount(double amount) {
        balance -= amount;
    }
}
