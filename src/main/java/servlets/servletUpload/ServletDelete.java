/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

import model.Shedules;
import services.DataOperationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Класс ServletDelete
 */
@WebServlet(name = "ServletDelete", urlPatterns = {"/servletDelete"})
public class ServletDelete extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		try {
			switch (operationType) {
				case DELETE:
					dos.deleteDB();
					break;
				case INSERT:
					dos.insertDB();
					break;
				case VIEW:
					Shedules shedules = dos.viewDataDB();
					req.setAttribute("shedules", shedules.getShedules());
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}
}