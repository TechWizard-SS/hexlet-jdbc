package code;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Демонстрационное приложение для работы с DAO-паттерном и базой данных H2 через JDBC.
 *
 * Этот класс показывает полный жизненный цикл сущности {@link User}:
 *
 * Создание таблицы в in-memory базе H2
 * Сохранение нового пользователя (INSERT)
 * Поиск пользователя по идентификатору (SELECT)
 * Обновление существующего пользователя (UPDATE)
 * Удаление пользователя (DELETE)
 *
 *
 * Используется в обучающих целях для демонстрации:
 *
 * Работы с {@link java.sql.Connection} и try-with-resources
 * Паттерна Data Access Object (DAO)
 * Базовых CRUD-операций без ORM или Spring
 *
 *
 * Зависимости:
 *
 * Драйвер H2 (в classpath)
 * Классы {@link User} и {@link UserDAO} в том же пакете
 *
 */
public class Application2 {
    public static void main(String[] args) throws SQLException {
        try (var connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            // Создаём таблицу (если нужно)
            var createTableSql = """
                CREATE TABLE users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    username VARCHAR(255),
                    phone VARCHAR(255)
                )
                """;
            try (var stmt = connection.createStatement()) {
                stmt.execute(createTableSql);
            }

            // Создаём DAO и работаем с ним
            var userDAO = new UserDAO(connection);

            // Создаём и сохраняем пользователя
            var user = new User("tommy", "123456789");
            userDAO.save(user); // после save() у user будет установлен id

            System.out.println("A user has been created with the id: " + user.getId());

            // Находим пользователя по id
            var foundUser = userDAO.find(user.getId());
            if (foundUser.isPresent()) {
                System.out.println("Found: " + foundUser.get().getName());
            }

            // Обновление
            user.setName("tommy_updated");
            user.setPhone("987654321");
            userDAO.save(user);

            var foundUser1 = userDAO.find(user.getId());
            if (foundUser1.isPresent()) {
                System.out.println("Found: " + foundUser1.get().getName());
            }

            // Проверяем, есть ли пользователь (пользователь есть, пока что не удалён)
            var maybeDeleted1 = userDAO.find(user.getId());
            System.out.println("After deletion: " + (maybeDeleted1.isPresent() ? "yes": "no"));

            // Удаляем пользователя
            userDAO.delete(user);
            System.out.println("User deleted");

            // Проверяем, что его больше нет (пользователь)
            var maybeDeleted = userDAO.find(user.getId());
            System.out.println("After deletion: " + (maybeDeleted.isPresent() ? "yes": "no"));
        }
    }
}
