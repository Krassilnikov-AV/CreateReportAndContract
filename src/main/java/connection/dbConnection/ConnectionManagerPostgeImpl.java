
package connection.dbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class ConnectionManagerPostgeImpl implements ConnectionManager {
	private static ConnectionManager connectionManager;
	private Properties properties = new Properties();

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManagerPostgeImpl();
		}
		return connectionManager;
	}

	private ConnectionManagerPostgeImpl() {
		init();
	}

	private void init() {
		properties = new Properties();
		try {
			properties.load(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("usersKey.properties")
			);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(
				getDbURL(),
				getDbUsername(),
				getDbPassword()
			);
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
		}
		return connection;
	}


	private String getDbURL() {
		return properties.getProperty("db.url");

	}

	private String getDbUsername() {
		return properties.getProperty("db.username");
	}

	private String getDbPassword() {
		return properties.getProperty("db.password");
	}
}
