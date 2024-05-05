package vending_machine;

import org.junit.jupiter.api.*;
import vending_machine.product.VendingMachineProduct;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachinePrinterTest {

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("자판기의 잔액이 출력된다.")
    void printBalance() {
        int money = 1000;
        VendingMachine machine = getMachine(money);
        VendingMachinePrinter printer = new VendingMachinePrinter(machine);

        printer.printBalance();
        assertThat(outContent).hasToString(String.format(VendingMachinePrinter.BALANCE_FORMAT + "\n", machine.getBalance()));
    }

    @Test
    @DisplayName("자판기에 상품이 없는 경우 '자판기에 상품이 없습니다.' 메시지가 출력된다. ")
    void printEmptyProducts() {
        VendingMachinePrinter vendingMachinePrinter = new VendingMachinePrinter(getMachine(10000));

        vendingMachinePrinter.printProducts();

        assertThat(outContent.toString()).hasToString(VendingMachinePrinter.PRODUCTS_EMPTY_MESSAGE + "\n");

    }

    @Test
    @DisplayName("자판기에 상품이 있는 경우 상품 리스트가 출력된다. (구매 가능 상품의 경우 구매 가능 메시지가 함께 출력된다.)")
    void printProducts() {
        List<VendingMachineProduct> products = List.of(
            new VendingMachineProduct("이슬톡톡", 2400)
        );
        VendingMachinePrinter vendingMachinePrinter = new VendingMachinePrinter(getMachine(10000, products));

        vendingMachinePrinter.printProducts();

        assertThat(outContent.toString()).contains(products.get(0) + "(구매가능)");
    }

    private VendingMachine getMachine(int money) {
        VendingMachine machine = new VendingMachine();
        machine.putMoney(money);
        return machine;
    }

    private VendingMachine getMachine(int money, List<VendingMachineProduct> products) {
        VendingMachine machine = new VendingMachine(products);
        machine.putMoney(money);
        return machine;
    }
}
