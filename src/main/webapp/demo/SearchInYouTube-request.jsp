<%
    var query = request.getParameter("searchQuery");
    response.sendRedirect("https://www.youtube.com/results?search_query=" + query);
%>
