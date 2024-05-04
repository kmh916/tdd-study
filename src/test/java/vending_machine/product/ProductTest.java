package vending_machine.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    @DisplayName("상품명과 가격으로 상품을 생성한다.")
    void createProduct() {
        VendingMachineProduct product = new VendingMachineProduct("product1", 700);

        assertThat(product.getName()).isEqualTo("product1");
        assertThat(product.getPrice()).isEqualTo(700);
    }

    @Test
    @DisplayName("상품명은 빈 문자열 혹은 null 일 경우 IllegalArgumentException 을 던진다.")
    void createProduct() {
        assertThatThrownBy(() -> new VendingMachineProduct(null, 700)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new VendingMachineProduct("", 700)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("상품 가격이 0이하 일 경우 IllegalArgumentException 을 던진다.")
    void createProduct() {
        assertThatThrownBy(() -> new VendingMachineProduct("product", 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new VendingMachineProduct("product", -100)).isInstanceOf(IllegalArgumentException.class);
    }

}
