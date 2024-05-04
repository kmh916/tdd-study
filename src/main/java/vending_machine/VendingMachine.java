package vending_machine;

import java.util.List;

public class VendingMachine {
    private int balance;

    public int getBalance() {
        return this.balance;
    }

    public void putMoney(int money) {
        if (!validateMoney(money)) {
            throw new IllegalArgumentException("invalid money : " + money);
        }
        this.balance += money;
    }

    private boolean validateMoney(int money) {
        return List.of(100, 500, 1000, 5000, 10000).contains(money);
    }
}
