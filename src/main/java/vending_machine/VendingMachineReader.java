package vending_machine;

import vending_machine.product.VendingMachineProduct;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class VendingMachineReader {

    private final VendingMachinePrinter printer;

    private final VendingMachine machine;

    private final Scanner scanner;

    public VendingMachineReader(VendingMachinePrinter printer, VendingMachine machine, InputStream inputStream) {
        this.printer = printer;
        this.machine = machine;
        this.scanner = new Scanner(inputStream);
    }

    public void readProductName() {
        VendingMachineProduct product = machine.order(scanner.nextLine());
        printer.onOrderSuccess(product);
    }
}
