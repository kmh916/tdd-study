package vending_machine;

import vending_machine.product.VendingMachineProduct;

import java.util.List;

public class VendingMachinePrinter {

    private final VendingMachine machine;

    public static final String BALANCE_FORMAT = "잔액 : %s";

    public static final String PRODUCTS_EMPTY_MESSAGE = "자판기에 상품이 없습니다.";

    public static final String PRODUCTS_HEADER = "<상품 목록>";

    public VendingMachinePrinter(VendingMachine machine) {
        this.machine = machine;
    }

    public void printBalance() {
        System.out.println(String.format(BALANCE_FORMAT, machine.getBalance()));
    }

    public void printProducts() {
        List<VendingMachineProduct> products = this.machine.getProducts();
        if (products.isEmpty()) {
            System.out.println(PRODUCTS_EMPTY_MESSAGE);
            return;
        }

        System.out.println(PRODUCTS_HEADER);
        for (int i = 0; i < products.size(); i++) {
            VendingMachineProduct product = products.get(i);
            String ableMessage = "";
            if (product.getPrice() <= this.machine.getBalance()) {
                ableMessage = "(구매가능)";
            }

            System.out.printf("%s : %s%s\n", (i + 1), product, ableMessage);
        }

    }
}
