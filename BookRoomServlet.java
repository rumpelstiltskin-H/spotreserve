import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookRoomServlet")
public class BookRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String roomType = request.getParameter("room_type");
        String date = request.getParameter("date");
        String shift = request.getParameter("shift");
        String eventName = request.getParameter("event_name");
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            // Fetch user ID
            String userQuery = "SELECT user_id FROM Users WHERE username=?";
            PreparedStatement userStmt = conn.prepareStatement(userQuery);
            userStmt.setString(1, username);
            ResultSet rs = userStmt.executeQuery();
            int userId = rs.next() ? rs.getInt("user_id") : 0;
            try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    		} catch (ClassNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
            // Insert booking
            String query = "INSERT INTO Bookings (user_id, room_type, date, shift, event_name) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setString(2, roomType);
            stmt.setString(3, date);
            stmt.setString(4, shift);
            stmt.setString(5, eventName);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("user_dashboard.jsp?message=Booking successful");
            } else {
                response.sendRedirect("book_room.jsp?error=Booking failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
