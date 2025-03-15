<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Feedback</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="form-container">
        <h2>Submit Feedback</h2>

        <% 
            String error = request.getParameter("error");
            String message = request.getParameter("message");
        %>
        
        <% if (error != null) { %>
            <p class="error-message"><%= error %></p>
        <% } %>
        <% if (message != null) { %>
            <p class="success-message"><%= message %></p>
        <% } %>

        <form action="FeedbackServlet" method="post">
            <textarea name="feedback" placeholder="Enter your feedback..." required></textarea>
            <button type="submit">Submit Feedback</button>
        </form>
    </div>
    <br><br>
                   <div>
                     <a href="user_dashboard.jsp"><b>BACK ClickHere</b></a>
                   </div>
</body>
</html>
