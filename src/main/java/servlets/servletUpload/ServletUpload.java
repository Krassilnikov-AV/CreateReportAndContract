package servlets.servletUpload;

import services.DataOperationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;


@MultipartConfig
@WebServlet(name = "ServletUpload", urlPatterns = {"/servletUpload"})
public class ServletUpload extends HttpServlet {

//	private String path = null;
//	private String name = "";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Part part = request.getPart("file");

			download(part.getInputStream());

		request.getRequestDispatcher("/upload.jsp").forward(request, response);
	}

	private void download(InputStream fileStream) {

		DataOperationsService dos = new DataOperationsService();
		try {
			dos.insertDB(fileStream);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}