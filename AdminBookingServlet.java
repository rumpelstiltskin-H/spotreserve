import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminBookingServlet")
public class AdminBookingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> bookings = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                System.out.println("DEBUG: Connected to the database.");

                String query = "SELECT b.booking_id, b.user_id, b.room_type, b.date, b.shift, b.event_name, b.status, u.username " +
                               "FROM Bookings b JOIN Users u ON b.user_id = u.user_id ORDER BY b.date ASC";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("DEBUG: No rows returned from the query.");
                } else {
                    while (rs.next()) {
                        Map<String, String> booking = new HashMap<>();
                        booking.put("booking_id", String.valueOf(rs.getInt("booking_id")));
                        booking.put("user_id", String.valueOf(rs.getInt("user_id")));
                        booking.put("username", rs.getString("username"));
                        booking.put("room_type", rs.getString("room_type"));
                        booking.put("date", String.valueOf(rs.getDate("date")));
                        booking.put("shift", rs.getString("shift"));
                        booking.put("event_name", rs.getString("event_name"));
                        booking.put("status", rs.getString("status"));

                        bookings.add(booking);
System.out.println("booking stutas "+bookings);

                        // Debug log for each booking
                        System.out.println("DEBUG: Booking fetched = " + booking);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Error fetching bookings");
            return;
        }

        // Log the size of bookings
        System.out.println("DEBUG: Total bookings fetched = " + bookings.size());

        // Pass bookings to the JSP
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("admin_booking_reports.jsp").forward(request, response);
    }
}
