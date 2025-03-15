

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            if ("approve".equals(action)) {
                int bookingId = Integer.parseInt(request.getParameter("booking_id"));
                PreparedStatement stmt = conn.prepareStatement("UPDATE Bookings SET status='Approved' WHERE booking_id=?");
                stmt.setInt(1, bookingId);
                stmt.executeUpdate();
                response.sendRedirect("manage_bookings.jsp?message=Booking approved");
            } else if ("reject".equals(action)) {
                int bookingId = Integer.parseInt(request.getParameter("booking_id"));
                PreparedStatement stmt = conn.prepareStatement("UPDATE Bookings SET status='Rejected' WHERE booking_id=?");
                stmt.setInt(1, bookingId);
                stmt.executeUpdate();
                response.sendRedirect("manage_bookings.jsp?message=Booking rejected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
