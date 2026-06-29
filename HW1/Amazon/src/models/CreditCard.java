package models;

public class CreditCard {
    String cardNumber;
    CreditCardExpirationDate expirationDate;
    String cvv;
    double accountBalance;
    int ID;

    public CreditCard(String cardNumber, CreditCardExpirationDate expirationDate, String cvv, double accountBalance, int ID) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.accountBalance = accountBalance;
        this.ID = ID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public CreditCardExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(CreditCardExpirationDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
