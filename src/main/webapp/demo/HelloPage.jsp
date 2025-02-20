<html>
<head>
    <title>Hello user page</title>
</head>
<body>
<h2> Hello user!</h2>

<%
    out.println("Hello " + request.getParameter("userName") + "!");
    out.println("Welcome to Servlet/JSP programming!");
%>

</body>
</html>