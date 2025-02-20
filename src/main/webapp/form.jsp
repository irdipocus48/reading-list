<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Reading list web application</title>
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
    <c:if test="${book != null}">
    <form name="myForm" action="${pageContext.request.contextPath}/update" method="post"> </c:if>
        <c:if test="${book == null}">
        <form name="myForm" action="${pageContext.request.contextPath}/insert" method="post"> </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${book != null}">
                            Edit
                        </c:if>
                        <c:if test="${book == null}">
                            Add new
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${book != null}">
                    <input type="text" name="id" value="<c:out value='${book.id}' />"/>
                </c:if>
                <tr>
                    <th>Title:</th>
                    <td>
                        <input type="text" name="title" size="45" value="<c:out value='${book.title}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Author:</th>
                    <td>
                        <input type="text" name="author" size="45" value="<c:out value='${book.author}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Genre:</th>
                    <td>
                        <select name="genre" value="<c:out value='${book.genre}' />">
                            <option value="Detective" selected>Detective</option>
                            <option value="Drama">Drama</option>
                            <option value="Romance">Romance</option>
                            <option value="Science">Science</option>
                            <option value="History">History</option>
                            <option value="Poetry">Poetry</option>
                            <option value="Other">Other</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th>Mark:</th>
                    <td>
                        <%--                        <c:if test="${book != null}">--%>
                        <%--                            <c:set target="myForm" property="mark" value="${book.mark}"/>--%>
                        <%--                        </c:if>--%>
                        <input type="radio" name="mark" value="1"> 1
                        <input type="radio" name="mark" value="2"> 2
                        <input type="radio" name="mark" value="3"> 3
                        <input type="radio" name="mark" value="4"> 4
                        <input type="radio" name="mark" value="5"> 5
                    </td>
                </tr>
                <tr>
                    <th>Comment:</th>
                    <td>
                        <textarea rows="5" cols="50" name="comment"> <c:out value='${book.comment}'/></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>

            </table>
        </form>
</div>
</body>
</html>
