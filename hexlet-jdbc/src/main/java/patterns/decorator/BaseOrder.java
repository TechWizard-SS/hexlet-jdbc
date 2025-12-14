package patterns.decorator;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BaseOrder implements Order {

    public List<Integer> prices;

    @Override
    public int getTotalPrice() {
        return prices.stream().mapToInt(x -> x).sum();
    }
}
