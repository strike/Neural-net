<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.*"%>

<%
	String text = request.getParameter("txt");
	String lang = request.getParameter("lg");
   	if (lang == null){
		lang = "EN";
	}
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ART</title>


<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-20519924-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</head>
  
<body>
  	<h1>ART</h1>
  	<div id="table"></div>
  	<pre><%
	if (text != null) {
		String[] textM = text.split("\n");		

ART art = new ART(textM[0],lang); 
out.print(art.test()+"<br />"); 
out.print(art.run());
out.print("!" + art.debug());
out.print("<br />");
for (int i = 1; i < textM.length; i++){
	art.reSetText(textM[i], lang);
	 out.print(art.run());
	 out.print("!" + art.debug());
	 out.print("<br />");
}


 }
 %></pre>
 
   <form  id="form" action="/art.jsp" method="post">
    	<textarea name='txt' ><%if (text != null) {out.print(text);	}%></textarea><br />
 
    	
    	<input type="radio" name="lg" value="EN" <% if (lang.equals("EN")) { out.print("checked='checked'"); } %> />EN<br />
		<input type="radio" name="lg" value="RU" <% if (lang.equals("RU")) { out.print("checked='checked'"); } %> />RU<br />

<button type='submit' onclick="check();">Send</button>
	</form>
</body>
</html>