
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="presentation.*"%>
<%
	String text = request.getParameter("txt");
	String lang = request.getParameter("lg");
	String output = request.getParameter("output");
	if (lang == null) {
		lang = "EN";
	}
	if (text != null) {
		text = text.trim();
	} else
		text = "";
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thrigram</title>
<%
	if (text != "") {
%>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load('visualization', '1', {packages: ['table']});
    </script>
<script type="text/javascript">
    
    function drawVisualization() {
      // Create and populate the data table.
      var data = new google.visualization.DataTable();
      data.addColumn('number', 'ID');
      data.addColumn('string', 'Text');
     data.addColumn('number', 'Weight');
     data.addColumn('number', 'Normalized (%)');
     <%presentation.Thrigram thrigram = new presentation.Thrigram();
					thrigram.setLang(lang);
					thrigram.setText(text);
					thrigram.generate();
					String a = thrigram.getResult();
					//a += "!" + thrigram.test();
					out.println(a);%>
 
    
      // Create and draw the visualization.
      visualization = new google.visualization.Table(document.getElementById('table'));
      visualization.draw(data, null);
     
    }
    

    google.setOnLoadCallback(drawVisualization);
    </script>
<%
	}
%>
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
<script type="text/javascript">
function check(){
	if ( document.getElementById('dat').checked ) {
		document.getElementById('form').action = '/api';
	}
}
</script>
<style>
.hide {
 display: none;
 }
</style>
</head>
<body>
<h1>Trigramm</h1>
<div id="table"></div>
<form id="form" action="/thrigram.jsp" method="post"><textarea name='txt'>
<%
	if (text != null) {
			out.print(text);
		}
%>
</textarea><br />

<input type="radio" name="lg" value="EN"
	<%if (lang.equals("EN")) {
					out.print("checked='checked'");
				}%> />EN<br />
<input type="radio" name="lg" value="RU"
	<%if (lang.equals("RU")) {
					out.print("checked='checked'");
				}%> />RU<br />
Output: <br />
<input type="radio" name="output" value="1" checked='checked' />Table<br />
<input id="dat" type="radio" name="output" value="0" />.dat<br />

<input class="hide" type="text" name="type" value="presentation" />
<input class="hide" type="text" name="subtype" value="thrigram" />
<button type='submit' onclick="check();">Send</button>

    
</form>
</body>
</html>