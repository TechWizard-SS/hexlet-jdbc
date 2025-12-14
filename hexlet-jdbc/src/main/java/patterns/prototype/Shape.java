package patterns.prototype;

public interface Shape extends Cloneable<Shape>{
    double getArea();
    Shape clone();
}
