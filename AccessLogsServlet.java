import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AccessLogsServlet")
public class AccessLogsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> accessLogs = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                String query = "SELECT al.log_id, u.username, al.action, al.log_timestamp " +
                               "FROM AccessLogs al JOIN Users u ON al.user_id = u.user_id ORDER BY al.log_timestamp DESC";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Map<String, String> log = new HashMap<>();
                    log.put("log_id", String.valueOf(rs.getInt("log_id")));
                    log.put("username", rs.getString("username"));
                    log.put("action", rs.getString("action"));
                    log.put("log_timestamp", String.valueOf(rs.getTimestamp("log_timestamp")));
                    accessLogs.add(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Error fetching access logs");
            return;
        }

        // Pass logs to the JSP
        request.setAttribute("accessLogs", accessLogs);
        request.getRequestDispatcher("access_logs.jsp").forward(request, response);
    }
}
