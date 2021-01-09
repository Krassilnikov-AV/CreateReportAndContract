package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

/**
 * Класс Servlet
 */
@MultipartConfig
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
	private static final String SERVER_PATH = "D:\\REPOSITORIES-2";

//	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//		throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//
//	}

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processRequest(request, response);
//	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("file");
		download(part.getInputStream(), part.getSubmittedFileName());
		request.getRequestDispatcher("/index.html").forward(request, response);  // позволяет н выкидывать новую страницу
	}

	private void download(InputStream fileStream, String name) {
		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
			Files.newOutputStream(Paths.get(SERVER_PATH + File.separator + name))
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