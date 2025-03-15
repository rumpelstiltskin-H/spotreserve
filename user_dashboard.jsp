<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> <!-- Include header if available -->
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Add your CSS file for styling -->
</head>
<body>
       <marquee>  <h1>ST ANNS SPOT RESERVER SYSTEM</h1></marquee> 
  <br><br>
    <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
    <h3>Dashboard</h3>
    <div class="dashboard-container">
        <a href="search_venues.jsp" class="button">Search Venues</a>
        <a href="book_room.jsp" class="button">Make a Booking</a>
        <a href="view_bookings.jsp" class="button">View Booking Status</a>
        <a href="notifications.jsp" class="button">View Notifications</a>
        <a href="feedback.jsp" class="button">Submit Feedback</a>
        <a href="cancel_booking.jsp" class="button">Cancel Booking</a>
        <a href="index.jsp" class="button">Logout</a>
    </div>
    <%@ page contentType="text/html; charset=UTF-8" %>


<style>
    .dashboard {
        text-align: center;
        margin-top: 20px;
    }

    .dashboard-actions {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
    }

    .button {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        font-weight: bold;
    }

    .button:hover {
        background-color: #0056b3;
    }
</style>
    
    <br><br>
                   <div>
                     <a href="index.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
