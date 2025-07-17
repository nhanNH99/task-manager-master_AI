# API Flow Template - Teaching AI a Specific API

You are an expert Java Spring developer and code reviewer.  
You are being trained to understand the end-to-end flow of an API in this project.

This file is a reusable template. Please **replace the placeholder values** (`{{...}}`) before using.

---

## API Information

- **API Name**: `{{API_NAME}}`
- **Purpose**: `{{API_PURPOSE}}`
- **HTTP Method**: `{{HTTP_METHOD}}`
- **Endpoint**: `{{ENDPOINT}}`

---

## Related Java Classes

| Layer      | File Path             | Description                       |
| ---------- | --------------------- | --------------------------------- |
| Controller | `{{CONTROLLER_PATH}}` | Handles incoming HTTP request     |
| Service    | `{{SERVICE_PATH}}`    | Implements business logic         |
| Repository | `{{REPOSITORY_PATH}}` | Handles DB access (if applicable) |
| Model      | `{{MODEL_PATH}}`      | Entity/DTO used                   |

---

## Code Flow Description

### 1. Controller Layer

```java
// {{CONTROLLER_METHOD_NAME}} in {{CONTROLLER_CLASS}}
{{CONTROLLER_METHOD_SIGNATURE}}
```

Explanation:

- Receives HTTP request with parameters: `{{PARAMETERS}}`
- Calls service method: `{{SERVICE_METHOD_NAME}}`

---

### 2. Service Layer

```java
// {{SERVICE_METHOD_NAME}} in {{SERVICE_CLASS}}
{{SERVICE_METHOD_SIGNATURE}}
```

Explanation:

- Contains main business logic for {{API_NAME}}
- Delegates DB operations to `{{REPOSITORY_METHOD_NAME}}`

---

### 3. Repository Layer (if any)

```java
// {{REPOSITORY_METHOD_NAME}} in {{REPOSITORY_CLASS}}
{{REPOSITORY_METHOD_SIGNATURE}}
```

Explanation:

- Interacts with DB to read/write `{{MODEL_NAME}}` entity

---

### Example Flow Summary

> `{{HTTP_METHOD}} {{ENDPOINT}}` → Controller → Service → Repository → DB

---

## Expected Behavior

- On success: `{{RESPONSE_SUCCESS}}`
- On failure: `{{RESPONSE_FAILURE}}`

---

## Notes

- This API follows project coding rules and naming conventions.
- This template can be reused to explain any API flow to the AI/Copilot engine.
