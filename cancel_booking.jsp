<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cancel Booking</title>
</head>
<body>
    <h1>Cancel Booking</h1>
    <table border="1">
        <tr><th>BookId</th><th>userId</th><th>RoomType</th><th>Date</th><th>Shift</th><th>EventName</th><th>Status</th></tr>
        <%
        Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM bookings");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("booking_id") + "</td>");
                    out.println("<td>" + rs.getInt("user_id") + "</td>");
                    

                    out.println("<td>" + rs.getString("room_type") + "</td>");
                    out.println("<td>" + rs.getString("date") + "</td>");
                    out.println("<td>" + rs.getString("shift") + "</td>");
                    out.println("<td>" + rs.getString("event_name") + "</td>");

                    out.println("<td>" + rs.getString("status") + "</td>");
                    out.println("<td><form action='CancelBookingServlet' method='post'>");
                    out.println("<input type='hidden' name='action1' value='delete'>");
                    out.println("<input type='hidden' name='userId' value='" + rs.getInt("user_id") + "'>");
                    out.println("<button type='submit'>Delete</button></form></td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
    <br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
