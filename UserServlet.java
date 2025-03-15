import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Driver not found");
            return;
        }

        // Connect to the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            
            // Handle registration
            if ("register".equals(action)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)");
                stmt.setString(1, username);
                stmt.setString(2, password); // Add encryption in production
                stmt.setString(3, role);
                stmt.executeUpdate();

                response.sendRedirect("login.jsp?message=Registration successful");
            } 
            if ("login".equals(action)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    HttpSession session = request.getSession();
                    session.setAttribute("user_id", userId);
                    session.setAttribute("username", username);

                    PreparedStatement logStmt = conn.prepareStatement("INSERT INTO AccessLogs (user_id, action) VALUES (?, ?)");
                    logStmt.setInt(1, userId);
                    logStmt.setString(2, "Logged In");
                    logStmt.executeUpdate();

                    response.sendRedirect("user_dashboard.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
