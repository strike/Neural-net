package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.http.*;
import presentation.*;

public class api extends HttpServlet {

	private static final long serialVersionUID = -5758497479639743060L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {		
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		String txt = "";

	
		
		if (req.getParameter("type").equals("presentation")){
			if (req.getParameter("type2").equals("thrigram")){
				Thrigram thrigram = new presentation.Thrigram();
				thrigram.setLang(req.getParameter("lg"));
				//TODO Make possible use file
				try {
				    BufferedReader in = new BufferedReader(new FileReader("/home/strike/eclipse/thrigram/war/test"));
				    String str;
				    while ((str = in.readLine()) != null) {
				        txt += str;
				    }
				    in.close();
				} catch (IOException e) {
				} 
				thrigram.setText(req.getParameter("txt"));
				//thrigram.setText(txt);
				thrigram.setOutput(1);
				resp.getWriter().println(thrigram.getResult());
				
			} else {
				if (req.getParameter("type2").equals("term")){
					Term thrigram = new presentation.Term();
					thrigram.setLang(req.getParameter("lg"));
					//thrigram.setText(req.getParameter("txt"));
					try {
					    BufferedReader in = new BufferedReader(new FileReader("/home/strike/eclipse/thrigram/war/test"));
					    String str;
					    while ((str = in.readLine()) != null) {
					        txt += str + "\n";
					    }
					    in.close();
					} catch (IOException e) {
					} 		
					thrigram.setText(txt);
					thrigram.setOutput(1);
					resp.getWriter().println(thrigram.getResult());
				}
				
			}
		}
}
}