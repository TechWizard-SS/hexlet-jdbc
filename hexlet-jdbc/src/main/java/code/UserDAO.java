package code;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

/**
 * Реализация паттерна Data Access Object (DAO) для сущности {@link User}.
 *
 * <p>Изолирует логику доступа к базе данных от бизнес-логики.
 * Предоставляет простой API для сохранения, поиска и удаления пользователей.
 *
 * <h2>Принципы паттерна DAO:</h2>
 * <ul>
 *   <li>Инкапсуляция SQL-запросов внутри класса.</li>
 *   <li>Отсутствие зависимости от бизнес-логики.</li>
 *   <li>Использование {@link java.sql.Connection} извне (внедрение зависимости).</li>
 * </ul>
 *
 * <h2>Особенности реализации:</h2>
 * <ul>
 *   <li>Метод {@link #save(User)} поддерживает как INSERT, так и UPDATE (паттерн "upsert").</li>
 *   <li>Используется {@link java.sql.Statement#RETURN_GENERATED_KEYS} для получения ID после вставки.</li>
 *   <li>Метод {@link #find(Long)} возвращает {@link java.util.Optional}, чтобы избежать null.</li>
 *   <li>Все ресурсы (PreparedStatement) управляются через try-with-resources.</li>
 * </ul>
 *
 * <h2>Потенциальные улучшения:</h2>
 * <ul>
 *   <li>Добавить валидацию входных данных.</li>
 *   <li>Оборачивать SQLException в кастомные unchecked-исключения.</li>
 *   <li>Поддержка транзакций (через внешнее управление).</li>
 * </ul>
 */
public class UserDAO {
    private final Connection connection;

    /**
     * Создаёт DAO с заданным соединением к БД.
     *
     * @param conn соединение с базой данных (не должно быть null)
     */
    public UserDAO(Connection conn) {
        this.connection = Objects.requireNonNull(conn, "Connection must not be null");
    }

    /**
     * Сохраняет пользователя в базу данных.
     *
     * <p>Если у пользователя {@code id == null} — выполняется INSERT.
     * Иначе — UPDATE по существующему ID.
     *
     * <p>После успешной вставки метод устанавливает сгенерированный ID в объект {@code user}.
     *
     * @param user сохраняемый пользователь (не null)
     * @throws SQLException при ошибках работы с БД
     */
    public void save(User user) throws SQLException {
        if (user.getId() == null) {
            var sql = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPhone());
                stmt.executeUpdate();
                var keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    user.setId(keys.getLong(1));
                } else {
                    throw new SQLException("DB did not return generated key after INSERT");
                }
            }
        } else {
            var sql = "UPDATE users SET username = ?, phone = ? WHERE id = ?";
            try (var stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPhone());
                stmt.setLong(3, user.getId());
                stmt.executeUpdate();
            }
        }
    }

    /**
     * Удаляет пользователя по его ID (через объект).
     *
     * @param user удаляемый пользователь (должен иметь ID)
     * @throws SQLException при ошибках работы с БД
     */
    public void delete(User user) throws SQLException {
        var sql = "DELETE FROM users WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Находит пользователя по ID.
     *
     * <p>Возвращает {@link Optional#empty()}, если пользователь не найден.
     * Это позволяет избежать проверок на {@code null} и делает код более безопасным.
     *
     * @param id идентификатор пользователя
     * @return {@link Optional} с пользователем или пустой Optional
     * @throws SQLException при ошибках работы с БД
     */
    public Optional<User> find(Long id) throws SQLException {
        var sql = "SELECT username, phone FROM users WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var user = new User(rs.getString("username"), rs.getString("phone"));
                    user.setId(id);
                    return Optional.of(user);
                }
                return Optional.empty();
            }
        }
    }
}