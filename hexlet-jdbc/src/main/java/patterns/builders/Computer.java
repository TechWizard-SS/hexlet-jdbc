package patterns.builders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

/**
 * Класс, представляющий конфигурацию компьютера.
 *
 * <p>Реализация поддерживает:
 * <ul>
 *   <li>Генерацию всех-параметров конструктора — {@link lombok.AllArgsConstructor}</li>
 *   <li>Генерацию Builder'а — {@link lombok.Builder}</li>
 *   <li>Автоматические сеттеры — {@link lombok.Setter}</li>
 * </ul>
 *
 * <h2>Особенность Builder'а от Lombok:</h2>
 * Параметр {@code setterPrefix = "with"} указывает Lombok'у генерировать
 * методы вида {@code withRam()}, а не стандартные {@code ram()}.
 * Это делает интерфейс более читаемым и похожим на ручную реализацию.
 *
 * <h2>Предупреждение:</h2>
 * Наличие {@code @Setter} делает объект изменяемым. Для истинного "immutable pattern"
 * сеттеры следует убрать, а поля сделать {@code final}.
 */

@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Computer {
    private int ram;
    private double cpu;
    private int ssd;
    private int hdd;
    private int power;
    private boolean hasCdDrive;
    private String videoAdapterType;
}
