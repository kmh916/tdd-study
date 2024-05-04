package vending_machine;

public class VendingMachinePrinter {

    private final VendingMachine machine;

    public static final String BALANCE_FORMAT = "잔액 : %s";

    public VendingMachinePrinter(VendingMachine machine) {
        this.machine = machine;
    }

    public void printBalance() {
        System.out.println(String.format(BALANCE_FORMAT, machine.getBalance()));
    }
}
