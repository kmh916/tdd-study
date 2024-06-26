package vending_machine;

import vending_machine.product.VendingMachineProduct;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class VendingMachinePrinter {

    private final VendingMachine machine;

    private final PrintStream printer;

    public static final String BALANCE_FORMAT = "잔액 : %s";

    public static final String PRODUCTS_EMPTY_MESSAGE = "자판기에 상품이 없습니다.";

    public static final String PRODUCTS_HEADER = "<상품 목록>";

    public static final String ORDER_SUCCESS_FORMAT = "%s이 출력 되었습니다.";

    public static final String ORDER_FAIL_MESSAGE = "해당 상품이 없습니다.";

    public static final String NOT_ENOUGH_MONEY_FORMAT = "잔액이 부족합니다 현재 잔액 : %s원";

    public VendingMachinePrinter(VendingMachine machine, PrintStream out) {
        this.machine = machine;
        this.printer = out;
    }

    public void printBalance() {
        this.printer.println(String.format(BALANCE_FORMAT, machine.getBalance()));
    }

    public void printProducts() {
        List<VendingMachineProduct> products = this.machine.getProducts();
        if (products.isEmpty()) {
            this.printer.println(PRODUCTS_EMPTY_MESSAGE);
            return;
        }

        this.printer.println(PRODUCTS_HEADER);
        for (int i = 0; i < products.size(); i++) {
            VendingMachineProduct product = products.get(i);
            String ableMessage = product.getPrice() <= this.machine.getBalance() ? "(구매가능)" : "";
            this.printer.printf("%s : %s%s\n", (i + 1), product, ableMessage);
        }

    }

    public void printOrderEvent(Optional<VendingMachineProduct> product) {
        product.ifPresentOrElse(
            p -> this.printer.printf(ORDER_SUCCESS_FORMAT + "\n", p.getName()),
            () -> this.printer.println(ORDER_FAIL_MESSAGE)
        );
    }

    public void printNotEnoughMoney() {
        this.printer.printf(NOT_ENOUGH_MONEY_FORMAT + "\n", this.machine.getBalance());
    }
}
