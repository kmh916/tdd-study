package vending_machine.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    @DisplayName("상품명과 가격으로 상품을 생성한다.")
    void createProduct() {
        VendingMachineProduct product = new VendingMachineProduct("product1", 700);

        assertThat(product.getName()).isEqualTo("product1");
        assertThat(product.getPrice()).isEqualTo(700);
    }

}
