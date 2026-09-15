# Release Notes

## Version 1.0.0 - Initial Release
**Release Date**: 2023-10-01
**Status**: Stable

### Summary
This release introduces the Enterprise Procurement and Inventory Management Platform for a retail organization operating across India. The platform manages the complete procurement lifecycle from identifying purchasing needs through supplier selection, purchase approval, delivery, inventory updates, invoice verification, and payment reconciliation. It supports multiple user roles, configurable approval workflows, budget controls, and integrates with external systems for a seamless procurement experience.

### New Features
- **Users and Access**: Support for multiple user roles with role-based access control. Users cannot approve their own requests.
- **Purchase Requisitions**: Ability to create, save, submit, and track purchase requisitions with multiple products, quantities, and delivery details.
- **Configurable Approval Workflows**: Approval workflows based on requisition value, with escalation rules and budget-exception approvals.

### Core Capabilities Delivered
- **Organisation and Master Data**: Management of legal entities, regions, stores, warehouses, cost centres, financial years, product categories, products, units of measure, suppliers, and category budgets.
- **Budget Controls**: Tracking of allocated, reserved, committed, spent, and available budget by cost centre, category, and financial year.
- **Supplier Onboarding and Quotation Management**: Supplier registration, quotation requests, and comparison.

### Improvements & Polish
- **UI Refinement**: Improved user interface for better navigation and usability.
- **Performance**: Optimized database queries for faster response times.

### Known Issues & Limitations
- **Limited Supplier Performance Metrics**: Only basic performance scores are implemented. Will be expanded in future releases.
- **Performance**: Handles up to 2,000 concurrent users and 50,000 requisitions per month.

### Breaking Changes
- **None**

### Compatibility
- Requires: Java 21, Spring Boot, PostgreSQL, AWS
- Tested on: Windows 10, macOS, Linux, Chrome, Firefox, Edge

### Upgrade Notes
To deploy this release:
1. Back up the database
2. Pull the latest code: `git pull origin main`
3. Install/update dependencies: `pip install -r requirements.txt && npm install`
4. Run migrations if any: `python migrate.py`
5. Restart services
6. Verify health: `curl http://localhost:5000/api/health`

### Contributors
- **Team A**: Backend development and API design
- **Team B**: Frontend development and UI design

### What's Next

#### Planned for Version 1.1
- **Advanced Supplier Performance Metrics**: Implementation of detailed supplier performance metrics.
- **Enhanced Reporting**: Additional reporting features and filters.

#### Future Roadmap
- **Mobile App**: Development of a mobile application for on-the-go procurement.
- **Integration with ERP**: Deeper integration with the organization's existing ERP system.

### Feedback & Support
- Report issues: [GitHub Issues](https://github.com/org/repo/issues)
- Request features: [GitHub Issues](https://github.com/org/repo/issues)
- Documentation: See README.md, User Guide, and API Documentation
