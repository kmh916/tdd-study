package vending_machine;

import org.junit.jupiter.api.*;
import util.OutputTest;
import vending_machine.product.VendingMachineProduct;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachinePrinterTest extends OutputTest {

    @Test
    @DisplayName("자판기의 잔액이 출력된다.")
    void printBalance() {
        int money = 1000;
        VendingMachine machine = getMachine(money);
        VendingMachinePrinter printer = getPrinter(machine);

        printer.printBalance();
        assertThat(output).hasToString(String.format(VendingMachinePrinter.BALANCE_FORMAT + "\n", machine.getBalance()));
    }

    @Test
    @DisplayName("자판기에 상품이 없는 경우 '자판기에 상품이 없습니다.' 메시지가 출력된다. ")
    void printEmptyProducts() {
        VendingMachinePrinter vendingMachinePrinter = getPrinter(getMachine(10000));

        vendingMachinePrinter.printProducts();

        assertThat(output.toString()).hasToString(VendingMachinePrinter.PRODUCTS_EMPTY_MESSAGE + "\n");

    }

    @Nested
    @DisplayName("자판기에 상품이 있는 경우")
    class PrintProducts {
        final List<VendingMachineProduct> products = List.of(
            new VendingMachineProduct("이슬톡톡", 2400)
        );
        @Test
        @DisplayName("구매 가능 상품의 경우 '상품명 + (구매가능)' 이 출력된다.")
        void printProductsWhenAbleToBuy() {
            VendingMachinePrinter vendingMachinePrinter = getPrinter(getMachine(10000, products));

            vendingMachinePrinter.printProducts();

            assertThat(output.toString()).contains(products.get(0) + "(구매가능)");
        }

        @Test
        @DisplayName("구매 불가능 상품의 경우 '상품명' 이 출력된다.")
        void printProductsWhenUnAbleToBuy() {
            VendingMachinePrinter vendingMachinePrinter = getPrinter(getMachine(1000, products));

            vendingMachinePrinter.printProducts();

            assertThat(output.toString()).contains(products.get(0).toString());
            assertThat(output.toString()).doesNotContain("구매가능");
        }
    }

    @Nested
    @DisplayName("상품 주문시")
    class PrintOrderEvent {
        @Test
        @DisplayName("상품이 정상적으로 주문되었다면 '(상품명)이 출력 되었습니다.'를 출력한다.")
        void onSuccess() {
            VendingMachinePrinter printer = getPrinter(getMachine(1000));

            String productName = "이슬톡톡";
            VendingMachineProduct p = new VendingMachineProduct(productName, 2400);
            Optional<VendingMachineProduct> product = Optional.of(p);

            printer.printOrderEvent(product);

            assertThat(output.toString()).isEqualTo(String.format(VendingMachinePrinter.ORDER_SUCCESS_FORMAT + "\n", p.getName()));
        }

        @Test
        @DisplayName("상품이 없다면 '해당 상품이 없습니다.'를 출력한다.")
        void onNotFound() {
            VendingMachinePrinter printer = getPrinter(getMachine(1000));

            Optional<VendingMachineProduct> product = Optional.empty();

            printer.printOrderEvent(product);

            assertThat(output.toString()).isEqualTo(VendingMachinePrinter.ORDER_FAIL_MESSAGE + "\n");
        }

        @Test
        @DisplayName("잔액이 충분하지 않다면 '잔액이 부족합니다 현재 잔액 : (잔액)원' 메시지를 출력한다.")
        void onNotEnoughMoney() {
            VendingMachinePrinter printer = getPrinter(getMachine(1000));

            printer.printNotEnoughMoney();

            assertThat(output.toString()).isEqualTo(String.format(VendingMachinePrinter.NOT_ENOUGH_MONEY_FORMAT + "\n", 1000));
        }

    }

    private VendingMachinePrinter getPrinter(VendingMachine machine) {
        return new VendingMachinePrinter(machine, new PrintStream(output));
    }

    private VendingMachine getMachine(int money) {
        return getMachine(money, Collections.emptyList());
    }

    private VendingMachine getMachine(int money, List<VendingMachineProduct> products) {
        VendingMachine machine = new VendingMachine(products);
        machine.putMoney(money);
        return machine;
    }
}
