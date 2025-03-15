
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                if ("delete".equals(action)) {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE user_id=?");
                    stmt.setInt(1, userId);
                    stmt.executeUpdate();
                    response.sendRedirect("cancel_booking.jsp?message=User deleted successfully");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("user_dashboard.jsp?error=Error processing request");
        }
    }
}

