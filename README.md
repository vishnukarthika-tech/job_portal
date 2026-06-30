The Smart Job Portal System is a console-based Java application designed to simulate a real-world job recruitment platform. It enables users to register, log in, browse job listings, apply for jobs, and allows administrators to manage job postings efficiently. The system uses JDBC (Java Database Connectivity) to interact with a MySQL database for persistent data storage.

🚀 Key Features
User Management
User registration with name, email, password, and role
Secure login authentication using database validation
Job Management
Add new job postings with title, location, salary, and company details
View all available job listings
Update existing job details
Delete job postings
Job Application System
Users can apply for jobs using user ID and job ID
Application status tracking (e.g., "Applied")
Database Integration
MySQL database (smart_job_portal1) used for storing:
Users
Jobs
Applications
JDBC used for executing SQL operations (INSERT, SELECT, UPDATE, DELETE)
🏗️ Technology Stack
Programming Language: Java
Database: MySQL
Connectivity: JDBC
Concepts Used:
Object-Oriented Programming (OOP)
Exception Handling
Collections Framework (List for skills)
CRUD Operations
Layered architecture (Main → DatabaseManager → Models)
📌 Project Structure
Main.java → Handles user interaction and menu system
User.java → Represents user data
Job.java → Represents job details
Application.java → Represents job applications
DatabaseManager.java → Handles all database operations
🎯 Objective

The main objective of this project is to provide a simple yet functional recruitment system that demonstrates how real-world job portals manage users, job postings, and applications using backend database operations.
