package vending_machine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VendingMachineTest {
    @ParameterizedTest
    @ValueSource(ints = {100, 500, 1000, 5000, 10000})
    @DisplayName("자판기에 금액 투입시 정상 금액일 경우 자판기 잔액이 증가한다")
    void putValidMoneyIntoMachine(int money) {
        VendingMachine machine = new VendingMachine();
        machine.putMoney(money);

        assertThat(machine.getBalance()).isEqualTo(money);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 500, 1000, 5000, 10000})
    @DisplayName("자판기에 금액 투입시 기존 잔액이 존재 할 경우 잔액은 (잔액 + 투입금액) 이다.")
    void putValidMoneyIntoMachineWhenExistsBalance(int money) {
        int balance = 100;
        VendingMachine machine = new VendingMachine();

        machine.putMoney(balance);
        machine.putMoney(money);

        assertThat(machine.getBalance()).isEqualTo(money + balance);
    }

    @ParameterizedTest
    @ValueSource(ints = {157, 665, 191283})
    @DisplayName("자판기에 올바르지 않은 금액을 입력할 경우 IllegalArgumentException을 던진다.")
    void putInvalidMoney(int money) {
        VendingMachine machine = new VendingMachine();

        assertThatThrownBy(() -> machine.putMoney(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("상품 리스트로 자판기를 생성한다.")
    void createWithProducts() {
        List<VendingMachineProduct> products = List.of(
            new VendingMachineProduct("product1", 700),
            new VendingMachineProduct("product2", 800)
        );
        VendingMachine machine = new VendingMachine(products);

        assertThat(machine.getProducts()).isEqualTo(products);
    }
}
