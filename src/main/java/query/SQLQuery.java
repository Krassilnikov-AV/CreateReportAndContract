package query;

import model.*;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;

/**
 * Класс SQLQuery
 */
public interface SQLQuery {

	public ShedulesSearch addValueTableShedule(Connection connection, String search, String dateMonth) throws SQLException, IOException,
		ParseException;

	public boolean deletedDataSQL(Connection connection) throws SQLException;

	boolean insertExecuteBatchQuerySQL(Connection connection) throws IOException, SQLException;

//	boolean searchToTeacher(String teacher, String dateSearch, String timeSearch) throws SQLException,		ParseException;

//	public List<String> searchToDateStart(String search, String dateMonth) throws SQLException, ParseException;

	List<String> searchToDateStart(Connection connection, String search, String dateMonth) throws SQLException,
		ParseException;

//	public LinkedList<String> searchToTeacher(String search, String dateMonth) throws SQLException, ParseException;

	public Shedules view(Connection connection) throws SQLException;


	//	public LinkedList<String> searchToCodegroup(String search, String dateMonth) throws SQLException, ParseException;
//	public LinkedList<String> searchToProgram(String search, String dateMonth) throws SQLException, ParseException;

	//	public List<String> searchToTimeStart(String search, String dateMonth) throws SQLException, ParseException;
//	public List<String> searchToDateEnd(String search, String dateMonth) throws SQLException, ParseException;
//	public List<String> searchToTimeEnd(String search, String dateMonth) throws SQLException, ParseException;
	//	public LinkedList<String> searchToAuditorium(String search, String dateMonth) throws SQLException, ParseException;
//

}