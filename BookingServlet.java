//
//
//import java.io.*;
//import java.sql.*;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//@WebServlet("/BookingServlet")
//public class BookingServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	HttpSession session = request.getSession(false);
//    	if (session == null || session.getAttribute("user_id") == null) {
//    	    response.sendRedirect("login.jsp?error=Please log in first");
//    	    return;
//    	}
//    	int userId = (Integer) session.getAttribute("user_id");
//
//        String roomType = request.getParameter("room_type");
//        String date = request.getParameter("date");
//        String shift = request.getParameter("shift");
//        String eventName = request.getParameter("event_name");
//        try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
//            String query = "INSERT INTO Bookings (user_id, room_type, date, shift, event_name) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setInt(1, userId);
//            stmt.setString(2, roomType);
//            stmt.setString(3, date);
//            stmt.setString(4, shift);
//            stmt.setString(5, eventName);
//            stmt.executeUpdate();
//
//            response.sendRedirect("user_dashboard.jsp?message=Booking successful");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendRedirect("book_room.jsp?error=Error occurred during booking");
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        int userId = (Integer) session.getAttribute("user_id");
//        try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
//            String query = "SELECT * FROM Bookings WHERE user_id=?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setInt(1, userId);
//
//            ResultSet rs = stmt.executeQuery();
//            request.setAttribute("bookings", rs);
//            request.getRequestDispatcher("view_bookings.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session if it exists, do not create a new one.

        // Check if user is logged in
        if (session == null || session.getAttribute("user_id") == null) {
            // Save the target URL to redirect back after login
            session = request.getSession(true);
            session.setAttribute("target_url", "book_room.jsp");
            response.sendRedirect("login.jsp?error=Please log in first");
            return;
        }

        // Get user ID from session
        int userId = (Integer) session.getAttribute("user_id");

        // Retrieve form inputs
        String roomType = request.getParameter("room_type");
        String date = request.getParameter("date");
        String shift = request.getParameter("shift");
        String eventName = request.getParameter("event_name");

        // Validate inputs
        if (roomType == null || date == null || shift == null || eventName == null) {
            response.sendRedirect("book_room.jsp?error=Missing required fields");
            return;
        }

        // Validate booking date
        try {
            LocalDate bookingDate = LocalDate.parse(date);
            if (bookingDate.isBefore(LocalDate.now())) {
                response.sendRedirect("book_room.jsp?error=Date must be in the future");
                return;
            }
        } catch (DateTimeParseException e) {
            response.sendRedirect("book_room.jsp?error=Invalid date format");
            return;
        }

        // Database connection and room booking
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin")) {
            // Check for room availability
            String checkQuery = "SELECT COUNT(*) FROM Bookings WHERE room_type = ? AND date = ? AND shift = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, roomType);
                checkStmt.setString(2, date);
                checkStmt.setString(3, shift);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) {
                    response.sendRedirect("book_room.jsp?error=Room already booked for the selected date and shift");
                    return;
                }
            }

            // Insert booking into the database
            String insertQuery = "INSERT INTO Bookings (user_id, room_type, date, shift, event_name) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, userId);
                insertStmt.setString(2, roomType);
                insertStmt.setString(3, date);
                insertStmt.setString(4, shift);
                insertStmt.setString(5, eventName);
                insertStmt.executeUpdate();
            }

            // Redirect to dashboard with success message
            response.sendRedirect("user_dashboard.jsp?message=Booking successful");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("book_room.jsp?error=Database error occurred");
        }
    }
}
