<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="form-container">
        <h2>Login</h2>
        <% 
            // Fetch the error message from the query parameter (if any)
            String error = request.getParameter("error"); 
            String message = request.getParameter("message");
        %>
        
        <!-- Display error message -->
        <% if (error != null) { %>
            <p class="error-message"><%= error %></p>
        <% } %>
        
        <!-- Display success message (e.g., after registration) -->
        <% if (message != null) { %>
            <p class="success-message"><%= message %></p>
        <% } %>
        
        <!-- Login Form -->
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="login" />
            <input type="text" name="username" placeholder="Enter Username" required />
            <input type="password" name="password" placeholder="Enter Password" required />
            <button type="submit">Login</button>
        </form>
        
        <p>Don't have an account? <a href="register.jsp">Register here</a>.</p>
    </div>
</body>
</html>
