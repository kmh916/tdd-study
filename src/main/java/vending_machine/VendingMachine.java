package vending_machine;

import vending_machine.product.VendingMachineProduct;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VendingMachine {
    private int balance;

    private final Map<String, VendingMachineProduct> products;

    public VendingMachine() {
        this.products = Collections.emptyMap();
    }

    public VendingMachine(List<VendingMachineProduct> products) {
        this.products = products.stream()
            .collect(
                Collectors.toMap(
                    VendingMachineProduct::getName,
                    Function.identity()
                )
            );
    }

    public int getBalance() {
        return this.balance;
    }

    public List<VendingMachineProduct> getProducts() {
        return this.products.values()
            .stream()
            .toList();
    }

    public void putMoney(int money) {
        if (!validateMoney(money)) {
            throw new IllegalArgumentException("invalid money : " + money);
        }
        this.balance += money;
    }

    public VendingMachineProduct order(String selectedProductName) {
        VendingMachineProduct product = this.products.get(selectedProductName);

        if (product == null) {
            throw new IllegalArgumentException();
        }

        if (this.balance >= product.getPrice()) {
            return product;
        }
        throw new IllegalStateException();
    }

    private boolean validateMoney(int money) {
        return List.of(100, 500, 1000, 5000, 10000).contains(money);
    }
}
