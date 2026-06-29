package models;
/*
Explanation:
- when we create an expense, we need to store some information about it.
- Expense is something that we need to make it an object.
- put those information here and use them in your code.
 */

import models.enums.Currency;

public class Expense {
    private final Currency currency = Currency.GTC; //this means all expenses are saved in GTC currency
    private long amount;
    private User creditor; //talabkar
    private User debtor; //bedehkar

    public User getCreditor() {
        return creditor;
    }

    public void setCreditor(User creditor) {
        this.creditor = creditor;
    }

    public User getDebtor() {
        return debtor;
    }

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }

    public Currency getCurrency() {
        return currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Expense(long amount, User creditor, User debtor) {
        this.amount = amount;
        this.creditor = creditor;
        this.debtor = debtor;
    }
}
