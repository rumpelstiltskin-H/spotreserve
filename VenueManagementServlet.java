
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/VenueManagementServlet")
public class VenueManagementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String venueName = request.getParameter("venueName");
        String location = request.getParameter("location");
        int price_per_hour=Integer.parseInt(request.getParameter("price_per_hour"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                if ("add".equals(action)) {
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO venues (venue_name, location, capacity,price_per_hour) VALUES (?, ?,?,?)");
                    stmt.setString(1, venueName);
                    stmt.setString(2, location);
                    stmt.setInt(3, capacity);
                    stmt.setInt(4, price_per_hour);
                    stmt.executeUpdate();
                    System.out.println(stmt);
                    response.sendRedirect("manage_venues.jsp?message=Venue added successfully");
                } else if ("delete".equals(action)) {
                    int venueId = Integer.parseInt(request.getParameter("venueId"));
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM venues WHERE id=?");
                    stmt.setInt(1, venueId);
                    stmt.executeUpdate();
                    response.sendRedirect("manage_venues.jsp?message=Venue deleted successfully");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage_venues.jsp?error=Error processing request");
        }
    }
}

