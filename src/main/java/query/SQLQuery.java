package query;

import model.ShedulesSearch;

import java.io.*;
import java.sql.*;
import java.text.ParseException;

public interface SQLQuery {

	public ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException, IOException,
		ParseException;

	public boolean deletedDataSQL(Connection connection) throws SQLException;

	public boolean insertTeacherSQL(Connection connection, InputStream fileStream) throws IOException,	SQLException;
//	boolean insertGroupData(Connection connection, InputStream fileStream) throws IOException, SQLException;

	boolean insertExecuteBatchQuerySQL(Connection connection, InputStream fileStream) throws IOException,
		SQLException;
	public boolean insertProgramSQL(Connection connection,
									InputStream fileStream) throws IOException, SQLException;
//	List<String> searchToDateStart(Connection connection, String search, String dateMonth) throws SQLException,
//		ParseException;

//	public LinkedList<String> searchToTeacher(String search, String dateMonth) throws SQLException, ParseException;

//	ShedulesSearch addValueTableShedule(Connection connection, String search, java.util.Date dateMonth) throws SQLException
//		, IOException, ParseException;

//	public Shedules view(Connection connection) throws SQLException;

	public ShedulesSearch view(Connection connection) throws SQLException;

	ShedulesSearch getSheduleBy(Connection connection, String[] idList) throws SQLException;

}