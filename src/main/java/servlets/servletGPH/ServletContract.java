

package servlets.servletGPH;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "ServletContract", urlPatterns = {"/servletContract"})
public class ServletContract extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		try {
			if (operationType == Operation.CREATE_CONTRACT) {
				dos.createDocTeacher();
				XWPFDocument file = dos.createDocTeacher();

				resp.setContentType("application/msword");
				BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());

				file.write(bos);
				bos.flush();
				bos.close();
				req.getRequestDispatcher("/ghp.jsp").forward(req, resp);
				/*должен создаваться документ при нажатии на кнопку "создать"
				* с выбором пути сохранения на диске пользователя*/
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}
