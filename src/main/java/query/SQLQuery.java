package query;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * Класс SQLQuery
 */
public interface SQLQuery {

	public void insertExecuteBatchQuerySQL() throws IOException, SQLException;
	public LinkedList<String> searchToProgram(String search) throws SQLException;
	public LinkedList<String> searchToCodegroup(String search) throws SQLException;
	public LinkedList<String> searchToAuditorium(String search) throws SQLException;
	public List<String> searchToDateStart(String search) throws SQLException, ParseException;
	public LinkedList<String> searchToTeacher(String search) throws SQLException;
	public void view();
	public int deletedDataSQLloc() throws SQLException;
}