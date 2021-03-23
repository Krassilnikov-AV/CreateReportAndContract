/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletUpload;

import services.DataOperationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

@MultipartConfig
@WebServlet(name = "ServletUploadDataDB", urlPatterns = {"/servletUploadDataDB"})
public class ServletUploadDataDB extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

	Part part = request.getPart("file");

	download(part.getInputStream());

		request.getRequestDispatcher("/gph.jsp").forward(request, response);
}

	private void download(InputStream fileStream) {

		DataOperationsService dos = new DataOperationsService();
		try {
			dos.insertTeacherDB(fileStream);
			dos.insertGroupDB(fileStream);
			dos.insertProgramDB(fileStream);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}