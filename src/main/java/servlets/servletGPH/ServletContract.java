

package servlets.servletGPH;

import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet
public class ServletContract extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		try {
			if (operationType == Operation.CREATE_CONTRACT) {
				dos.createDocTeacher();
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/ghp.jsp").forward(req, resp);
	}
}
