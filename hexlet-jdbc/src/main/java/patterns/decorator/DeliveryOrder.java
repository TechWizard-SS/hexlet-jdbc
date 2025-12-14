package patterns.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryOrder implements Order {

    private Order order;

    @Override
    public int getTotalPrice() {
        return order.getTotalPrice() + 1000;
    }
}