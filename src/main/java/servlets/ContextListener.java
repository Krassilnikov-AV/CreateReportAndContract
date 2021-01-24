/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Класс ContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private AtomicReference<UserDAO> dao;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		dao = new AtomicReference<>(new UserDAO());

		dao.get().add(new User(1, "Pavel", "1", User.ROLE.ADMIN));
		dao.get().add(new User(2, "Egor", "1", User.ROLE.USER));

		final ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("dao", dao);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		dao = null;
	}
}