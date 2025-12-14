package patterns.prototype;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Square implements Shape {
    private final int a;


    @Override
    public double getArea() {
        return a * a;
    }

    @Override
    public Shape clone() {
        return new Square(a);
    }
}
