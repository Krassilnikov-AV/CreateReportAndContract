/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package services;

import connection.dbConnection.*;
import createDocument.*;
import model.Shedules;
import query.SQLQueryDataImpl;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

/**
 * Класс DataOperationsService
 */
public class DataOperationsService {
	private SQLQueryDataImpl sqd = new SQLQueryDataImpl();
	private CreateScheduleReport createScheduleReport = new CreateScheduleReport();
	private ContractTeacher contractTeacher = new ContractTeacher();

		ConnectionManager connectionManager = ConnectionManagerPostgeImpl.getInstance();
	//getJDBCConnect();
	Connection connection = connectionManager.getConnection();

	public boolean deleteDB() throws SQLException {

		return sqd.deletedDataSQL(connection);

	}
	public boolean insertDB() throws IOException, SQLException {
		return sqd.insertExecuteBatchQuerySQL(connection);

	}
	 public Shedules viewDataDB() throws SQLException {
		return sqd.view(connection);
	 }
	public void createDoc() throws SQLException, ParseException {
		createScheduleReport.createDoc(connection);
	}
	public void createDocTeacher() throws SQLException, ParseException {
		contractTeacher.createDocTeacher();
	}
}