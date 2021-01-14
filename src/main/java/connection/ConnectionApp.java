package connection;

import java.io.IOException;
import java.sql.*;

/**
 * Класс ExampleConnection, устанавливает соединение с базой данных
 * прописывает название и URL базы данных
 */
public class ConnectionApp {

	private ConfigureApp conf = new ConfigureApp();

//	public static void main(String[] args) throws SQLException, IOException {
//		ConnectionApp conApp = new ConnectionApp();
////
//		SQLQueryDate SQLQueryDate = new SQLQueryDate();
//		conApp.getNameURL();
//
////		SQLQueryDate.deletedDataSQL();
////		SQLQueryDate.insertExecuteBatchQuerySQL();
////		SQLQueryDate.insertExecuteUpdateQuerySQL();
//
////		SQLQueryDate.insertStartDateSQL();
////		SQLQueryDate.insertStartTimeSQL();
//
//		exQuery.writeWithCompileQuery(100);
//		exQuery.writeInABatchWithCompiledQuery(100);
//	}


	/*
	метод, который возвращает url базы данных, прописанной в property файле
}*/
	public String getURL() {
		String databaseDriver = conf.getDatabaseDriver();
		String databaseHost = conf.getDatabaseHost();
		String databasePort = conf.getDatabasePort();
		String databaseName = conf.getDatabaseName();

//		String url =databaseDriver.concat("://").concat(databaseHost).concat(":").concat(databasePort).concat("/").concat(databaseName);
//		String url =databaseDriver +"://"+databaseHost+"/"+databasePort+"/"+databaseName;
		String url =databaseDriver +"://"+databaseHost+":"+databasePort+"/"+databaseName;
		return url;
	}

	public Connection getPostConnection() throws SQLException, IOException {
		System.out.println("Устанавливаем соединение с БД...");

		Connection connection = DriverManager.getConnection(getURL(),
			conf.getDatabaseUser(),
			conf.getDatabasePassword());
		return connection;
	}

	public  void getNameURL() throws IOException {

		System.out.println("Название базы данных: " + conf.getDatabaseName());
		System.out.println("URL базы данных: " + getURL());
	}
}