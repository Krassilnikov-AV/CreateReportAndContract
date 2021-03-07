/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletSchedule;

import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Класс ServletSchedule
 */
@WebServlet(name = "ServletSchedule", urlPatterns = {"/servletSchedule"})
public class ServletSchedule extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos=new DataOperationsService();
		try {
			if (operationType == Operation.CREATE_SHEDULE) {
				dos.createDoc();
			}
		} catch (SQLException | ParseException e) {
		e.printStackTrace();
	}
		req.getRequestDispatcher("/shedule.jsp").forward(req, resp);
	}
}