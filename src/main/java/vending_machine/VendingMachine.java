package vending_machine;

import vending_machine.product.VendingMachineProduct;

import java.util.Collections;
import java.util.List;

public class VendingMachine {
    private int balance;

    private final List<VendingMachineProduct> products;

    public VendingMachine() {
        this.products = Collections.emptyList();
    }

    public VendingMachine(List<VendingMachineProduct> products) {
        this.products = products;
    }

    public int getBalance() {
        return this.balance;
    }

    public List<VendingMachineProduct> getProducts() {
        return products;
    }

    public void putMoney(int money) {
        if (!validateMoney(money)) {
            throw new IllegalArgumentException("invalid money : " + money);
        }
        this.balance += money;
    }

    public VendingMachineProduct order(String selectedProductName) {
        VendingMachineProduct product = products.stream()
            .filter(i -> i.getName().equals(selectedProductName))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);

        if (this.balance >= product.getPrice()) {
            return product;
        }
        throw new IllegalStateException();
    }

    private boolean validateMoney(int money) {
        return List.of(100, 500, 1000, 5000, 10000).contains(money);
    }
}
