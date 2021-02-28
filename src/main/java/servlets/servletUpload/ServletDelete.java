/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

import query.SQLQueryData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Класс ServletDelete
 */
@WebServlet(name = "ServletDelete", urlPatterns = {"/servletDelete"})
public class ServletDelete extends HttpServlet {

	Properties properties = null;
	Connection connection = null;
	private SQLQueryData sqd=null;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	@Override
	public void init() {
				sqd = new SQLQueryData();
				properties = new Properties();
				try {
					properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/usersKey" +
						".properties")));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private String getDbURL() {
		dbUrl = properties.getProperty("db.url");
		return dbUrl;
	}
	private String getDbUsername() {
		dbUsername = properties.getProperty("db.username");
		return dbUsername;
	}

	private String getDbPassword() {
		dbPassword = properties.getProperty("db.password");
		return dbPassword;
	}
	private void getJDBCConnect() {
		try {
			String driverClassName = properties.getProperty("db.driverClassName");
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(getDbURL(), getDbUsername(), getDbPassword());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean insert = req.getParameter("insert") != null;
		boolean delete = req.getParameter("delete") != null;
		getJDBCConnect();

		if (insert) {
			try {
				sqd.insertExecuteBatchQuerySQL(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (delete) {
			try {
				sqd.deletedDataSQL(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}
}