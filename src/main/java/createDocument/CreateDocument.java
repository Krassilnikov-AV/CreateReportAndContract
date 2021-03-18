package createDocument;

import model.ShedulesSearch;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.sql.*;
import java.text.ParseException;

/**
 * Класс CreateDocument
 */
public interface CreateDocument {

	XWPFDocument createDoc(ShedulesSearch connection) throws SQLException, ParseException;

	default void createDocTeacher() throws SQLException, ParseException {

	}

//	void createDocTeacher() throws SQLException, ParseException;
}