<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Manage Bookings</h2>
<table border="1">
    <tr>
        <th>User</th>
        <th>Room</th>
        <th>Date</th>
        <th>Shift</th>
        <th>Event Name</th>
        <th>Action</th>
    </tr>
    <% 
   
		Class.forName("com.mysql.cj.jdbc.Driver");
	
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Bookings WHERE status = 'Pending'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("user_id") %></td>
        <td><%= rs.getString("room_type") %></td>
        <td><%= rs.getDate("date") %></td>
        <td><%= rs.getString("shift") %></td>
        <td><%= rs.getString("event_name") %></td>
        <td>
            <form action="AdminServlet" method="post" style="display:inline;">
                <input type="hidden" name="booking_id" value="<%= rs.getInt("booking_id") %>" />
                <button type="submit" name="action" value="approve">Approve</button>
                <button type="submit" name="action" value="reject">Reject</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>