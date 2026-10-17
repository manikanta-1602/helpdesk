<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Renew | Sign Up</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

    <link rel="preconnect"
          href="https://fonts.googleapis.com">

    <link rel="preconnect"
          href="https://fonts.gstatic.com"
          crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

    <script src="https://unpkg.com/lucide@latest"></script>

</head>

<body>

<div class="auth-container">

    <div class="auth-card signup-card">

        <div class="logo">

            <i data-lucide="bell-ring"></i>

            <h1>Renew</h1>

        </div>

        <h2>Create Account</h2>

        <p class="subtitle">
            Start managing your reminders effortlessly.
        </p>

        <% if(request.getAttribute("error") != null){ %>

            <div class="alert alert-error">

                <%= request.getAttribute("error") %>

            </div>

        <% } %>

        <form action="${pageContext.request.contextPath}/signup"
              method="post">

            <div class="form-group">

                <label>Full Name</label>

                <input
                        type="text"
                        name="name"
                        placeholder="John Doe"
                        required>

            </div>

            <div class="form-group">

                <label>Email</label>

                <input
                        type="email"
                        name="email"
                        placeholder="john@example.com"
                        required>

            </div>

            <div class="form-group">

                <label>Username</label>

                <input
                        type="text"
                        name="uname"
                        placeholder="john_doe"
                        required>

            </div>

            <div class="form-group">

                <label>Password</label>

                <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Enter password"
                        minlength="8"
                        required>

            </div>

            <div class="form-group">

                <label>Confirm Password</label>

                <input
                        type="password"
                        id="confirmPassword"
                        placeholder="Confirm password"
                        minlength="8"
                        required>

            </div>

            <button type="submit"
                    class="btn btn-primary btn-full">

                Create Account

            </button>

        </form>

        <div class="divider">

            <span>or</span>

        </div>

        <p class="bottom-text">

            Already have an account?

            <a href="${pageContext.request.contextPath}/login">

                Login

            </a>

        </p>

    </div>

</div>

<script>

    lucide.createIcons();

    const form = document.querySelector("form");

    form.addEventListener("submit", function(e){

        const password =
                document.getElementById("password").value;

        const confirm =
                document.getElementById("confirmPassword").value;

        if(password !== confirm){

            e.preventDefault();

            alert("Passwords do not match.");

        }

    });

</script>

</body>

</html>