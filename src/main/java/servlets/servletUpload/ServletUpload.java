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
@WebServlet("/servletUpload")
public class ServletUpload extends HttpServlet {

	String path = null;
	String name = "";
//	Connection connection = null;

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");       // распознавание русского текста
		path = request.getParameter("path");
		Part part = request.getPart("file");
		name = part.getSubmittedFileName();          // получить в классе чтения, создать в свойствах->читать и
		// получать в необходимом классе для чтения
		download(part.getInputStream(), name);
		request.getRequestDispatcher("/index.html").forward(request, response);
//		SQLQueryData sqd = new SQLQueryData();
//		boolean deleteDB = request.getParameter("deleteDB") !=null;
//		if(deleteDB)
//		try {
//			sqd.deletedDataSQL(connection);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

//	private void deleteDate() throws SQLException {
//
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