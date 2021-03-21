package createDocument;

import model.ShedulesSearch;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.sql.*;
import java.text.ParseException;

/**
 * Класс CreateDocument
 */
public interface CreateDocument {

//	XWPFDocument createDoc(ShedulesSearch connection) throws SQLException, ParseException;

	default XWPFDocument createDocTeacher() throws SQLException, ParseException {
		return null;
	}

	default XWPFDocument createDoc(ShedulesSearch createShedules, String fio) throws SQLException, ParseException {
		return null;
	}

//	void createDocTeacher() throws SQLException, ParseException;
}