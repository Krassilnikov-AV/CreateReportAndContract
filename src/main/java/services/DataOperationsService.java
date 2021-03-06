package services;

import connection.dbConnection.*;
import createDocument.*;
import model.ShedulesSearch;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import query.SQLQueryDataImpl;

import java.io.*;
import java.sql.*;
import java.text.ParseException;

public class DataOperationsService {
	private SQLQueryDataImpl sqd = new SQLQueryDataImpl();
	private CreateScheduleReport createScheduleReport = new CreateScheduleReport();
	private ContractTeacher contractTeacher = new ContractTeacher();

	private ConnectionManager connectionManager = ConnectionManagerPostgeImpl.getInstance();
	//getJDBCConnect();
	private Connection connection = connectionManager.getConnection();

	public boolean deleteDB() throws SQLException {

		return sqd.deletedDataSQL(connection);

	}

	public boolean insertDB(InputStream fileStream) throws IOException, SQLException {

		return sqd.insertExecuteBatchQuerySQL(connection, fileStream);
	}

	public ShedulesSearch viewDataDB() throws SQLException {
		return sqd.view(connection);
	}

	public XWPFDocument createDoc(ShedulesSearch createShedules, String fio) throws SQLException, ParseException {
		return createScheduleReport.createDoc(createShedules, fio);
	}

	public void createDocTeacher(
		String strDate, String FIOpost
		, String NUMContract, String POST, String FIOTeacher, String dataStartContract
		, String dataEndContract, String academicHour, String PaymentAmount
		, String adresPlace, String contractPrice
		, String dateBirth, String placeBirth, String registrationAddress
		, String education, String detailsDiploma, String serialDiploma
		, String dateDiploma
		, String passportSerial, String passportNumber, String issuedWhomWhen
		, String numberINN, String certificateInsurance, String nameBank
		, String bikBank, String numberScore, String numberCard, String numberTel) throws SQLException, ParseException {
		contractTeacher.createDocTeacher(strDate, FIOpost
			, NUMContract, POST, FIOTeacher, dataStartContract, dataEndContract
			,academicHour, PaymentAmount, adresPlace, contractPrice,
			dateBirth, placeBirth, registrationAddress
			, education, detailsDiploma, serialDiploma
			, dateDiploma, passportSerial, passportNumber, issuedWhomWhen
			, numberINN, certificateInsurance, nameBank
			, bikBank, numberScore, numberCard, numberTel);
	}

	public ShedulesSearch searcheShedule(String searh, String dateMonth) throws SQLException, ParseException {
		return sqd.addValueTableShedule(connection, searh, dateMonth);
	}

	public ShedulesSearch getSheduleBy(String[] idList) throws SQLException {
		return sqd.getSheduleBy(connection, idList);
	}
}