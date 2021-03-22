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
		, String NUMContract, String POST, String FIOTeacher
		, String dataStartContract, String dataEndContract, String PaymentAmount
		, String adresPlace, String contractPrice, String contractPeriod
		, String dateBirth, String placeBirth, String registrationAddress
		, String education, String detailsDiploma, String serialDiploma
		, String dateDiploma) throws SQLException, ParseException {
		contractTeacher.createDocTeacher(strDate, FIOpost
			, NUMContract, POST, FIOTeacher, dataStartContract,
			dataEndContract, PaymentAmount, adresPlace, contractPrice,
			contractPeriod,
			dateBirth, placeBirth, registrationAddress
			, education, detailsDiploma, serialDiploma
			, dateDiploma);
	}

	public ShedulesSearch searcheShedule(String searh, String dateMonth) throws SQLException, ParseException {
		return sqd.addValueTableShedule(connection, searh, dateMonth);
	}

	public ShedulesSearch getSheduleBy(String[] idList) throws SQLException {
		return sqd.getSheduleBy(connection, idList);
	}
}