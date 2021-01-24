package connection;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;


public class ConfigureApp {

	private String databaseDriver;
	private String databaseHost;
	private String databasePort;
	private String databaseName;
	private String databaseUser;
	private String databasePassword;
	//	final String CONFIG = "D:\\REPOSITORIES-2\\CreateReportAndContract\\src\\main\\resources\\config.properties";
	final String CONFIG = "src\\main\\resources\\config.properties";

	public ConfigureApp() {
		init();
	}

	public Connection configurableApp() throws ClassNotFoundException, NoSuchMethodException, IOException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

		Connection conn = classGetPath();
		System.out.println("Connection to Store DB succesfull!");

		return conn;
	}


	public void init() {

		Properties ps = new Properties();

		try (InputStream fs = new FileInputStream(CONFIG)) {
			ps.load(fs);

			if (ps.getProperty("database.host").length() > 0) {
				databaseHost = ps.getProperty("database.host");
			}
			if (ps.getProperty("database.driver").length() > 0) {
				databaseDriver = ps.getProperty("database.driver");
			}
			if (ps.getProperty("database.port").length() > 0) {
				databasePort = ps.getProperty("database.port");
			}
			if (ps.getProperty("database.name").length() > 0) {
				databaseName = ps.getProperty("database.name");
			}
			if (ps.getProperty("database.user").length() > 0) {
				databaseUser = ps.getProperty("database.user");
			}
			if (ps.getProperty("database.password").length() > 0) {
				databasePassword = ps.getProperty("database.password");
			}
		} catch (IOException e) {
			System.out.println("не получили данные..." + e);
		}
	}


	public String getDatabaseDriver() {
		return databaseDriver;
	}

	public String getDatabaseHost() {
		return databaseHost;
	}

	public String getDatabasePort() {
		return databasePort;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	//
	public String getDatabasePassword() {
		return databasePassword;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
//	public String getDatabaseUrl() {
//		return databaseUrl;
//	}
	public Connection classGetPath() throws IOException, SQLException {

		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("conf.properties"))) {
			props.load(in);
		}
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public String toString() {
		return databaseDriver + databaseHost + databasePort + databaseName + databaseUser + databasePassword;
	}
}
