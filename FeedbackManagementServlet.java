import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FeedbackManagementServlet")
public class FeedbackManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                String query = "SELECT feedback_id, user_id, feedback_text, created_at FROM feedback";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                printFeedbackTable(response.getWriter(), rs);

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("admin_dashboard.jsp?error=Error loading feedback");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Error loading feedback");
        }
    }

    private void printFeedbackTable(PrintWriter out, ResultSet rs) throws SQLException {
        // Get metadata about the result set
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            out.printf("%-30s", metaData.getColumnName(i));
        }
        out.println();

        // Print column divider
        for (int i = 1; i <= columnCount; i++) {
            out.print("--------------------");
        }
        out.println();

        
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                out.printf("%-30s", value != null ? value : "");
            }
            out.println();
        }
    }
    

 }
