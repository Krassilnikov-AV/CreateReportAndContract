package createDocument;

import model.ShedulesSearch;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.sql.*;
import java.text.ParseException;

public interface CreateDocument {

	default XWPFDocument createDoc(ShedulesSearch createShedules, String fio) throws SQLException, ParseException {
		return new XWPFDocument();
	}

	default public void createDocTeacher(
		String strDate, String FIOpost
		, String NUMContract, String POST, String FIOTeacher
		, String dataStartContract, String dataEndContract, String academicHour, String PaymentAmount
		, String adresPlace, String contractPrice
		, String dateBirth, String placeBirth, String registrationAddress
		, String education, String detailsDiploma, String serialDiploma
		, String dateDiploma, String passportSerial, String passportNumber, String issuedWhomWhen
		, String numberINN,	String certificateInsurance, String nameBank
		, String bikBank, String numberScore, String numberCard,  String numberTel) throws SQLException, ParseException {}
}