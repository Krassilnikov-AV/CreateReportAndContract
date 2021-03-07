package createDocument;

import java.sql.*;
import java.text.ParseException;

/**
 * Класс CreateDocument
 */
public interface CreateDocument {

	void createDoc(Connection connection) throws SQLException, ParseException;

}