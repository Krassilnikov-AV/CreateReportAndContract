/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

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
		boolean insert = req.getParameter("insert") != null;
		boolean delete = req.getParameter("delete") != null;
		DataOperationsService dos = new DataOperationsService();

		if (insert) {
			try {
				dos.insertDB();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (delete) {
			try {
				dos.deleteDB();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}
}