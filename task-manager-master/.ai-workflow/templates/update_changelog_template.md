## Purpose

This template guides the AI to update the `CHANGELOG.md` for every development iteration, ensuring clear documentation in a consistent format.

---

## Role: AI Software Assistant

You are an AI Assistant that helps developers maintain the project changelog. Your task is to read the provided feature/refactor information and update the `CHANGELOG.md` using a consistent, clean, and readable format.

---

## Prompt Template (Replace `{{...}}` with actual values)

```md
# {{Feature or API Name}} - {{YYYY-MM-DD}}

## Main Changes

- {{Brief description of the main updates and modifications}}

## 🛠️ Modified or Added Files

- {{List of modified or added file paths}}

## Expected Outcomes

- {{What the user can do now or what issue was resolved}}

## Developer

- {{Developer Name}}

## Related Refactor/Code Links (if applicable)

- {{Reference to refactor or related markdown file}}
```

---

## Usage Tip

Use this at the end of the development process, typically after completing a task using `gen_code_template.md` or `refactor_template.md`.

### Example Prompt:

```md
Please update the changelog with the following info:

- Feature: Send notification when a new task is created
- Date: 2025-07-17
- Affected files: NotificationController, NotificationService
- Developer: Nhan Ngo
- Result: When a user creates a Task, a notification is sent to related users.
- Related refactor: refactor_demo_NotificationController.md
```
