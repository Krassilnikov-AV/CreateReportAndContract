
package servlets.servletUpload;

import model.ShedulesSearch;
import services.DataOperationsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Класс ServletOperation
 */
@WebServlet(name = "ServletOperation", urlPatterns = {"/servletOperation"})
public class ServletOperation extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		try {
			switch (operationType) {
				case DELETE:
					dos.deleteDB();
					break;


				case VIEW:
					ShedulesSearch shedules=dos.viewDataDB();
					req.setAttribute("shedules", shedules.getShedules());
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/upload.jsp").forward(req, resp);
	}
}