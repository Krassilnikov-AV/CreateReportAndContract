package connection.dbConnection;

import java.sql.Connection;


public interface ConnectionManager {
	Connection getConnection();
}