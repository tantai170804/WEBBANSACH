<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách</title>
</head>
<body>

<h1>📚 Danh sách sách</h1>

<ul>
<%
    if (books != null) {
        for (Book b : books) {
%>
    <li>
        <a href="book-detail?id=<%= b.getId() %>">
            <%= b.getTitle() %>
        </a>
        – <%= b.getAuthor() %>
    </li>
<%
        }
    }
%>
</ul>

</body>
</html>
