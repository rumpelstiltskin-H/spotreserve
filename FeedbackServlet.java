import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve session
        HttpSession session = request.getSession(false);
        
        // Validate session and user authentication
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp?error=Please log in first");
            return;
        }

        String username = (String) session.getAttribute("username");
        String feedbackText = request.getParameter("feedback");

        // Validate feedback input
        if (feedbackText == null || feedbackText.trim().isEmpty()) {
            response.sendRedirect("feedback.jsp?error=Feedback cannot be empty");
            return;
        }

        // Load the JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("feedback.jsp?error=JDBC Driver not found");
            return;
        }

        // Database operations
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            // Fetch the user ID based on username
            PreparedStatement userStmt = conn.prepareStatement("SELECT user_id FROM Users WHERE username=?");
            userStmt.setString(1, username);
            ResultSet rs = userStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // Insert feedback into the database
                PreparedStatement feedbackStmt = conn.prepareStatement("INSERT INTO feedback (user_id, feedback_text) VALUES (?, ?)");
                feedbackStmt.setInt(1, userId);
                feedbackStmt.setString(2, feedbackText);
                feedbackStmt.executeUpdate();

                // Redirect with success message
                response.sendRedirect("feedback.jsp?message=Feedback submitted successfully");
            } else {
                // Handle case where user is not found in the database
                response.sendRedirect("feedback.jsp?error=User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("feedback.jsp?error=Database error occurred");
        }
    }
}
