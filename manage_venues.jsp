<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Venues</title>
</head>
<body>
    <h1>Manage Venues</h1>
    <form action="VenueManagementServlet" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="venueName" placeholder="Venue Name" required>
        <input type="text" name="location" placeholder="Location" required>
        <input type="number" name="capacity" placeholder="Capacity" required>
        <input type="number" name="price_per_hour" placeholder="price_per_hour" required>
       
        <button type="submit">Add Venue</button>
    </form>
    <table border="1">
        <tr><th>ID</th><th>Venue Name</th><th>Location</th><th>Capacity</th><th>Action</th></tr>
        <%
        Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM venues");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("venue_id") + "</td>");
                    out.println("<td>" + rs.getString("venue_name") + "</td>");
                    out.println("<td>" + rs.getString("location") + "</td>");
                    out.println("<td>" + rs.getInt("capacity") + "</td>");
                    out.println("<td><form action='VenueManagementServlet' method='post'>");
                    out.println("<input type='hidden' name='action' value='delete'>");
                    out.println("<input type='hidden' name='venueId' value='" + rs.getInt("venue_id") + "'>");
                    out.println("<button type='submit'>Delete</button></form></td>");
                    out.println("</tr>");
                }
            }
        %>
    </table><br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
