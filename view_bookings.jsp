<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <center>
<h2>My Bookings</h2>
<table border=1>
    <tr>
        <th>Room</th>
        <th>Date</th>
        <th>Shift</th>
        <th>Event Name</th>
        <th>Status</th>
    </tr>
    <% 
	Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bookings WHERE user_id = ?");
        stmt.setInt(1, (Integer) session.getAttribute("user_id"));
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("room_type") %></td>
        <td><%= rs.getDate("date") %></td>
        <td><%= rs.getString("shift") %></td>
        <td><%= rs.getString("event_name") %></td>
        <td><%= rs.getString("status") %></td>
    </tr>
    <% } %>
</table>
</center>
<br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>