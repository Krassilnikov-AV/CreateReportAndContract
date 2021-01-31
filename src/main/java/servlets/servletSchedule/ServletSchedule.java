/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletSchedule;

import createDocument.SсheduleClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Класс ServletSchedule
 */
@WebServlet
public class ServletSchedule extends HttpServlet {

	SсheduleClass sc = new SсheduleClass();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sc.ceateDoc();
	}
}