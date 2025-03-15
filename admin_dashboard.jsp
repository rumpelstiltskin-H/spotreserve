<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> <!-- Include header if available -->
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Add your CSS file for styling -->
</head>
<body>
     <marquee>  <h1>ST ANNS SPOT RESERVER SYSTEM</h1></marquee> 
<br><br>
    <h1>Welcome, Admin!</h1>
    <h3>Dashboard</h3>
    <div class="dashboard-container">
        <a href="manage_venues.jsp" class="button">Manage Venues</a>
        <a href="manage_users.jsp" class="button">Manage Users</a>
        <a href="manage_bookings.jsp" class="button">ViewBookings/UpdateStatus</a>
        
        <a href="viewFeedBack.jsp" class="button">Feedback Management</a>
        <a href="send_notifications.jsp" class="button">Send Notifications</a>
        <a href="generate_reports.jsp" class="button">Generate Reports</a>
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
    
    
</body>
</html>
