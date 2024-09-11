package backend.academy.seminar1.first_try;

import java.time.Instant;

public abstract class Transaction {
    private Long id;
    private double amount;
    private Instant date;

    public Transaction(Long id, double amount, Instant date) {
    }
}

public class InternalTransaction extends Transaction {
    private Account from;
    private Account to;

    public InternalTransaction(Long id, double amount, Instant date, Account from, Account to) {
        super(id, amount, date);
        this.from = from;
        this.to = to;
    }
}

public class ExtTransaction extends Transaction {
    private Account from;
    private String externalAccountId;

    public ExtTransaction(Long id, double amount, Instant date, Account from, String externalAccountId) {
        super(id, amount, date);
        this.from = from;
        this.externalAccountId = externalAccountId;
    }
}
