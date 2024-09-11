package backend.academy.seminar1.first_try;

import java.util.List;

public class BankService {

    private Long curAccNumber = 0L;

    private List<Account> accounts;

    public void createAccount(Client client, AccountType type) {
        switch (type) {
            case CARD:
                accounts.add(new CardAccount(++curAccNumber, client));
                break;
            case SAVE:
                accounts.add(new SaveAccount(++curAccNumber, client));
                break;
            case COMMON:
                accounts.add(new CommonAccount(++curAccNumber, client));
                break;
            case CREDIT:
                accounts.add(new CreditAccount(++curAccNumber, client));
                break;
        }
    }

    public void transfer(Account from, Account to, double amount) {
        from.minusAmount(amount);
        to.plusAmount(amount);
    }

    public void transfer(Account from, String externalAccountId, double amount) {
        from.minusAmount(amount);
        // логика перехода на другой банк
    }
}
