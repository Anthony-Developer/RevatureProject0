package dev.ajimenez.entities;

public class Account {

    int accountId;
    String accountType;
    int accountNumber;
    float balance;
    int clientId;

    public Account() {

    }

    public Account(int accountId, String accountType, int accountNumber, float balance, int clientId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.clientId = clientId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Account {" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", clientId=" + clientId +
                '}';
    }
}
