# Code Generation Template - gen_code_template.md

> **Role Setup**
>
> You are a **Senior Java Developer** familiar with the current Spring Boot codebase structure.  
> You will assist in generating backend code (Entity, Repository, Service, Controller, DTO, etc.)  
> that aligns with the architectural and coding conventions of this project.

---

This is a template prompt file designed to help developers instruct AI (GitHub Copilot or ChatGPT)  
to generate new backend code (Entity, Repository, Service, Controller, DTO, etc.) for a feature in this project.

## Instructions for Use:

Replace the placeholder fields (`{{...}}`) below with the actual values before prompting the AI.

---

## Feature Overview

- **Feature Name**: {{Feature name (e.g., Notification)}}
- **Type**: {{New Feature | Modify Existing Feature}}
- **Business Context**: {{Explain why this feature is needed, and its purpose}}

---

## Code Scope and Requirements

- **Target API(s)**:
  - Method: {{HTTP method (GET, POST, PUT, DELETE)}}
  - Endpoint: {{/api/feature/...}}
- **Involved Entity or Domain Object(s)**: {{List the domain models involved}}
- **Create or Modify**:
  - [ ] Entity
  - [ ] Repository
  - [ ] Service
  - [ ] Controller
  - [ ] Request / Response DTOs
  - [ ] Mapper (if any)

---

## Code Guidelines

- Follow package conventions as analyzed from `project_structure.md`
- Follow naming conventions and structure used in existing codebase
- Refer to and reuse logic from existing services/utilities where applicable
- Follow the rules defined in `coding_rules.md`

---

## Additional Notes

- Add JavaDoc or inline comments to explain complex logic
- Make sure exception handling is consistent with existing project style
- If assumptions are needed, clearly state them in comments

---

## Expected Output

AI should return clean, production-quality Java Spring code that:

- Follows current architecture
- Is ready to copy into project with minimal edits
- Includes all defined components (Entity, Service, Controller, etc.)
