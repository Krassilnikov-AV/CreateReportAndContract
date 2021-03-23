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
		String academicHour=req.getParameter("academicHour");

		String PaymentAmount=req.getParameter("payment");
			String adresPlace=req.getParameter("workAddress");
		String contractPrice=req.getParameter("contractPrice");
//		String contractPeriod=req.getParameter("contractPeriod");
		// раздел 10
		String dateBirth=req.getParameter("dateBirth");
		String placeBirth=req.getParameter("placeBirth");
		String registrationAddress=req.getParameter("registrationAddress");
		String education=req.getParameter("education");
		String detailsDiploma=req.getParameter("detailsDiploma");
		String serialDiploma=req.getParameter("serialDiploma");
		String dateDiploma=req.getParameter("dateDiploma");

		String passportSerial=req.getParameter("passportSerial");
		String passportNumber=req.getParameter("passportNumber");
		String issuedWhomWhen=req.getParameter("issuedWhomWhen");
		String numberINN=req.getParameter("numberINN");
		String certificateInsurance=req.getParameter("certificateInsurance");
		String nameBank=req.getParameter("nameBank");
		String bikBank=req.getParameter("bikBank");
		String numberScore=req.getParameter("numberScore");
		String numberCard=req.getParameter("numberCard");
		String numberTel=req.getParameter("numberTel");

//		try {
		if (operationType == Operation.CREATE_CONTRACT) {
			try {
				dos.createDocTeacher(strDate, FIOpost
					, NUMContract, POST, FIOTeacher, dataStartContract, dataEndContract
					, academicHour, PaymentAmount, adresPlace, contractPrice
					, dateBirth, placeBirth, registrationAddress
					, education, detailsDiploma, serialDiploma
					, dateDiploma, passportSerial, passportNumber, issuedWhomWhen
					, numberINN, certificateInsurance, nameBank
					, bikBank, numberScore, numberCard, numberTel);
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
