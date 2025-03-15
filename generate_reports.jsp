<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate Reports</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <center>
    <br><br>
        <h2>Booking Reports</h2>
        <table border="1">
            <tr>
                <th>Room Type</th>
                <th>Total Bookings</th>
            </tr>
            <% 
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin");
                    String query = "SELECT room_type, COUNT(*) AS total_bookings FROM Bookings GROUP BY room_type";
                     PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
            %>
            <tr>
            
                <td><%= rs.getString("room_type") %></td>
                <td><%= rs.getInt("total_bookings") %></td>
            </tr>
            <% 
                    }
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
        <br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
                   </center>
    </div>
</body>
</html>
