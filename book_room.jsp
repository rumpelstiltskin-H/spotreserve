<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <br><br><br>
     <center>
        <h1>RoomBooking</h1>
<form action="BookRoomServlet" method="post">
    <label>Room Type:</label>
    <select name="room_type">
        <option value="AV Room">AV Room</option>
        <option value="Room 47">Room 47</option>
        <option value="Auditorium">Auditorium</option>
    </select>
    <label>Date:</label>
    <input type="date" name="date" required />
    <label>Shift:</label>
    <select name="shift">
        <option value="Morning">Morning</option>
        <option value="Afternoon">Afternoon</option>
        <option value="All Day">All Day</option>
    </select>
    <label>Event Name:</label>
    <input type="text" name="event_name" required />
    <button type="submit">Book Room</button>
</form>
 </center>
<br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>