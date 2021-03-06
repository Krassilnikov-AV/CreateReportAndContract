package servlets.servletUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

/**
 * Класс ServletUpload
 */
@MultipartConfig
@WebServlet(name = "ServletUpload", urlPatterns = {"/servletUpload"})
public class ServletUpload extends HttpServlet {

	String path = null;
	String name = "";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");       // распознавание русского текста
		path = request.getParameter("path");
		Part part = request.getPart("file");
		name = part.getSubmittedFileName();          // получить в классе чтения, создать в свойствах->читать и
		// получать в необходимом классе для чтения
		download(part.getInputStream(), name);
		request.getRequestDispatcher("/uploadFileDb.jsp").forward(request, response);
//		insertDeleteView(request, response);
	}

//	private void insertDeleteView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Operation operationType = Operation.fromString(req.getParameter("operation"));
//		DataOperationsService dos = new DataOperationsService();
//		try {
//			switch (operationType) {
//				case DELETE:
//					dos.deleteDB();
//					break;
//				case INSERT:
//					dos.insertDB();
//					break;
//				case VIEW:
//					Shedules shedules = dos.viewDataDB();
//					req.setAttribute("shedules", shedules.getShedules());
//					break;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		req.getRequestDispatcher("/uploadFileDb.jsp").forward(req, resp);
//	}

	private void download(InputStream fileStream, String name) {
		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
			Files.newOutputStream(Paths.get(path + File.separator + name))
		)) {
			int read;
			byte[] readByte = new byte[1024];
			while ((read = fileStream.read(readByte)) != -1) {
				bufferedOutputStream.write(readByte, 0, read);
			}

			bufferedOutputStream.flush();     // загрузка на диск
		} catch (IOException e) {
			System.out.println("the file is corrupted!!!");
			e.printStackTrace();
		}
	}
}