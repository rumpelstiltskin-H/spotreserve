<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Send Notifications</h2>
<form action="NotificationServlet" method="post">
    <label>User ID (0 for all users):</label>
    <input type="number" name="user_id" />
    <label>Message:</label>
    <textarea name="message" required></textarea>
    <button type="submit">Send</button>
</form>
<br><br>
                   <div>
                     <a href="admin_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>