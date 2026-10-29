# 🏋️ Gym Management System

A desktop-based Gym Management System developed using **Java Swing** and **MySQL**. This application provides a simple interface for managing gym members, payments, and attendance.

## ✨ Features

- 🔐 **Login System** — Access the application through a login screen.
- 📊 **Dashboard** — Navigate between the main management sections.
- 👥 **Member Management** — Manage gym member information.
- 💳 **Payment Management** — Record and manage member payments.
- 📅 **Attendance Management** — Track gym attendance.
- 🎨 **Modern UI** — Dark theme with green accents.

## 🛠️ Technologies Used

- **Java** — Core programming language
- **Java Swing** — Graphical user interface
- **MySQL** — Database management
- **JDBC** — Java database connectivity
- **Eclipse IDE** — Development environment

## 📋 Prerequisites

Before running the project, install:

- Java JDK 8 or later
- Eclipse IDE
- MySQL Server
- MySQL Connector/J

## ⚙️ Setup and Installation

1. Clone or download this repository.
2. Open Eclipse IDE.
3. Select **File → Import → Existing Projects into Workspace**.
4. Choose the downloaded project folder.
5. Create a MySQL database:

   ```sql
   CREATE DATABASE gym_management;
   ```

6. Update the database connection settings in `DBConnection.java` to match your MySQL username, password, and database configuration.
7. Add the MySQL Connector/J library to the project's build path.
8. Run `GymManagementSystem.java` to start the application.

## 🗄️ Database Configuration

Make sure MySQL Server is running and the `gym_management` database exists.

Update the connection URL, username, and password in `DBConnection.java` as needed.

> **Security note:** Never upload real database passwords or other secret credentials to a public repository.

## 📁 Project Modules

- `LoginFrame.java` — Login screen
- `DashboardFrame.java` — Main dashboard
- `MemberFrame.java` — Member management
- `PaymentFrame.java` — Payment management
- `AttendanceFrame.java` — Attendance management
- `DBConnection.java` — Database connection
- `GymTheme.java` — Shared UI styling
- `GymManagementSystem.java` — Application entry point

## 🚀 Future Improvements

- Database-backed user authentication
- Member search and filtering
- Payment reports
- Attendance summaries
- Improved input validation

## 👨‍💻 Author

**Karthik**

GitHub: [@karthik123461](https://github.com/karthik123461)

---

⭐ If you find this project useful, consider giving the repository a star!
