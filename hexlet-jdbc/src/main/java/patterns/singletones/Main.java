package patterns.singletones;

/**
 * Демонстрационный класс для проверки корректности реализаций паттерна Singleton.
 *
 * <p>Проверяет, что все три реализации ({@link EnumSingleton}, {@link LazySingleton},
 * {@link SimpleSingleton}) действительно возвращают один и тот же экземпляр при
 * многократных вызовах метода получения экземпляра.
 *
 * <p>Вывод программы должен быть:
 * <pre>
 * true
 * true
 * true
 * </pre>
 *
 * Это подтверждает, что каждая реализация соблюдает основное правило Singleton:
 * <i>"Гарантированно только один экземпляр класса в течение жизни приложения"</i>.
 *
 * <h2>Примечание:</h2>
 * Этот класс предназначен исключительно для обучающих целей и демонстрации.
 * В реальном приложении такие проверки лучше выносить в модульные тесты (JUnit).
 */
public class Main {
    public static void main(String[] args) {
        // Проверка Enum-реализации
        var singleton1 = EnumSingleton.INSTANCE;
        var singleton2 = EnumSingleton.INSTANCE;
        System.out.println(singleton1 == singleton2); // true

        // Проверка ленивой реализации
        var singleton3 = LazySingleton.getInstance();
        var singleton4 = LazySingleton.getInstance();
        System.out.println(singleton3 == singleton4); // true

        // Проверка жадной (eager) реализации
        var singleton5 = SimpleSingleton.getInstance();
        var singleton6 = SimpleSingleton.getInstance();
        System.out.println(singleton5 == singleton6); // true
    }
}
