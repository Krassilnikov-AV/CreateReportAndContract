package servlets.servletGPH;

import services.DataOperationsService;
import servlets.servletUpload.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "ServletContract", urlPatterns = {"/servletContract"})
public class ServletContract extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Operation operationType = Operation.fromString(req.getParameter("operation"));
		DataOperationsService dos = new DataOperationsService();
		String strDate = req.getParameter("dateContract");
		String FIOpost = req.getParameter("fioPost");
		String POST = req.getParameter("post");
		String NUMContract = req.getParameter("numberContract");
		String FIOTeacher=req.getParameter("fioTeacher");
		String dataStartContract =req.getParameter("dateStartContract");
		String dataEndContract=req.getParameter("dateFinishContract");
		String PaymentAmount=req.getParameter("payment");
			String adresPlace=req.getParameter("workAddress");
		String contractPrice=req.getParameter("contractPrice");
		String contractPeriod=req.getParameter("contractPeriod");
		// раздел 10
		String dateBirth=req.getParameter("dateBirth");
		String placeBirth=req.getParameter("placeBirth");
		String registrationAddress=req.getParameter("registrationAddress");
		String education=req.getParameter("education");
		String detailsDiploma=req.getParameter("detailsDiploma");
		String serialDiploma=req.getParameter("serialDiploma");
		String dateDiploma=req.getParameter("dateDiploma");

//		String passport=req.getParameter("passport");
//		String numberINN=req.getParameter("numberINN");
//		String certificateInsurance=req.getParameter("certificateInsurance");
//		String serviceСost=req.getParameter("serviceСost");
//		String PlaceServiceProvision=req.getParameter("PlaceServiceProvision");
//		String TotalContractPrice=req.getParameter("TotalContractPrice");
//		String TotalCostServices=req.getParameter("TotalCostServices");
//		String PlaceServiceProvision=req.getParameter("PlaceServiceProvision");
//		String PlaceServiceProvision=req.getParameter("PlaceServiceProvision");
//		String PlaceServiceProvision=req.getParameter("PlaceServiceProvision");
//		String PlaceServiceProvision=req.getParameter("PlaceServiceProvision");
//		try {
		if (operationType == Operation.CREATE_CONTRACT) {
			try {
				dos.createDocTeacher(strDate, FIOpost
					, NUMContract, POST, FIOTeacher, dataStartContract
					, dataEndContract, PaymentAmount, adresPlace, contractPrice
					, contractPeriod, dateBirth, placeBirth, registrationAddress
					, education, detailsDiploma, serialDiploma
					, dateDiploma);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
//				XWPFDocument file = dos.createDocTeacher();
//
//				resp.setContentType("application/msword");
//				BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
//
//				file.write(bos);
//				bos.flush();
//				bos.close();
			req.getRequestDispatcher("/gph.jsp").forward(req, resp);
		}
//		} catch (SQLException | ParseException e) {
//			e.printStackTrace();
//		}
	}
}
