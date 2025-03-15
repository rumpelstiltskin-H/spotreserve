<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Search Venues</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>Search Venues</h1>

    <form action="SearchVenueServlet" method="GET">
        <label for="location">Location:</label>
        <input type="text" name="location" id="location" placeholder="Enter location">
        <label for="capacity">Capacity:</label>
        <input type="number" name="capacity" id="capacity" placeholder="Min capacity">
        <button type="submit">Search</button>
    </form>

    <%
        List<Map<String, String>> searchResults = (List<Map<String, String>>) request.getAttribute("searchResults");

        if (searchResults == null || searchResults.isEmpty()) {
    %>
        <p>No venues found.</p>
    <%
        } else {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>Venue Name</th>
                    <th>Location</th>
                    <th>Capacity</th>
                    <th>Price Per Hour</th>
                    <th>Availability</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Map<String, String> venue : searchResults) {
                %>
                <tr>
                    <td><%= venue.get("venue_name") %></td>
                    <td><%= venue.get("location") %></td>
                    <td><%= venue.get("capacity") %></td>
                    <td><%= venue.get("price_per_hour") %></td>
                    <td><%= venue.get("availability") %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>
    <br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
