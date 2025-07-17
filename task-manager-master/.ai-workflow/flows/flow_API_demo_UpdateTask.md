# API Flow Template - Teaching AI a Specific API

You are an expert Java Spring developer and code reviewer.  
You are being trained to understand the end-to-end flow of an API in this project.

---

## API Information

- **API Name**: Update Task
- **Purpose**: Update existing task information (title, description, deadline, status, etc.)
- **HTTP Method**: PUT
- **Endpoint**: /tasks/{id}

---

## Related Java Classes

| Layer      | File Path                                           | Description                   |
| ---------- | --------------------------------------------------- | ----------------------------- |
| Controller | `pl.rengreen.taskmanager.controller.TaskController` | Handles incoming PUT request  |
| Service    | `pl.rengreen.taskmanager.service.TaskService`       | Implements business logic     |
| Repository | `pl.rengreen.taskmanager.repository.TaskRepository` | Handles DB access for Task    |
| Model      | `pl.rengreen.taskmanager.model.Task`                | Entity representing Task data |

---

## Code Flow Description

### 1. Controller Layer

```java
@PutMapping("/tasks/{id}")
public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask)
```

- Receives HTTP PUT request with path variable `id` and JSON body `updatedTask`
- Calls service method: `taskService.updateTask(id, updatedTask)`

---

### 2. Service Layer

```java
public Task updateTask(Long id, Task updatedTask)
```

- Fetches task by ID
- Updates fields and saves the task using `taskRepository.save(task)`
- Throws `TaskNotFoundException` if not found

---

### 3. Repository Layer

```java
Optional<Task> findById(Long id);
Task save(Task task);
```

- `findById` retrieves task from DB
- `save` updates task with new values

---

### Example Flow Summary

> `PUT /tasks/{id}` → `TaskController.updateTask(...)` → `TaskService.updateTask(...)` → `TaskRepository.findById` + `save` → DB

---

## Expected Behavior

- On success: Returns `200 OK` with updated `Task` object in body
- On failure: Returns `404 Not Found` if task ID does not exist

---

## Notes

- API follows MVC pattern and Spring best practices
- Follows existing naming conventions in the project
