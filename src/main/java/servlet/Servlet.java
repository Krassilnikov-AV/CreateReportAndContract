package servlet;

import connection.ConnectionApp;
import query.SQLQueryDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;

/**
 * Класс Servlet
 */
@MultipartConfig
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
//	private static final String SERVER_PATH = "D:\\REPOSITORIES-2";   // при отсутствии выбора пути сервера

	String path = null;
	String name = "";
	ConnectionApp conApp = new ConnectionApp();
	SQLQueryDate sqlQuery = new SQLQueryDate();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");       // распознавание русского текста
		path = request.getParameter("path");
		Part part = request.getPart("file");
		name = part.getSubmittedFileName();          // получить в классе чтения, создать в свойствах->читать и
		// получать в необходимом классе для чтения
		download(part.getInputStream(), name);
		doGet(request, response);
		request.getRequestDispatcher("/index.html").forward(request, response);  // позволяет не выкидывать новую
		// страницу
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean delete = req.getParameter("delete") !=null;

		if(delete) {
//			conApp.getNameURL();
			try {
				sqlQuery.deletedDataSQL();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/index.html").forward(req, resp);  // позволяет не выкидывать новую
		// страницу
	}

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