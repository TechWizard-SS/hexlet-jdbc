package patterns.builders;

/**
 * Демонстрация трёх способов создания объекта {@link Computer}:
 *
 * <ol>
 *   <li><b>Через конструктор</b> — быстро, но нечитаемо при большом числе параметров
 *       (особенно если многие из них опциональны или имеют одинаковый тип).</li>
 *   <li><b>Через ручной Builder</b> — много кода, но полный контроль, хорошая читаемость.</li>
 *   <li><b>Через Lombok Builder</b> — минимальный код, чистый и выразительный синтаксис.</li>
 * </ol>
 *
 * <h2>Вывод:</h2>
 * Для классов с 4+ параметрами (особенно опциональными) Builder — предпочтительный подход.
 * Lombok позволяет избежать шаблонного кода без потери выразительности.
 *
 * <h2>Рекомендация:</h2>
 * В продакшне чаще используется Lombok {@code @Builder}, но понимание, как он работает под капотом,
 * необходимо.
 */

public class Main {
    public static void main (String[] args) {
        // Создание при помощи конструктора
        var computer1 = new Computer(10, 1.3, 100, 0, 1000, false, "");

        // Создание при помощи собственного билдера
        var computer2 = new ComputerBuilder()
                .withRam(10)
                .withPower(1000)
                .withCpu(1.3)
                .withSsd(100)
                .build();

        // Создание при помощи билдера, сгенерированного библиотекой Lombok
        var computer3 = Computer.builder()
                .withPower(1000)
                .withCpu(1.3)
                .withSsd(100)
                .build();

        System.out.println(computer1);
        System.out.println(computer2);
        System.out.println(computer3);
    }
}
