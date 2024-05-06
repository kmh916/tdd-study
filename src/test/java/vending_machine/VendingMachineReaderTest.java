package vending_machine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vending_machine.product.VendingMachineProduct;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.mockito.Mockito.verify;

class VendingMachineReaderTest {

    private final VendingMachineProduct product1 = new VendingMachineProduct("이슬톡톡", 2400);
    private final List<VendingMachineProduct> products = List.of(product1);

    @Test
    @DisplayName("상품 선택시 해당 상품이 있을 경우 VendingMachine.order와 VendingMachinePrinter.onOrderSuccess를 호출한다.")
    void readSuccess() {
        String selectedProductName = "이슬톡톡";
        VendingMachine machine = new VendingMachine(products);
        VendingMachinePrinter printer = new VendingMachinePrinter(machine);
        VendingMachineReader reader = new VendingMachineReader(
            printer,
            machine,
            new ByteArrayInputStream(selectedProductName.getBytes())
        );

        reader.readProductName();

        verify(machine).order(product1.getName());
        verify(printer).onOrderSuccess(product1);
    }
}
