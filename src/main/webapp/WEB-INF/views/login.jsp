<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Renew | Login</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

    <script src="https://unpkg.com/lucide@latest"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>

<div class="auth-container">

    <div class="auth-card">

        <div class="logo">
            <i data-lucide="bell-ring"></i>
            <h1>Renew</h1>
        </div>

        <h2>Welcome Back</h2>

        <p class="subtitle">
            Sign in to continue managing your reminders.
        </p>

        <% if(request.getAttribute("error") != null){ %>
            <div class="alert alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="form-group">

                <label>Email or Username</label>

                <input
                        type="text"
                        name="loginId"
                        placeholder="Enter your email or username"
                        required>

            </div>

            <div class="form-group">

                <label>Password</label>

                <input
                        type="password"
                        name="password"
                        placeholder="Enter your password"
                        required>

            </div>

            <button type="submit" class="btn btn-primary">
                Login
            </button>

        </form>

        <div class="divider">
            <span>or</span>
        </div>

        <p class="bottom-text">
            Don't have an account?
            <a href="${pageContext.request.contextPath}/signup">
                Create one
            </a>
        </p>

    </div>

</div>

<script>
    lucide.createIcons();
</script>

</body>
</html>