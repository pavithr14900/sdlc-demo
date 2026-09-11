# README / Setup Guide

## Overview
This application is designed to manage employee leave requests within an organization. It allows employees to submit leave applications, view their leave balances, and managers to approve or reject leave requests. The core benefits include streamlined leave management, improved transparency, and reduced administrative overhead.

## Key Features
- Employees can submit leave applications with details such as leave type, start date, and end date.
- Employees can view their leave balances and the status of their leave applications.
- Managers can approve or reject leave applications, ensuring a controlled workflow.

## Prerequisites
- Java 11+
- PostgreSQL 13+
- Node.js 16+
- npm 8+
- Docker (optional for database setup)

## Project Structure
The repository layout is as follows:
- backend/: Contains the Spring Boot application with REST controllers, service classes, and repositories.
- frontend/: Contains the React application with components, services, and API calls.
- shared/: Contains shared utilities and configurations.
- docs/: Contains API documentation and user guides.

## Installation & Setup

### Backend Setup
1. Clone the repository: `git clone <url>`
2. Navigate to the project: `cd employee-leave-application`
3. Create virtual environment: `mvn clean install`
4. Start PostgreSQL server using Docker: `docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres`
5. Set environment variables in `src/main/resources/application.properties`:
   - `spring.datasource.url=jdbc:postgresql://localhost:5432/leave_db`
   - `spring.datasource.username=postgres`
   - `spring.datasource.password=mysecretpassword`
6. Start the backend server: `mvn spring-boot:run`

### Frontend Setup
1. Navigate to frontend: `cd frontend`
2. Install dependencies: `npm install`
3. Build/prepare assets: `npm run build`
4. Start the frontend server: `npm start`
5. Open browser: http://localhost:3000

## Configuration

### Required Environment Variables
- `SPRING_DATASOURCE_URL`: The URL of the PostgreSQL database.
- `SPRING_DATASOURCE_USERNAME`: The username for the PostgreSQL database.
- `SPRING_DATASOURCE_PASSWORD`: The password for the PostgreSQL database.

### Optional Environment Variables
- `JWT_SECRET` (default: `my-jwt-secret`): The secret key used for JWT token signing.
- `JWT_EXPIRATION` (default: `3600000`): The expiration time of JWT tokens in milliseconds.

## Running the Application

### Starting the Backend
1. Ensure PostgreSQL server is running.
2. Set environment variables in `src/main/resources/application.properties`.
3. Start the backend server: `mvn spring-boot:run`
4. Verify: Backend is running on http://localhost:8080

### Starting the Frontend
1. Navigate to frontend directory: `cd frontend`
2. Start the frontend server: `npm start`
3. Open browser: http://localhost:3000
4. Verify: UI loads and connects to backend

## Verification Checklist
- [ ] Backend health check responds at GET /api/health with 200 OK
- [ ] Frontend loads without console errors
- [ ] Basic workflow completes end-to-end (e.g., employee submits a leave application and views its status)
- [ ] API endpoints respond with expected data format

## Troubleshooting

### Backend Issues
- **Port already in use**: Change PORT env var or kill process on port 8080
- **Module not found**: Run `mvn clean install` again
- **Database connection error**: Verify connection string in `application.properties`, check PostgreSQL service is running
- **Authentication error**: Verify JWT secret and expiration in `application.properties`

### Frontend Issues
- **Blank page or won't load**: Check browser console for errors, ensure backend is running
- **API connection error**: Verify backend URL in config, check CORS settings
- **Module resolution error**: Delete `node_modules` and run `npm install` again
- **Build fails**: Ensure Node.js version matches requirements, clear cache: `npm cache clean --force`

## Common Workflows

### Submit Leave Application
1. Navigate to the leave application page.
2. Fill in the leave details (employee ID, leave type, start date, end date).
3. Click 'Submit'.
4. Verify: Leave application is submitted and status is 'PENDING'.

### Approve Leave Application
1. Navigate to the leave application details page.
2. Click 'Approve'.
3. Verify: Leave application status is updated to 'APPROVED'.

## Deployment Notes
For production deployment, ensure to:
- Configure environment variables securely.
- Use a production-ready database.
- Implement SSL for secure communication.
- Set up a reverse proxy (e.g., Nginx) for load balancing and SSL termination.

## Getting Help
- Check logs: Backend logs in console, frontend logs in browser DevTools
- Review error message and stack trace for clues
- Check environment variables are set correctly
- Refer to the API Documentation and User Guide for more details
