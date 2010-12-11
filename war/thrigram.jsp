<%@ page import="thrigram.*" %>


<% String text = request.getParameter("txt");
  %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Trigramma</title>
   
   <% if (text != null){ %>
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
     <%  thrigram.Thrigram thrigram = new thrigram.Thrigram();
     
    thrigram.setText(text);
    String a = thrigram.getResult();
    out.println(a);
     %>
      // Create and draw the visualization.
      visualization = new google.visualization.Table(document.getElementById('table'));
      visualization.draw(data, null);
    }
    

    google.setOnLoadCallback(drawVisualization);
    </script>
    <% } %>
  </head>
  <body style="font-family: Arial;border: 0 none;">
  	<h1>Trigramma</h1>
    <div id="table"></div>
    <form action="/thrigram.jsp" method="post">
    	<textarea name='txt' /><% if (text != null){ out.println(text); } %></textarea><br />
    	<button type='submit'>Send</button>
    </form>
  </body>
</html>