package query;

import model.ShedulesSearch;

import java.io.*;
import java.sql.*;
import java.text.ParseException;

public interface SQLQuery {

	ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException, IOException,
		ParseException;

	boolean deletedDataSQL(Connection connection) throws SQLException;

	boolean insertExecuteBatchQuerySQL(Connection connection, InputStream fileStream) throws IOException, SQLException;

//	public boolean insertProgramSQL(Connection connection, InputStream fileStream) throws IOException, SQLException;


	public ShedulesSearch view(Connection connection) throws SQLException;

	ShedulesSearch getSheduleBy(Connection connection, String[] idList) throws SQLException;

}