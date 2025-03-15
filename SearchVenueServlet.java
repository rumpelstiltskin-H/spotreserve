import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SearchVenueServlet")
public class SearchVenueServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getParameter("location");
        String capacityParam = request.getParameter("capacity");

        int capacity = 0;
        if (capacityParam != null && !capacityParam.isEmpty()) {
            try {
                capacity = Integer.parseInt(capacityParam);
            } catch (NumberFormatException e) {
                capacity = 0; 
            }
        }

        
        List<Map<String, String>> searchResults = new ArrayList<>();

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
                String query = "SELECT venue_id, venue_name, location, capacity, price_per_hour, availability " +
                               "FROM Venues WHERE 1=1";

                if (location != null && !location.isEmpty()) {
                    query += " AND location LIKE ?";
                }
                if (capacity > 0) {
                    query += " AND capacity >= ?";
                }

                PreparedStatement stmt = conn.prepareStatement(query);

                int paramIndex = 1;
                if (location != null && !location.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + location + "%");
                }
                if (capacity > 0) {
                    stmt.setInt(paramIndex++, capacity);
                }

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Map<String, String> venue = new HashMap<>();
                    venue.put("venue_id", String.valueOf(rs.getInt("venue_id")));
                    venue.put("venue_name", rs.getString("venue_name"));
                    venue.put("location", rs.getString("location"));
                    venue.put("capacity", String.valueOf(rs.getInt("capacity")));
                    venue.put("price_per_hour", String.valueOf(rs.getDouble("price_per_hour")));
                    venue.put("availability", rs.getBoolean("availability") ? "Available" : "Unavailable");
                    searchResults.add(venue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("search_venues.jsp?error=Error fetching venues");
            return;
        }

        System.out.println("DEBUG: Total venues found = " + searchResults.size());

        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("search_venues.jsp").forward(request, response);
    }
}
