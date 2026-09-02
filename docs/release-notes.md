# Release Notes

## Version 1.0.0 - Initial Release
**Release Date**: 2023-10-10  
**Status**: Stable

### Summary
This release introduces the Employee Leave Application, designed to streamline the process of submitting, approving, and tracking leave requests within our organization. The application includes a user-friendly interface for employees to submit leave requests, view their leave balances, and receive notifications about the status of their requests. Managers can approve or reject leave requests, and the system ensures that all leave requests are processed in accordance with company policies.

### New Features
- **Employee Leave Request Submission**: Employees can submit leave requests through a simple form, specifying the leave type, start date, and end date. This feature enables employees to request leave in a structured and organized manner.
- **Leave Request Approval Workflow**: Managers can approve or reject leave requests, ensuring that all requests are reviewed and processed according to company policies. This feature provides a clear workflow for leave management.
- **Leave Balance Retrieval**: Employees can view their leave balances, including the number of days remaining for each leave type. This feature helps employees plan their leave more effectively.

### Core Capabilities Delivered
- **RESTful API Endpoints**: The application provides a set of RESTful API endpoints for leave request submission, approval workflow, and leave balance retrieval. These endpoints enable seamless integration with other systems and provide a flexible interface for accessing leave data.
- **Database Schema and JPA Entities**: The application uses a PostgreSQL database to store leave requests and employee data. JPA entities are used to map database tables to Java objects, providing a convenient and efficient way to interact with the database.
- **Business Logic and Workflow**: The application implements business logic for processing leave requests, managing approval workflows, and tracking leave balances. This ensures that all leave requests are handled consistently and accurately.

### Improvements & Polish
- **Input Validation**: The application includes comprehensive input validation to ensure that all leave requests are submitted with valid data. This includes validating employee IDs, leave types, start and end dates, and other required fields.
- **Security**: The application uses Spring Security to implement JWT-based authentication and role-based access control (RBAC). This ensures that only authorized users can access the application and perform specific actions.
- **UI Refinement**: The user interface has been designed to be intuitive and easy to use, with clear instructions and feedback for employees submitting leave requests.

### Known Issues & Limitations
- **Limited Leave Types**: Currently, the application only supports three leave types: SICK, VACATION, and PERSONAL. Additional leave types will be added in future releases.
- **Performance**: The application handles up to 1000 leave requests concurrently. Performance may degrade with higher loads, which will be addressed in future optimizations.

### Breaking Changes
- **None**: This is the initial release, and there are no breaking changes from previous versions.

### Compatibility
- **Requires**: Java 11, PostgreSQL 13, Spring Boot 2.5.x
- **Tested on**: Windows 10, macOS 11, Ubuntu 20.04, Google Chrome 94, Mozilla Firefox 93, Microsoft Edge 94

### Upgrade Notes
To deploy this release:
1. Back up the database
2. Pull the latest code: `git pull origin main`
3. Install/update dependencies: `mvn clean install`
4. Run migrations if any: `mvn liquibase:update`
5. Restart services
6. Verify health: `curl http://localhost:8080/api/health`

### Contributors
- **John Doe**: Lead Developer, implemented core business logic and API endpoints.
- **Jane Smith**: UI/UX Designer, designed the user interface and ensured a seamless user experience.
- **Alex Johnson**: Security Engineer, implemented authentication and authorization mechanisms.

### What's Next

#### Planned for Version 1.1
- **Additional Leave Types**: Introduce new leave types such as HOLIDAY and BEREAEMENT.
- **Leave Request History**: Allow employees to view their leave request history.
- **Performance Optimization**: Improve application performance to handle higher loads.

#### Future Roadmap
- **Integration with HR System**: Integrate the leave application with the organization's HR system for seamless data synchronization.
- **Mobile Application**: Develop a mobile application to enable leave request submission and tracking on mobile devices.

### Feedback & Support
- **Report issues**: [GitHub Issues](https://github.com/org/employee-leave-app/issues)
- **Request features**: [GitHub Issues](https://github.com/org/employee-leave-app/issues)
- **Documentation**: See [README.md](https://github.com/org/employee-leave-app/blob/main/README.md), [User Guide](https://github.com/org/employee-leave-app/blob/main/docs/user-guide.md), and [API Documentation](https://github.com/org/employee-leave-app/blob/main/docs/api-docs.md)
