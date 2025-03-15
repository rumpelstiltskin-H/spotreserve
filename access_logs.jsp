<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Access Logs</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Access Logs</h1>

    <%
        List<Map<String, String>> accessLogs = (List<Map<String, String>>) request.getAttribute("accessLogs");

        if (accessLogs == null || accessLogs.isEmpty()) {
    %>
        <p>No logs available.</p>
    <%
        } else {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>Log ID</th>
                    <th>Username</th>
                    <th>Action</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Map<String, String> log : accessLogs) {
                %>
                <tr>
                    <td><%= log.get("log_id") %></td>
                    <td><%= log.get("username") %></td>
                    <td><%= log.get("action") %></td>
                    <td><%= log.get("log_timestamp") %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>
</body>
</html>
