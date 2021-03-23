package servlets.servletSchedule;

import model.ShedulesSearch;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "ServletSchedule", urlPatterns = {"/servletSchedule"})
public class ServletSchedule extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		String fio = req.getParameter("fioPost");
		String dateMonth = req.getParameter("calendar");
		String searh = req.getParameter("wordName");

		try {
			switch (operationType) {
				case SELECT_SHEDULE:
					ShedulesSearch shedules = dos.searcheShedule(searh, dateMonth);
					req.setAttribute("shedules", shedules.getShedules());

					req.getRequestDispatcher("/shedule.jsp").forward(req, resp);
					break;

				case CREATE_SHEDULE:
					String idList[] = req.getParameterValues("data_shedule");

					ShedulesSearch createShedules = dos.getSheduleBy(idList);
					XWPFDocument file = dos.createDoc(createShedules, fio);

					resp.setContentType("application/msword");
					BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
					file.write(bos);
					bos.flush();
					bos.close();
					break;
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}