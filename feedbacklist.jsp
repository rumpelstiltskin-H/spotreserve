<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<tbody>
    <%
        ResultSet feedbackList = (ResultSet) request.getAttribute("feedbacklist");
        boolean hasFeedback = false;
        if (feedbackList != null) {
            while (feedbackList.next()) {
                hasFeedback = true;
    %>
    <tr>
        <td><%= feedbackList.getInt("feedback_id") %></td>
        <td><%= feedbackList.getString("username") %></td>
        <td><%= feedbackList.getString("feedback_text") %></td>
        <td><%= feedbackList.getTimestamp("created_at") %></td>
        <td>
            <form action="FeedbackManagementServlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="feedback_id" value="<%= feedbackList.getInt("feedback_id") %>">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <% 
            }
        }
        if (!hasFeedback) {
    %>
    <tr>
        <td colspan="5">
            <p style="text-align: center;">No feedback available. Encourage users to submit feedback.</p>
        </td>
    </tr>
    <% } %>
</tbody>

</body>
</html>