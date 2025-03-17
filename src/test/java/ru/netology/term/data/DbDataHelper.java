package ru.netology.term.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDataHelper {
    private static final QueryRunner queryRunner = new QueryRunner();
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/shop_db";
    private static final String dbUser = "tester";
    private static final String dbPassword = "!Q2w3e4r";

    private DbDataHelper() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @SneakyThrows
    public static long countOrders() {
        var sql = "SELECT COUNT(*) FROM order_entity;";
        try (var conn = getConnection()) {
            return queryRunner.query(conn, sql, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection()) {
            return queryRunner.query(conn, sql, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void cleanDB() {
        try (var conn = getConnection()) {
            conn.setAutoCommit(false);
            queryRunner.update(conn, "DELETE FROM credit_request_entity");
            queryRunner.update(conn, "DELETE FROM order_entity");
            queryRunner.update(conn, "DELETE FROM payment_entity");
            conn.commit();
        }
    }
}
