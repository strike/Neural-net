package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.http.*;
import presentation.*;

public class api extends HttpServlet {

	private static final long serialVersionUID = -5758497479639743060L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.getWriter().println("<html><body>API help is not avalible now</body></html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {		
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		String txt = "";

	
		
		if (req.getParameter("type").equals("presentation")){
			if (req.getParameter("subtype").equals("thrigram")){
				Thrigram thrigram = new presentation.Thrigram();
				thrigram.setLang(req.getParameter("lg"));				
				/*try {
				    BufferedReader in = new BufferedReader(new FileReader("/home/strike/eclipse/thrigram/war/test"));
				    String str;
				    while ((str = in.readLine()) != null) {
				        txt += str;
				    }
				    in.close();
				} catch (IOException e) {
				} */
				thrigram.setText(req.getParameter("txt"));
				//thrigram.setText(txt);
				thrigram.setOutput(1);
				thrigram.generate();
				//resp.getWriter().println(thrigram.debug());
				resp.getWriter().println(thrigram.getResult());
				
			} else {
				if (req.getParameter("subtype").equals("term")){
					Term Term = new presentation.Term();
					Term.setLang(req.getParameter("lg"));
					if (Term.setText(req.getParameter("txt"))){
						Term.setOutput(1);						
						resp.getWriter().println(Term.getResult());
					}
					//resp.getWriter().println(Term.debug());
				}
				
			}
		}
}
}