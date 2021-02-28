/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

import connection.PoolConnectionBuilder;
import query.SQLQueryData;

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

	private SQLQueryData sqlQueryData;

	@Override
	public void init()  {
		sqlQueryData = new SQLQueryData();
	 sqlQueryData.setConnectionBuilder(new PoolConnectionBuilder());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean delete = req.getParameter("delete") != null;

		if (delete) {
			try {
				sqlQueryData.deletedDataSQL();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/index.html").forward(req, resp);  // позволяет не выкидывать новую
	}
}