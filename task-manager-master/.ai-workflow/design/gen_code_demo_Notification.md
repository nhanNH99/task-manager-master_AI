# Code Generation Prompt - Notification Feature

You are a **Senior Java Developer** familiar with the current Spring Boot codebase structure.  
You will assist in generating backend code (Entity, Repository, Service, Controller, DTO, etc.)  
that aligns with the architectural and coding conventions of this project.

## Feature Overview

- **Feature Name**: Notification
- **Type**: New Feature
- **Business Context**: Users should receive timely notifications related to tasks, such as reminders, updates, and overdue warnings.

---

## Code Scope and Requirements

- **Target API(s)**:
  - Method: POST, GET
  - Endpoint: `/api/notifications`, `/api/notifications/{id}`
- **Involved Entity or Domain Object(s)**: Notification, User, Task
- **Create or Modify**:
  - [x] Entity
  - [x] Repository
  - [x] Service
  - [x] Controller
  - [x] Request / Response DTOs
  - [ ] Mapper (if any)

---

## Code Guidelines

- Follow package conventions as analyzed from `project_structure.md`
- Follow naming conventions and structure used in existing codebase
- Refer to and reuse logic from existing services/utilities where applicable
- Follow the rules defined in `coding_rules.md`

---

## Additional Notes

- Notifications can be one-time or recurring (daily reminder at 8 AM)
- Include support for pagination in `GET /api/notifications`
- Add exception handling if a user ID or task ID does not exist
- Provide response DTO with read/unread status

---

## Expected Output

AI should return clean, production-quality Java Spring code that:

- Follows current architecture
- Is ready to copy into project with minimal edits
- Includes all defined components (Entity, Service, Controller, etc.)
