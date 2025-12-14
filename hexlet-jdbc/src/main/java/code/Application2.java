package code;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Демонстрация использования паттерна DAO через класс {@link UserDAO}.
 *
 * <p>Показывает преимущества:
 * <ul>
 *   <li>Чистый и понятный клиентский код;</li>
 *   <li>Отделение логики работы с БД;</li>
 *   <li>Легкость тестирования и поддержки.</li>
 * </ul>
 *
 * <h2>Выполняемые операции:</h2>
 * <ol>
 *   <li>Создание и сохранение пользователя;</li>
 *   <li>Поиск по ID;</li>
 *   <li>Обновление данных;</li>
 *   <li>Удаление и проверка отсутствия.</li>
 * </ol>
 *
 * <h2>Безопасность:</h2>
 * Можно использовать {@link Optional} для избежания NullPointerException.
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
