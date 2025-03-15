


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        int userId = role.equals("admin") ? 0 : (Integer) session.getAttribute("user_id");
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            String query = role.equals("admin") ? 
                "SELECT * FROM Notifications" : 
                "SELECT * FROM Notifications WHERE user_id=?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            if (!role.equals("admin")) {
                stmt.setInt(1, userId);
            }

            ResultSet rs = stmt.executeQuery();
            request.setAttribute("notifications", rs);
            request.getRequestDispatcher("notifications.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String userIdStr = request.getParameter("user_id");
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            String query = "INSERT INTO Notifications (user_id, message) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userIdStr == null ? 0 : Integer.parseInt(userIdStr));
            stmt.setString(2, message);
            stmt.executeUpdate();

            response.sendRedirect("send_notifications.jsp?message=Notification sent");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

