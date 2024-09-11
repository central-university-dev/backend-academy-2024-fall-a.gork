package backend.academy.seminar1.solution.domain.transaction.self;

import backend.academy.seminar1.solution.domain.account.entity.Account;

// С карты на вклад
public class CardToDepositTransaction extends SelfTransaction {
    public CardToDepositTransaction(
        String transactionId,
        double amount,
        Account sourceAccount,
        Account destinationAccount
    ) {
        super(transactionId, amount, sourceAccount, destinationAccount);
    }

    @Override
    public void execute() {
        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
            System.out.println("Funds transferred from card to deposit account.");
        } else {
            System.out.println("Insufficient funds for transfer from card to deposit.");
        }
    }
}
