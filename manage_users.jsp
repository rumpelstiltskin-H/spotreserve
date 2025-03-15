<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>
    <h1>Manage Users</h1>
    <table border="1">
        <tr><th>ID</th><th>Username</th><th>Role</th><th>Action</th></tr>
        <%
        Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("user_id") + "</td>");
                    out.println("<td>" + rs.getString("username") + "</td>");
                    out.println("<td>" + rs.getString("role") + "</td>");
                    out.println("<td><form action='UserManagementServlet' method='post'>");
                    out.println("<input type='hidden' name='action' value='delete'>");
                    out.println("<input type='hidden' name='userId' value='" + rs.getInt("user_id") + "'>");
                    out.println("<button type='submit'>Delete</button></form></td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
    <br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
