package patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Circle implements Shape {
    private final int radius;

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }


    @Override
    public Shape clone() {
        return new Circle(this.radius);
    }
}
