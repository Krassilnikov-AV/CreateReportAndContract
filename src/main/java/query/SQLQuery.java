package query;

import model.ShedulesSearch;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

/**
 * Класс SQLQuery
 */
public interface SQLQuery {

	public ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException, IOException,
		ParseException;

	public boolean deletedDataSQL(Connection connection) throws SQLException;


//	public boolean insertDataContractTeacherSQL() throws IOException, SQLException;
//	boolean searchToTeacher(String teacher, String dateSearch, String timeSearch) throws SQLException,		ParseException;

//	public List<String> searchToDateStart(String search, String dateMonth) throws SQLException, ParseException;

	boolean insertExecuteBatchQuerySQL(Connection connection, InputStream fileStream) throws IOException,
		SQLException;

	List<String> searchToDateStart(Connection connection, String search, String dateMonth) throws SQLException,
		ParseException;

//	public LinkedList<String> searchToTeacher(String search, String dateMonth) throws SQLException, ParseException;

//	ShedulesSearch addValueTableShedule(Connection connection, String search, java.util.Date dateMonth) throws SQLException
//		, IOException, ParseException;

//	public Shedules view(Connection connection) throws SQLException;

	public ShedulesSearch view(Connection connection) throws SQLException;

	ShedulesSearch getSheduleBy(Connection connection, String[] idList) throws SQLException;

	//	public LinkedList<String> searchToCodegroup(String search, String dateMonth) throws SQLException, ParseException;
//	public LinkedList<String> searchToProgram(String search, String dateMonth) throws SQLException, ParseException;

	//	public List<String> searchToTimeStart(String search, String dateMonth) throws SQLException, ParseException;
//	public List<String> searchToDateEnd(String search, String dateMonth) throws SQLException, ParseException;
//	public List<String> searchToTimeEnd(String search, String dateMonth) throws SQLException, ParseException;
	//	public LinkedList<String> searchToAuditorium(String search, String dateMonth) throws SQLException, ParseException;
//

}