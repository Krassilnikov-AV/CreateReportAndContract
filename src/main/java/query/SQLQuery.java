package query;

import model.Shedules;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;

/**
 * Класс SQLQuery
 */
public interface SQLQuery {

	public void insertExecuteBatchQuerySQL() throws IOException, SQLException;
	public LinkedList<String> searchToCodegroup(String search, String dateMonth) throws SQLException, ParseException;
	public LinkedList<String> searchToProgram(String search, String dateMonth) throws SQLException, ParseException;
	public List<String> searchToDateStart(String search, String dateMonth) throws SQLException, ParseException;
	public List<String> searchToTimeStart(String search, String dateMonth) throws SQLException, ParseException;
	public List<String> searchToDateEnd(String search, String dateMonth) throws SQLException, ParseException;
	public List<String> searchToTimeEnd(String search, String dateMonth) throws SQLException, ParseException;

	public LinkedList<String> searchToAuditorium(String search, String dateMonth) throws SQLException, ParseException;
	public LinkedList<String> searchToTeacher(String search, String dateMonth) throws SQLException, ParseException;

	public Shedules view(Connection connection) throws SQLException;
	public int deletedDataSQLloc() throws SQLException;
}