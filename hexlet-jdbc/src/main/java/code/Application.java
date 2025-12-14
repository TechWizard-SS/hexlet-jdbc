package code;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Пример работы с базой данных напрямую через JDBC (без DAO).
 *
 * <p>Иллюстрирует "сырой" подход:
 * <ul>
 *   <li>SQL-запросы в основном коде;</li>
 *   <li>Отсутствие инкапсуляции логики работы с БД;</li>
 *   <li>Сложность поддержки при росте функционала.</li>
 * </ul>
 *
 * <h2>Цель этого класса:</h2>
 * Показать, почему нужен паттерн DAO — чтобы избежать такого "размазывания" SQL по всему коду.
 *
 * <h2>Используемая БД:</h2>
 * H2 в memory-режиме: {@code jdbc:h2:mem:hexlet_test}
 */
public class Application {
    public static void main(String[] args) throws SQLException {

        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            String createSql = """
                CREATE TABLE users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    username VARCHAR(255),
                    phone VARCHAR(255)
                )
                """;

            try (var stmt = conn.createStatement()) {
                stmt.execute(createSql);
            }

            // Вставка данных
            var sql = "INSERT INTO users (username, phone) VALUES (?, ?)";
                try (var preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, "alan");
                preparedStatement.setString(2, "123456000");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "mol");
                preparedStatement.setString(2, "12345312");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "ken");
                preparedStatement.setString(2, "123456");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "tommy");
                preparedStatement.setString(2, "123456789");
                preparedStatement.executeUpdate();

            }

            var sql2 = "DELETE FROM users WHERE username = ?";
            try (var preparedStatement = conn.prepareStatement(sql2)) {
                preparedStatement.setString(1, "alan");
                preparedStatement.executeUpdate();
            }

            // Выборка
            String selectSql = "SELECT id, username, phone FROM users ORDER BY username";
            try (var selectStmt = conn.createStatement(); var rs = selectStmt.executeQuery(selectSql)) {

                 System.out.println("\nRemaining users:");
                 while (rs.next()) {
                    System.out.printf("id=%d, username=%s, phone=%s%n",
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("phone"));
                }
            }
        }
    }
}
