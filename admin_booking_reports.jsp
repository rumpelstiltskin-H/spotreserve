<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Reports</title>
</head>
<body>
    <h1>Booking Reports</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>User ID</th>
                <th>Username</th>
                <th>Room Type</th>
                <th>Date</th>
                <th>Shift</th>
                <th>Event Name</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Map<String, String>> bookings = (List<Map<String, String>>) request.getAttribute("bookings");
                if (bookings == null) {
                    System.out.println("DEBUG: bookings list is null in JSP.");
                } else {
                    System.out.println("DEBUG: Total bookings passed to JSP = " + bookings.size());
                }

                if (bookings != null && !bookings.isEmpty()) {
                    for (Map<String, String> booking : bookings) {
            %>
            <tr>
                <td><%= booking.get("booking_id") %></td>
                <td><%= booking.get("user_id") %></td>
                <td><%= booking.get("username") %></td>
                <td><%= booking.get("room_type") %></td>
                <td><%= booking.get("date") %></td>
                <td><%= booking.get("shift") %></td>
                <td><%= booking.get("event_name") %></td>
                <td><%= booking.get("status") %></td>
                <td>
                    <form action="AdminBookingServlet" method="post">
                        <input type="hidden" name="booking_id" value="<%= booking.get("booking_id") %>">
                        <select name="status">
                            <option value="Pending" <%= "Pending".equals(booking.get("status")) ? "selected" : "" %>>Pending</option>
                            <option value="Approved" <%= "Approved".equals(booking.get("status")) ? "selected" : "" %>>Approved</option>
                            <option value="Rejected" <%= "Rejected".equals(booking.get("status")) ? "selected" : "" %>>Rejected</option>
                        </select>
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9" style="text-align: center;">No bookings found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
        
    </table>
    <br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
