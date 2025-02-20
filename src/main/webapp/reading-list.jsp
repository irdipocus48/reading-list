<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>ReadList management application</title>
</head>
<body>
<center>
    <h1>Reading list</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/new">Add new</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/list">List all</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Mark</th>
            <th>Comment</th>
            <th>Actions</th>


        </tr>
        <%--        @elvariable id="bookList" type="java.util.List"--%>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.genre}"/></td>
                <td><c:out value="${book.mark}"/></td>
                <td><c:out value="${book.comment}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/edit?id=<c:out value='${book.id}' />">Edit</a>
                    <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${book.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>