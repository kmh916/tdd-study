package vending_machine;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachinePrinterTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
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
        assertThat(outContent.toString()).isEqualTo(String.format(VendingMachinePrinter.BALANCE_FORMAT + "\n", machine.getBalance()));
    }

    private VendingMachine getMachine(int money) {
        VendingMachine machine = new VendingMachine();
        machine.putMoney(money);
        return machine;
    }
}
