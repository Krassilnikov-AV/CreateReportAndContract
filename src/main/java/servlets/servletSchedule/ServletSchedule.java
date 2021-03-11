/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletSchedule;

import model.ShedulesSearch;
import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "ServletSchedule", urlPatterns = {"/servletSchedule"})
public class ServletSchedule extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
//		ConnectionManager connectionManager = ConnectionManagerPostgeImpl.getInstance();
		//getJDBCConnect();
//		SQLQueryDataImpl sqd = new SQLQueryDataImpl();
//		Connection connection = connectionManager.getConnection();
		String dateMonth = req.getParameter("calendar");
		String searh = req.getParameter("pathToSave");

		try {
			switch (operationType) {
				case CREATE_SHEDULE:
					dos.createDoc();
					break;
				case SELECT_SHEDULE:
					ShedulesSearch shedules = dos.searcheShedule(searh, dateMonth);

					req.setAttribute("shedules", shedules.getShedules());
					break;
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/shedule.jsp").forward(req, resp);
	}
}