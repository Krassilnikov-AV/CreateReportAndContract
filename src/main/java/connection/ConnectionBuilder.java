package connection;

import java.sql.*;

/**
 * Класс ConnectionBuilder
 */
public interface ConnectionBuilder {
	public Connection getConnection() throws SQLException;
}