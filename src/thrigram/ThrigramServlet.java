package thrigram;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ThrigramServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		char[] s = req.getParameter("s").toCharArray();
		resp.getWriter().println(req.getParameter("s") + "<br />");
		int len = s.length;
		int N = 3;
		int M = 26;
		int k;
		String[] thr;
		thr = new String[18279];
		
		double[] V;
		V = new double[18279];
		
		//resp.getWriter().println("!" + V[7] + "!");
 
		for (int i = 0; i <= len-N; i++){
				k = M*M*s2c(s[i]) + M*s2c(s[i+1]) + s2c(s[i+2]);  
				thr[k] = String.valueOf(s[i]) + 
					String.valueOf(s[i+1]) + 
					String.valueOf(s[i+2]);
				V[k] = V[k] + SpaceCur(s[i],s[i+1],s[i+2]); 
				resp.getWriter().println(k + " " + thr[k] + " = " + V[k] +"<br>");
		}
		
	}
	
	private double SpaceCur(char a, char b, char c){
		int i = 0;
		if ( a == ' ' ){
			i++;
		}
		if ( b == ' '){
			i++;
		}
		if ( c == ' '){
			i++;
		}
		if ( i == 0 ){
			return 1;
		} else {
			if ( i == 1 ){
				return 0.5;
			} else {
				return 0.25;
			}
		}
	}

	private int s2c(char c) {
		switch(c){
		case 'a' : return 1;
		case 'b' : return 2;
		case 'c' : return 3;
		case 'd' : return 4;
		case 'e' : return 5;
		case 'f' : return 6;
		case 'g' : return 7;
		case 'h' : return 8;
		case 'i' : return 9;
		case 'j' : return 10;
		case 'k' : return 11;
		case 'l' : return 12;
		case 'm' : return 13;
		case 'n' : return 14;
		case 'o' : return 15;
		case 'p' : return 16;
		case 'q' : return 17;
		case 'r' : return 18;
		case 's' : return 19;
		case 't' : return 20;
		case 'u' : return 21;
		case 'v' : return 22;
		case 'w' : return 23;
		case 'x' : return 24;
		case 'y' : return 25;
		case 'z' : return 26;
		}
		return 0;
	}
}
