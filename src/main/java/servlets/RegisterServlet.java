/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

	private Connection connection;

//	@Override
//	public void init() {
//		Properties properties = new Properties();
//		try {
//			properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/usersKey" +
//				".properties")));
//			String dbUrl = properties.getProperty("db.url");
//			String dbUsername = properties.getProperty("db.username");
//			String dbPassword = properties.getProperty("db.password");
//			String driverClassName = properties.getProperty("db.driverClassName");
//
//			Class.forName(driverClassName);
//
//			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//		} catch (IOException e) {
//			throw new IllegalStateException(e);
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		String firstname = request.getParameter("first-name");
//		String lastname = request.getParameter("last-name");
//		String errorMsg = null;
//		if (email == null || email.equals("")) {
//			errorMsg = "поле логина не должно быть пустым.";
//		}
//		if (password == null || password.equals("")) {
//			errorMsg = "поле пароля не должно быть пустым.";
//		}
//		if (firstname == null || firstname.equals("")) {
//			errorMsg = "поле имени не должно быть пустым.";
//		}
//		if (lastname == null || lastname.equals("")) {
//			errorMsg = "поле фамилии не должно быть пустым.";
//		}
//
//		if (errorMsg != null) {
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
//			PrintWriter out = response.getWriter();
//			out.println("<font color=red>" + errorMsg + "</font>");
//			rd.include(request, response);
//		} else {
//			try (PreparedStatement ps =
//					 connection.prepareStatement("insert into userskey(first_name, last_name, email_, password_) " +
//						 "values (?,?,?,?)")) {
//				ps.setString(1, firstname);
//				ps.setString(2, lastname);
//				ps.setString(3, email);
//				ps.setString(4, password);
//
//				ps.execute();
//				request.getRequestDispatcher("/register.html").forward(request, response);
//				/*
//				* после успешной регистрации,
//				* можно на этой странице остаться или перейти на общую, т.е. выйти
//				* */
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new ServletException("DB Connection problem.");
//			}
//		}
//	}
}