package connection.dbConnection;

import java.sql.Connection;

/**
 * Класс ConnectionManager
 */
public interface ConnectionManager {
	Connection getConnection();
}