/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Класс LogoutServlet
 */
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req,
						 HttpServletResponse resp) throws ServletException, IOException {
		final HttpSession session = req.getSession();

		session.removeAttribute("password");
		session.removeAttribute("login");
		session.removeAttribute("role");

		resp.sendRedirect("/");
	}
}