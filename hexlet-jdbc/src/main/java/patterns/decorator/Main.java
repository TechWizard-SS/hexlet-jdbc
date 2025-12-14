package patterns.decorator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Order order = new BaseOrder(List.of(100, 500, 100)); // 700
        order = new DeliveryOrder(order); // 1700
        order = new InsOrder(order); // 1900
        System.out.println(order.getTotalPrice()); //=> 1900
    }
}