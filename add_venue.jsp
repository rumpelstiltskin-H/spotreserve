<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="VenueManagementServlet" method="POST">
    <input type="hidden" name="action" value="add">
    <label>Venue Name:</label>
    <input type="text" name="venue_name" required>
    <label>Location:</label>
    <input type="text" name="location" required>
    <label>Capacity:</label>
    <input type="number" name="capacity" required>
    <label>Price Per Hour:</label>
    <input type="number" step="0.01" name="price_per_hour" required> <!-- Add this input -->
    <button type="submit">Add Venue</button>
</form>

</body>
</html>