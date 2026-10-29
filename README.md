# 🏋️ Gym Management System

### A Java Swing & MySQL Desktop Application

A desktop application designed to simplify everyday gym management tasks through a clean graphical interface and a MySQL database.

**Developed by [KARTHIK-M-J](https://github.com/KARTHIK-M-J)**

[View Repository](https://github.com/KARTHIK-M-J/GymManagementSystem)

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Application Modules](#-application-modules)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation and Setup](#-installation-and-setup)
- [Database Configuration](#-database-configuration)
- [Running the Application](#-running-the-application)
- [Future Improvements](#-future-improvements)
- [Author](#-author)

---

## 🎯 About the Project

The **Gym Management System** is a Java desktop application developed to organize common gym administration activities in one place.

The project uses Java Swing to provide the graphical user interface and MySQL to store application data. It is designed as a practical project for learning desktop application development, database connectivity, and CRUD operations.

The main goal is to make gym administration more organized, accessible, and easier to manage.

## ✨ Features

### 🔐 Login System
- Provides a login screen to access the application.
- Can be extended with database-backed authentication and role-based access.

### 📊 Dashboard
- Provides a central navigation screen.
- Offers access to the main management modules.
- Uses a modern dark interface with green accents.

### 👥 Member Management
- Dedicated module for gym member information.
- Designed to support organized member record management.

### 💳 Payment Management
- Dedicated module for payment-related records.
- Can be extended with payment history and balance tracking.

### 📅 Attendance Management
- Dedicated module for attendance records.
- Can be extended with attendance summaries and reporting.

> Feature availability depends on the current implementation. Refer to the source code for the exact operations supported by each module.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application logic |
| Java Swing | Desktop graphical user interface |
| MySQL | Relational database |
| JDBC | Java-to-MySQL connectivity |
| MySQL Connector/J | JDBC driver |
| Eclipse IDE | Development environment |

## 🧩 Application Modules

| Module | Description |
|---|---|
| `LoginFrame.java` | Login interface |
| `DashboardFrame.java` | Main dashboard and navigation |
| `MemberFrame.java` | Member management interface |
| `PaymentFrame.java` | Payment management interface |
| `AttendanceFrame.java` | Attendance management interface |
| `DBConnection.java` | Database connectivity |
| `GymTheme.java` | Shared colors, fonts, and UI styling |
| `GymManagementSystem.java` | Application entry point |

## 📂 Project Structure

```text
GymManagementSystem/
├── src/
│   └── gymmanagement/
│       ├── GymManagementSystem.java
│       ├── LoginFrame.java
│       ├── DashboardFrame.java
│       ├── MemberFrame.java
│       ├── PaymentFrame.java
│       ├── AttendanceFrame.java
│       ├── DBConnection.java
│       └── GymTheme.java
├── README.md
└── database/
    └── gym_management.sql
```

*This is the intended structure. Adjust the file names and folders to match the actual repository.*

## 📋 Prerequisites

Install the following before running the project:

- Java Development Kit (JDK 8 or a compatible version)
- Eclipse IDE
- MySQL Server
- MySQL Connector/J

Basic familiarity with Java, SQL, and Eclipse is helpful.

## 🚀 Installation and Setup

### 1. Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/KARTHIK-M-J/GymManagementSystem.git
```

### 2. Import into Eclipse

1. Open Eclipse IDE.
2. Select **File → Import**.
3. Choose **General → Existing Projects into Workspace**.
4. Browse to the downloaded project directory.
5. Select the project and click **Finish**.

### 3. Create the Database

Open MySQL Workbench or your preferred MySQL client and execute:

```sql
CREATE DATABASE gym_management;
```

If a database setup script is included in the repository, execute that script to create the required tables.

### 4. Configure MySQL Connectivity

Open `DBConnection.java` and configure the connection URL, username, and password for your local MySQL installation.

Example connection URL:

```text
jdbc:mysql://localhost:3306/gym_management
```

Use your own database credentials. Do not commit real passwords or other secrets to a public repository.

### 5. Add the JDBC Driver

If Eclipse cannot find the MySQL driver:

1. Download the compatible MySQL Connector/J library.
2. Right-click the project in Eclipse.
3. Select **Build Path → Configure Build Path**.
4. Open the **Libraries** tab.
5. Add the Connector/J JAR.
6. Apply the changes.

## ⚙️ Database Configuration

The application uses MySQL for database connectivity.

Before launching the application, ensure that:

- MySQL Server is running.
- The `gym_management` database exists.
- All required tables have been created.
- The connection details in `DBConnection.java` are correct.
- The MySQL JDBC driver is included in the build path.

A database setup script should be included so that other developers can recreate the schema without manually creating each table.

## ▶️ Running the Application

1. Start MySQL Server.
2. Open the project in Eclipse.
3. Verify the database configuration.
4. Locate `GymManagementSystem.java`.
5. Right-click the file.
6. Select **Run As → Java Application**.

The login window should appear if the application and database are configured correctly.

## 🎨 User Interface

The application follows a dark-themed visual style with green accents, designed to provide a consistent appearance across its main screens.

Recommended screenshots to add:

- Login screen
- Main dashboard
- Member management
- Payment management
- Attendance management

Once screenshots have been uploaded to a folder such as `screenshots/`, display them here using their actual file paths. For example:

```markdown
![Dashboard](screenshots/dashboard.png)
```

## 🔮 Future Improvements

Potential enhancements for future versions include:

- [ ] Database-backed login and secure password hashing
- [ ] Member search, editing, and deletion
- [ ] Membership plans and expiry tracking
- [ ] Payment history and outstanding balance reports
- [ ] Attendance summaries and date filters
- [ ] Input validation and clearer error messages
- [ ] Dashboard statistics and reports
- [ ] Database backup and recovery support

## 🔒 Security Notes

- Never publish real database passwords, API keys, or other credentials.
- Use secure password hashing for stored user passwords.
- Validate user input and use prepared SQL statements.
- Keep database credentials outside publicly shared source code when possible.

## 👨‍💻 Author

**KARTHIK M-J**

- GitHub: [@KARTHIK-M-J](https://github.com/KARTHIK-M-J)
- Project: [GymManagementSystem](https://github.com/KARTHIK-M-J/GymManagementSystem)

---

⭐ If you find this project useful, consider starring the repository!

*Built as a practical Java Swing and MySQL project to develop desktop application and database management skills.*
