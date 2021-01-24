/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.filter;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

/**
 * Класс AuthFilter
 */
//@WebServlet()
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest request,
						 final ServletResponse response,
						 final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		final String login = req.getParameter("login");
		final String password = req.getParameter("password");

		@SuppressWarnings("unchecked")   // не проверять приведение (AtomicReference<UserDAO>)
		final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");
		final HttpSession session = req.getSession();

		if (nonNull(session) &&
			nonNull(session.getAttribute("login")) &&
			nonNull(session.getAttribute("password"))) {
			final User.ROLE role = (User.ROLE) session.getAttribute("role");
			moveToMenu(req, res, role);

		} else if (dao.get().userIsExist(login, password)) {
			final User.ROLE role = dao.get().getRoleByLoginPassword(login, password);

			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("role", role);

			moveToMenu(req, res, role);
		} else {
			moveToMenu(req, res, User.ROLE.UNKNOWN);
		}
	}

	private void moveToMenu(final HttpServletRequest req,
							final HttpServletResponse res,
							final User.ROLE role) throws ServletException, IOException {
		if (role.equals(User.ROLE.ADMIN)) {
			req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);
		} else if (role.equals(User.ROLE.USER)) {
			req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);
		} else {
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
		}
	}

	@Override
	public void destroy() {

	}
}