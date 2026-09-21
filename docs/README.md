# README / Setup Guide

## Overview
The Employee Expense & Corporate Travel Management Platform is designed to streamline the process of submitting, approving, and tracking employee expenses and travel requests. This platform benefits employees by simplifying expense and travel request submissions, managers by providing an efficient approval workflow, and finance teams by ensuring policy compliance and generating insightful reports. The target users include employees, managers, and finance personnel within an organization.

## Key Features
- Employees can submit expenses and travel requests, upload receipts, and track reimbursement status.
- Managers can approve or reject expenses and travel requests with comments.
- Finance personnel can validate policy compliance and generate department-wise spend, policy violations, outstanding reimbursements, and monthly cost reports.
- The system includes configurable approval thresholds, duplicate-claim detection, audit history, notifications, role-based access control (RBAC), and dashboards.

## Prerequisites
- Java 11 or higher
- Node.js 16+
- PostgreSQL 13+
- Docker (for local development)
- Basic understanding of Spring Boot and React

## Project Structure
The repository layout follows a standard microservices architecture with a backend and frontend.
- backend/: Contains the Spring Boot application with RESTful APIs, business logic, and data access layers.
- frontend/: Contains the React application for the user interface.
- shared/: Contains shared utilities and configurations.
- docs/: Contains API documentation and user guides.

## Installation & Setup

### Backend Setup
1. Clone the repository: `git clone <url>`
2. Navigate to the project: `cd project-name`
3. Create virtual environment: `python -m venv venv`
4. Activate: `source venv/bin/activate` (Linux/Mac) or `venv\Scripts\activate` (Windows)
5. Install dependencies: `pip install -r requirements.txt`
6. Set up PostgreSQL database: `docker run --name=postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres`
7. Create database: `createdb expense_db`
8. Run migrations: `python manage.py migrate`

### Frontend Setup
1. Navigate to frontend: `cd frontend`
2. Install dependencies: `npm install`
3. Build/prepare assets: `npm run build`

## Configuration

### Required Environment Variables
- `SPRING_DATASOURCE_URL`: Controls the database connection URL, format `jdbc:postgresql://localhost:5432/expense_db`, example value `jdbc:postgresql://localhost:5432/expense_db`.
- `SPRING_DATASOURCE_USERNAME`: Controls the database username, format `username`, example value `postgres`.
- `SPRING_DATASOURCE_PASSWORD`: Controls the database password, format `password`, example value `mysecretpassword`.

### Optional Environment Variables
- `JWT_SECRET` (default: `secret`): Controls the JWT secret key used for authentication.

## Running the Application

### Starting the Backend
1. Ensure virtual environment is activated
2. Set environment variables: `export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/expense_db`
3. Start server: `python app.py`
4. Verify: Backend is running on http://localhost:8080

### Starting the Frontend
1. Navigate to frontend directory: `cd frontend`
2. Start dev server: `npm run dev`
3. Open browser: http://localhost:3000 (or displayed URL)
4. Verify: UI loads and connects to backend

## Verification Checklist
- [ ] Backend health check responds at GET /api/health with 200 OK
- [ ] Frontend loads without console errors
- [ ] Basic workflow completes end-to-end (e.g., employee submits an expense, manager approves it)
- [ ] API endpoints respond with expected data format

## Troubleshooting

### Backend Issues
- **Port already in use**: Change PORT env var or kill process on port 8080
- **Module not found**: Run `pip install -r requirements.txt` again
- **Database connection error**: Verify connection string in config, check service is running
- **Authentication error**: Verify JWT secret in.env is correct and not expired

### Frontend Issues
- **Blank page or won't load**: Check browser console for errors, ensure backend is running
- **API connection error**: Verify backend URL in config, check CORS settings
- **Module resolution error**: Delete node_modules and run `npm install` again
- **Build fails**: Ensure Node.js version matches requirements, clear cache: `npm cache clean --force`

## Common Workflows

### Employee Submits Expense
1. Log in as an employee
2. Navigate to the "Submit Expense" page
3. Fill in the expense details and upload a receipt
4. Submit the expense
5. Verify: Expense is submitted and appears in the "My Expenses" section

### Manager Approves Expense
1. Log in as a manager
2. Navigate to the "Pending Expenses" section
3. Select an expense to approve
4. Provide approval comments and submit
5. Verify: Expense status changes to "Approved" and appears in the "Approved Expenses" section

## Deployment Notes
- Ensure environment variables are set correctly in the production environment.
- Use a secure method for managing secrets, such as HashiCorp Vault or AWS Secrets Manager.
- Configure load balancers and auto-scaling for high availability.

## Getting Help
- Check logs: Backend logs in console, frontend logs in browser DevTools
- Review error message and stack trace for clues
- Check environment variables are set correctly
- Refer to the API Documentation and User Guide for more details
