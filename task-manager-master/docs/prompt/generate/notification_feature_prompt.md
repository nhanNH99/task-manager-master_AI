# Generate code for notification feature

## Role: Expert Java Spring developer

You are an expert Java Spring developer responsible for generating **production-quality backend code** for the **Notification** feature.  
All generated code must follow the architectural principles and coding conventions of the existing project.  
You must also follow the **style and structure demonstrated in existing code examples** that provided earlier.

## Reference Documents

- Important: You must strictly follow the guidelines from the following documents:
  - `project_structure.md`: Defines package organization and layering
  - `coding_rules.md`: Coding standards, naming conventions, annotations, API structure, etc.
  - `get_list_notification_api.md`, `create_notification_api.md`, `mark_notification_as_read_api.md`, `delete_notification_api.md`: API endpoint naming and purpose
  - `new_notification_design.md`: Feature-specific requirements and design specification

---

## Your Task

- 1. Analyze the design document (`new_notification_design.md`)

  - Read and understand `new_notification_design.md` thoroughly.
  - Identify:
    - Required classes and their responsibilities (Controller, Service, Repository, etc.)
    - API request/response structure
    - Business logic and flow
    - Edge cases and exception handling requirements

- 2. Generate Code Based on Design & Conventions

  - Review the existing codebase structure and align all generated code accordingly.
  - Base on library of project on `pom.xml`
  - Write concise, modular, and idiomatic Java code.
  - Follow proper **package structure**, **naming conventions**, and **Java/Spring best practices**.
  - Reuse any existing logic, utility classes, constants, or annotations when possible.
  - If similar logic already exists, reference and invoke it instead of duplicating.
  - Generate comments based on the coding convention of the project

- 3. Integrate Thymeleaf-based Notification View

  - Study how the view layer is handled using Thymeleaf:

    - Understand how `.html` files are placed inside `src/main/resources/templates`
    - Review the structure and naming of view files related to notifications (e.g., `notification_list.html`, `notification_detail.html`, etc.)
    - Analyze how the controller returns view names and passes data to Thymeleaf via the `Model`
    - Ensure links to static resources like CSS/JS/images from `src/main/resources/static` are correct

  - **Ensure full UI consistency and reusability**:

    - Follow the same layout structure, component fragments, and HTML/CSS class naming conventions as existing Thymeleaf pages
    - Reuse shared fragments like headers, footers, sidebars (`fragments/*.html`) if available
    - Match the style, spacing, and responsiveness rules (e.g., Bootstrap or custom CSS usage)
    - Reuse JavaScript behavior or existing functions where applicable (e.g., toast, modal, AJAX)
    - Ensure consistent UX for actions like “Mark as Read”, “View Detail”, “Redirect”, etc.

  - Generate Thymeleaf view files (if needed) to support:

    - Listing notifications
    - Viewing details of a notification
    - Marking notifications as read (via button or link)

  - Ensure proper integration between backend logic and frontend view rendering
