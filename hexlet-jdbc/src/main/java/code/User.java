package code;

/**
 * Модель данных, представляющая пользователя в системе.
 *
 * <p>Используется в связке с {@link UserDAO} для передачи данных между
 * бизнес-логикой и базой данных.
 *
 * <h2>Особенности:</h2>
 * <ul>
 *   <li>Содержит поля, соответствующие колонкам таблицы {@code users}.</li>
 *   <li>Имеет конструктор по умолчанию (требуется для некоторых фреймворков и ручного маппинга).</li>
 *   <li>Поддерживает установку/получение {@code id}, который генерируется БД при вставке.</li>
 * </ul>
 *
 * <h2>Замечание:</h2>
 * В продвинутых приложениях такие классы часто делают:
 * <ul>
 *   <li>Неизменяемыми (immutable) — без сеттеров;</li>
 *   <li>Или используют record (Java 14+) для краткости.</li>
 * </ul>
 */
public class User {
    private Long id;
    private String name;
    private String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
