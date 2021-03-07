package createDocument;

import java.sql.*;
import java.text.ParseException;

/**
 * Класс CreateDocument
 */
public interface CreateDocument {

	default void createDoc(Connection connection) throws SQLException, ParseException {
	}

	default void createDocTeacher() throws SQLException, ParseException {

	}

//	void createDocTeacher() throws SQLException, ParseException;
}