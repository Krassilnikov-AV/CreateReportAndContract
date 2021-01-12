package connection;

import java.io.*;
import java.util.Properties;


public class ConfigurateApp {

//	private String path;         // путь для сохранения на сервере
	private String databaseDriver;
	private String databaseHost;
	private String databasePort;
	private String databaseName;
	private String databaseUser;
	private String databasePassword;
//	private String databaseUrl;
//	final String CONFIG = "D:\\REPOSITORIES-2\\CreateReportAndContract\\src\\main\\resources\\config.properties";
	final String CONFIG = "src\\main\\resources\\config.properties";

	public ConfigurateApp()  {
			init();
	}

	public  void init() {

		Properties ps = new Properties();

		try (Reader fs = new FileReader("config.properties")) {
			File f = new File("config.properties");
//			FileInputStream fs1 = new FileInputStream(f);
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
//			if (ps.getProperty("pathStorage").length() > 0) {
//				path = ps.getProperty("pathStorage");
//			}
//			return ps;
		}
		catch (IOException e) {
			System.out.println("не получили данные..." + e);
		}
	}

//	public String getPath() {
//		return path;
//	}

    public String getDatabaseDriver() {
        return databaseDriver;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseName() { return databaseName;  }

	public String getDatabaseUser() {
		return databaseUser;
	}

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


	@Override
	public String toString() {
		return databaseDriver + databaseHost + databasePort + databaseName + databaseUser + databasePassword;
	}
}
