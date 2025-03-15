import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/ReportServlet")
//public class ReportServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin");
//
//            // Fetch total bookings grouped by room type
//            String query = "SELECT room_type, COUNT(*) AS total_bookings FROM Bookings GROUP BY room_type";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//
//            // Store the result set in the request attribute to forward it to the JSP
//            request.setAttribute("reportData", rs);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("generate_reports.jsp");
//            dispatcher.forward(request, response);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("admin_dashboard.jsp?error=Error generating reports");
//        }
//    }
//}

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "admin");

            String query = "SELECT room_type, COUNT(*) AS total_bookings FROM Bookings GROUP BY room_type";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            request.setAttribute("reportData", rs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("generate_reports.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_dashboard.jsp?error=Error generating reports");
        }
    }
}


