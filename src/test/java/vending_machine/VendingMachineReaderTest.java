package vending_machine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vending_machine.product.VendingMachineProduct;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class VendingMachineReaderTest {

    private final VendingMachineProduct product1 = new VendingMachineProduct("이슬톡톡", 2400);
    VendingMachinePrinter printer = mock();

    VendingMachine machine = mock();

    @Test
    @DisplayName("상품 선택시 VendingMachine.order와 VendingMachinePrinter.printOrderEvent를 호출한다.")
    void readSuccess() {
        Optional<VendingMachineProduct> product = Optional.of(product1);
        Mockito.when(machine.order(any())).thenReturn(product);
        String selectedProductName = "이슬톡톡";
        VendingMachineReader reader = new VendingMachineReader(
            printer,
            machine,
            new ByteArrayInputStream(selectedProductName.getBytes())
        );

        reader.readProductName();

        verify(machine).order(product1.getName());
        verify(printer).printOrderEvent(product);
    }
}
