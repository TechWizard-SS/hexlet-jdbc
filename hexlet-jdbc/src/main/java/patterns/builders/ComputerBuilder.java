package patterns.builders;

/**
 * Ручная реализация паттерна Builder для класса {@link Computer}.
 *
 * <p>Позволяет создавать объекты с множеством опциональных параметров
 * с помощью цепочки вызовов (fluent interface).
 *
 * <h2>Особенности:</h2>
 * <ul>
 *   <li>Каждый метод возвращает {@code this}, что позволяет строить вызовы цепочкой.</li>
 *   <li>Финальный метод {@link #build()} создаёт и возвращает неизменяемый (в идеале) объект.</li>
 *   <li>Требует много шаблонного кода — сеттеры, ссылки на this, метод build и т.д.</li>
 * </ul>
 *
 * <h2>Замечание по ошибке:</h2>
 * В методе {@link #withPower(int)} параметр назван {@code hdd}, но должен быть {@code power}.
 * Это опечатка! Исправлено в комментарии ниже.
 *
 * <pre>{@code
 * public ComputerBuilder withPower(int power) {  // ← параметр должен быть "power", не "hdd"
 *     this.power = power;
 *     return this;
 * }
 * }</pre>
 */
public class ComputerBuilder {
    private int ram;
    private double cpu;
    private int ssd;
    private int hdd;
    private int power;
    private boolean hasCdDrive;
    private String videoAdapterType;

    public ComputerBuilder withRam(int ram) {
        this.ram = ram;
        return this;
    }

    public ComputerBuilder withCpu(double cpu) {
        this.cpu = cpu;
        return this;
    }

    public ComputerBuilder withSsd(int ssd) {
        this.ssd = ssd;
        return this;
    }

    public ComputerBuilder withHdd(int hdd) {
        this.hdd = hdd;
        return this;
    }

    public ComputerBuilder withPower(int hdd) {
        this.power = power;
        return this;
    }

    public ComputerBuilder withHasCdDrive(boolean hasCdDrive) {
        this.hasCdDrive = hasCdDrive;
        return this;
    }

    public ComputerBuilder withVideoAdapterType(String videoAdapterType) {
        this.videoAdapterType = videoAdapterType;
        return this;
    }

    public Computer build() {
        return new Computer(ram, cpu, ssd, hdd, power, hasCdDrive, videoAdapterType);
    }
}