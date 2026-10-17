<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.renew.model.User" %>

<%
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Renew | Edit Profile</title>

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

<div class="dashboard">

    <!-- Sidebar -->

    <aside class="sidebar">

        <div class="logo">

            <i data-lucide="bell-ring"></i>

            <h2>Renew</h2>

        </div>

        <a href="${pageContext.request.contextPath}/dashboard">

            <i data-lucide="layout-dashboard"></i>

            Dashboard

        </a>

        <a href="${pageContext.request.contextPath}/addReminder">

            <i data-lucide="plus-circle"></i>

            Add Reminder

        </a>

        <a class="active"
           href="#">

            <i data-lucide="user"></i>

            Edit Profile

        </a>

        <a href="${pageContext.request.contextPath}/logout">

            <i data-lucide="log-out"></i>

            Logout

        </a>

    </aside>

    <!-- Main Content -->

    <main class="content">

        <div class="topbar">

            <h1>Edit Profile</h1>

            <a class="btn btn-outline"
               href="${pageContext.request.contextPath}/dashboard">

                Back

            </a>

        </div>

        <div class="form-card">

            <% if(request.getAttribute("error") != null){ %>

                <div class="alert alert-error">

                    <%= request.getAttribute("error") %>

                </div>

            <% } %>

            <% if(request.getAttribute("success") != null){ %>

                <div class="alert alert-success">

                    <%= request.getAttribute("success") %>

                </div>

            <% } %>

            <form action="${pageContext.request.contextPath}/editUser"
                  method="post">

                <div class="form-group">

                    <label>

                        Full Name

                    </label>

                    <input
                            type="text"
                            name="name"
                            value="<%= user.getName() %>"
                            maxlength="100"
                            required>

                </div>

                <div class="form-group">

                    <label>

                        Email Address

                    </label>

                    <input
                            type="email"
                            name="email"
                            value="<%= user.getEmail() %>"
                            maxlength="100"
                            required>

                </div>

                <div class="form-group">

                    <label>

                        Username

                    </label>

                    <input
                            type="text"
                            name="uname"
                            value="<%= user.getUname() %>"
                            maxlength="50"
                            required>

                </div>

                <div class="button-group">

                    <button
                            type="submit"
                            class="btn btn-primary">

                        Save Changes

                    </button>

                    <a
                            href="${pageContext.request.contextPath}/dashboard"
                            class="btn btn-secondary">

                        Cancel

                    </a>

                </div>

            </form>

            <hr class="section-divider">

            <div class="danger-zone">

                <h2>

                    Danger Zone

                </h2>

                <p>

                    Deleting your account is permanent.
                    All reminders associated with this account
                    will be lost.

                </p>

                <form action="${pageContext.request.contextPath}/deleteUser"
                      method="post"
                      onsubmit="return confirm('Are you sure you want to permanently delete your account?');">

                    <button
                            type="submit"
                            class="btn btn-danger">

                        Delete My Account

                    </button>

                </form>

            </div>

        </div>

    </main>

</div>

<script>

    lucide.createIcons();

</script>

<script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>

</html>