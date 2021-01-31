/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

import connection.PoolConnectionBuilder;
import query.SQLQueryDate;

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
//private static final Logger logger = LoggerFactory.getLogger(ServletDelete.class);
	private SQLQueryDate sqlQueryDate;

	@Override
	public void init()  {
		sqlQueryDate = new SQLQueryDate();
	 sqlQueryDate.setConnectionBuilder(new PoolConnectionBuilder());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean delete = req.getParameter("delete") != null;

		if (delete) {
			try {
				sqlQueryDate.deletedDataSQL();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/index.html").forward(req, resp);  // позволяет не выкидывать новую
	}
}