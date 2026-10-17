<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.renew.model.Reminder" %>

<%
    Reminder reminder = (Reminder) request.getAttribute("reminder");
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Renew | Edit Reminder</title>

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

            <i data-lucide="square-pen"></i>

            Edit Reminder

        </a>

        <a href="${pageContext.request.contextPath}/editUser">

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

            <h1>Edit Reminder</h1>

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

            <form action="${pageContext.request.contextPath}/editReminder"
                  method="post">

                <input
                        type="hidden"
                        name="reminderId"
                        value="<%= reminder.getId() %>">

                <div class="form-group">

                    <label>

                        Reminder Name

                    </label>

                    <input
                            type="text"
                            name="reminderName"
                            value="<%= reminder.getName() %>"
                            maxlength="100"
                            required>

                </div>

                <div class="form-group">

                    <label>

                        Notes

                    </label>

                    <textarea
                            name="reminderNotes"
                            rows="5"><%= reminder.getNotes() == null ? "" : reminder.getNotes() %></textarea>

                </div>

                <div class="form-group">

                    <label>

                        Reminder Date & Time

                    </label>

                    <input
                            type="datetime-local"
                            name="reminderDateTime"
                            value="<%= reminder.getDateTime().toString() %>"
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

        </div>

    </main>

</div>

<script>

    lucide.createIcons();

</script>

<script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>

</html>