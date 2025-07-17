# Refactor Prompt Template

You are a Senior Java Developer familiar with the current Spring Boot codebase structure.

## Context

- **Project Package Root**: `pl.rengreen.taskmanager`
- **Affected Layer(s)**: [Controller | Service | Repository | Model | Util]
- **File(s) to Refactor**:
  - `src/main/java/pl/rengreen/taskmanager/service/NotificationService.java`
  - `src/main/java/pl/rengreen/taskmanager/controller/NotificationController.java`

## Refactor Objectives

- **Reason for Refactor**:
  - [ ] Business logic has changed
  - [ ] Does not follow coding convention
  - [ ] Code is not maintainable / has duplication
  - [ ] Bug or incorrect behavior
  - [x] Improve readability and follow clean code practice
  - [x] Apply consistent naming and structure

## Refactor Instructions

```java
// BEFORE: method name not clear
public void handleNotify(Task task) {
   // logic
}

// AFTER: expected to rename and extract into smaller methods
public void sendTaskDueNotification(Task task) {
   // improved and modular logic
}
```

```java
// BEFORE: direct DB call in controller
Notification notif = notificationRepository.findById(id).get();

// AFTER: should delegate to service layer
Notification notif = notificationService.getById(id);
```

## Reference Rules

- Follow architecture and naming conventions described in `project_structure.md` and `coding_rules.md`
- Ensure logic is modular and covered by unit tests if possible
