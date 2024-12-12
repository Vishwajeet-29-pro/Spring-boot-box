# Spring Security Role-Based API

This project is a simple implementation of role-based access control (RBAC) using Spring Security. It provides basic endpoints to demonstrate secure API access for users with `ADMIN` and `USER` roles.

> **Note:** This project is meant as a kick-start example and is not a fully integrated Spring application. Feel free to explore, but it's not intended for production use.

## Features
- **Role-Based Authorization**:
    - `ADMIN` role can access all APIs.
    - `USER` role has restricted access to specific APIs.
- **Endpoints for Managing Users**:
    - Create, update, delete, and retrieve user information.
    - Check if a username exists.

## Endpoints Overview
### Public Endpoints
- `POST /api/v1/register`: Register a new user (if implemented).

### Admin Endpoints
- `GET /api/v1/admin`: Retrieve all users.
- `PUT /api/v1/admin/update/{id}`: Update user details by ID.
- `PATCH /api/v1/admin/update-role/{username}`: Update a user's role.
- `PATCH /api/v1/admin/update-password/{username}`: Reset a user's password.
- `DELETE /api/v1/admin/delete/{id}`: Delete a user by ID.

### User-Specific Endpoints
- `GET /api/v1/user/{username}`: Retrieve user details by username.
- `GET /api/v1/user/check-username/{username}`: Check if a username exists.
