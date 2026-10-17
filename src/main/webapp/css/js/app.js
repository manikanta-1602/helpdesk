/*
======================================
Renew - app.js
======================================
*/

// ==============================
// Auto-hide alerts
// ==============================

window.addEventListener("load", () => {

    const alerts = document.querySelectorAll(".alert");

    if (alerts.length > 0) {

        setTimeout(() => {

            alerts.forEach(alert => {

                alert.style.opacity = "0";

                setTimeout(() => {

                    alert.remove();

                }, 400);

            });

        }, 3000);

    }

});

// ==============================
// Delete Reminder Confirmation
// ==============================

document.querySelectorAll(".delete-btn").forEach(button => {

    button.addEventListener("click", function (event) {

        const confirmDelete = confirm(
            "Delete this reminder?"
        );

        if (!confirmDelete) {

            event.preventDefault();

        }

    });

});

// ==============================
// Delete Account Confirmation
// ==============================

const deleteAccountBtn = document.getElementById("deleteAccountBtn");

if (deleteAccountBtn != null) {

    deleteAccountBtn.addEventListener("click", function (event) {

        const confirmDelete = confirm(
            "This action is permanent.\n\nDelete your account?"
        );

        if (!confirmDelete) {

            event.preventDefault();

        }

    });

}

// ==============================
// Password Confirmation
// (Signup Page)
// ==============================

const signupForm = document.getElementById("signupForm");

if (signupForm != null) {

    signupForm.addEventListener("submit", function (event) {

        const password =
            document.getElementById("password").value;

        const confirmPassword =
            document.getElementById("confirmPassword").value;

        if (password !== confirmPassword) {

            alert("Passwords do not match.");

            event.preventDefault();

        }

    });

}

// ==============================
// Trim whitespace
// ==============================

document.querySelectorAll("input[type='text'], input[type='email'], textarea")
    .forEach(input => {

        input.addEventListener("blur", function () {

            this.value = this.value.trim();

        });

    });

// ==============================
// Prevent past reminder dates
// ==============================

const reminderDate =
    document.querySelector("input[type='datetime-local']");

if (reminderDate != null) {

    const now = new Date();

    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());

    reminderDate.min = now.toISOString().slice(0, 16);

}

// ==============================
// End
// ==============================