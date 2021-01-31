/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletGPH;

import createDocument.ContractTeacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Класс ServletContract
 */
@WebServlet
public class ServletContract extends HttpServlet {
	ContractTeacher ct = new ContractTeacher();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ct.ceateDoc();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}