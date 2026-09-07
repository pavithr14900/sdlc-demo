# README / Setup Guide

## Overview
This application is designed to manage employee leave requests within an organization. It allows employees to submit leave requests, view their leave history, and managers to approve or reject these requests. The core benefits include streamlined leave management, improved transparency, and reduced administrative overhead. The target users are employees and managers within the organization.

## Key Features
- Employees can submit leave requests with details such as leave type, start date, and end date.
- Managers can approve or reject leave requests.
- Employees can view their leave history and current leave balance.

## Prerequisites
- Java 11+
- PostgreSQL 12+
- Node.js 16+
- npm 8+
- Hardware/resource requirements: Minimum 2GB RAM, 1 CPU core

## Project Structure
The repository layout is as follows:
- `backend/`: Contains the backend code, including REST controllers, service classes, and repository interfaces. Key frameworks used are Spring Boot and JPA.
- `frontend/`: Contains the frontend code, including React components for the user interface. Key frameworks used are React and Redux.
- `shared/`: Contains shared utilities and configurations.
- `docs/`: Contains documentation, including API documentation and user guides.

## Installation & Setup

### Backend Setup
1. Clone the repository: `git clone <url>`
2. Navigate to the project: `cd project-name`
3. Create virtual environment: `python -m venv venv`
4. Activate: `source venv/bin/activate` (Linux/Mac) or `venv\Scripts\activate` (Windows)
5. Install dependencies: `pip install -r requirements.txt`
6. Configure PostgreSQL database by setting the following environment variables:
   - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://localhost:5432/leave_management`
   - `SPRING_DATASOURCE_USERNAME`: `your_username`
   - `SPRING_DATASOURCE_PASSWORD`: `your_password`

### Frontend Setup
1. Navigate to frontend: `cd frontend`
2. Install dependencies: `npm install`
3. Build/prepare assets: `npm run build`

## Configuration

### Required Environment Variables
- `JWT_SECRET`: Secret key for JWT token signing, format: string, example value: `your_jwt_secret`
- `JWT_EXPIRATION`: Expiration time for JWT tokens in milliseconds, format: integer, example value: `3600000`

### Optional Environment Variables
- `PORT` (default: `5000`): Port on which the backend server runs, format: integer, effect if changed: server runs on specified port

## Running the Application

### Starting the Backend
1. Ensure virtual environment is activated
2. Set environment variables: `export JWT_SECRET=your_jwt_secret`
3. Start server: `python app.py`
4. Verify: Backend is running on http://localhost:5000

### Starting the Frontend
1. Navigate to frontend directory: `cd frontend`
2. Start dev server: `npm run dev`
3. Open browser: http://localhost:5173 (or displayed URL)
4. Verify: UI loads and connects to backend

## Verification Checklist
- [ ] Backend health check responds at GET /api/health with 200 OK
- [ ] Frontend loads without console errors
- [ ] Basic workflow completes end-to-end (e.g., employee submits a leave request and manager approves it)
- [ ] API endpoints respond with expected data format

## Troubleshooting

### Backend Issues
- **Port already in use**: Change PORT env var or kill process on port 5000
- **Module not found**: Run `pip install -r requirements.txt` again
- **Database connection error**: Verify connection string in config, check service is running
- **Authentication error**: Verify API keys/tokens in.env are correct and not expired

### Frontend Issues
- **Blank page or won't load**: Check browser console for errors, ensure backend is running
- **API connection error**: Verify backend URL in config, check CORS settings
- **Module resolution error**: Delete node_modules and run `npm install` again
- **Build fails**: Ensure Node.js version matches requirements, clear cache: `npm cache clean --force`

## Common Workflows

### Submit a Leave Request
1. Navigate to the leave request form.
2. Fill in the required fields: employee ID, leave type, start date, and end date.
3. Click the "Submit" button.
4. Verify: Leave request is submitted successfully and a confirmation message is displayed.

### Approve a Leave Request
1. Navigate to the leave requests list.
2. Click on the "Approve" button for the leave request to be approved.
3. Verify: Leave request status is updated to "APPROVED" and a confirmation message is displayed.

## Deployment Notes
For production deployment, consider the following:
- Use a secrets management service to store sensitive information such as database credentials and JWT secret.
- Configure load balancing and scaling for the backend server.
- Set up a CI/CD pipeline for automated testing and deployment.

## Getting Help
- Check logs: Backend logs in console, frontend logs in browser DevTools
- Review error message and stack trace for clues
- Check environment variables are set correctly
- Refer to the API Documentation and User Guide for more details
