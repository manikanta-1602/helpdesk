<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.renew.model.Reminder" %>

<%
    List<Reminder> reminders =
            (List<Reminder>) request.getAttribute("reminders");
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Renew | Dashboard</title>

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

        <a href="${pageContext.request.contextPath}/editUser">

            <i data-lucide="user"></i>

            Edit Profile

        </a>

        <a href="${pageContext.request.contextPath}/logout">

            <i data-lucide="log-out"></i>

            Logout

        </a>

    </aside>

    <main class="content">

        <div class="topbar">

            <h1>Your Reminders</h1>

            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/addReminder">

                + New Reminder

            </a>

        </div>

        <div class="cards">

        <% if(reminders == null || reminders.isEmpty()){ %>

            <div class="empty-card">

                <i data-lucide="calendar-plus"></i>

                <h2>No reminders yet</h2>

                <p>Create your first reminder.</p>

            </div>

        <% } else { %>

            <% for(Reminder reminder : reminders){ %>

                <div class="reminder-card">

                    <div class="card-header">

                        <h2>

                            <%= reminder.getName() %>

                        </h2>

                    </div>

                    <div class="card-body">

                        <p>

                            <strong>Date :</strong>

                            <%= reminder.getDateTime() %>

                        </p>

                        <p>

                            <strong>Notes :</strong>

                            <%= reminder.getNotes() %>

                        </p>

                    </div>

                    <div class="card-actions">

                        <a class="btn btn-outline"

                           href="${pageContext.request.contextPath}/editReminder?reminderId=<%= reminder.getId() %>">

                            Edit

                        </a>

                        <form action="${pageContext.request.contextPath}/deleteReminder"
                              method="post">

                            <input type="hidden"
                                   name="reminderId"
                                   value="<%= reminder.getId() %>">

                            <button class="btn btn-danger"
                                    type="submit">

                                Delete

                            </button>

                        </form>

                    </div>

                </div>

            <% } %>

        <% } %>

        </div>

    </main>

</div>

<script>

    lucide.createIcons();

</script>

</body>

</html>