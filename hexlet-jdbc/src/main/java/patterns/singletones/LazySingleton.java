package patterns.singletones;

/**
 * Реализация паттерна Singleton с ленивой инициализацией.
 *
 * <p>Экземпляр создаётся только при первом вызове {@link #getInstance()}.
 * Однако эта реализация НЕ является потокобезопасной — в многопоточной среде
 * может быть создано более одного экземпляра.
 *
 * <h2>Плюсы:</h2>
 * <ul>
 *   <li>Ленивая инициализация — объект создаётся только при необходимости.</li>
 * </ul>
 *
 * <h2>Минусы:</h2>
 * <ul>
 *   <li>Не потокобезопасна.</li>
 *   <li>Требует дополнительной синхронизации для безопасного использования в многопоточных приложениях.</li>
 * </ul>
 *
 * <h2>Когда использовать:</h2>
 * Только в однопоточных приложениях или в обучающих целях.
 */
public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}