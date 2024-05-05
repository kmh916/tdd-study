package vending_machine.product;

public class VendingMachineProduct {

    private final String name;
    private final int price;

    public VendingMachineProduct(String name, int price) {
        if (name == null || name.isBlank() || price <= 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name + " - " + price + "원";
    }
}
