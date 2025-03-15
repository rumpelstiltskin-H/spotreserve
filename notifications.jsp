<!DOCTYPE html>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Notifications</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
  <center>
    <div class="container">
        <h2>Your Notifications</h2>
        <table border=1>
            <tr>
                <th>Notification</th>
                <th>Date</th>
            </tr>
            <% 
             /*   HttpSession session = request.getSession(false);*/
                if (session != null && session.getAttribute("user_id") != null) {
                    int userId = (Integer) session.getAttribute("user_id");
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                        String query = "SELECT message, sent_at FROM Notifications WHERE user_id = ? OR user_id = 0";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, userId);

                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("message") %></td>
                <td><%= rs.getTimestamp("sent_at") %></td>
            </tr>
            <% 
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </table>
    </div>
        </center>
    <br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
