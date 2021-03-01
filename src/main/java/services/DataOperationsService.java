/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package services;

import connection.dbConnection.*;
import query.SQLQueryData;

import java.io.IOException;
import java.sql.*;

/**
 * Класс DataOperationsService
 */
public class DataOperationsService {
	private SQLQueryData sqd = new SQLQueryData();
	ConnectionManager connectionManager = ConnectionManagerPostgeImpl.getInstance();
	//getJDBCConnect();
	Connection connection = connectionManager.getConnection();

	public boolean deleteDB() throws SQLException {

		return sqd.deletedDataSQL(connection);

	}
	public boolean insertDB() throws IOException, SQLException {
		return sqd.insertExecuteBatchQuerySQL(connection);

	}
	// public  ________ viewDataBD(){}
}