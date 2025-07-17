# AI Project Structure Context

You are an experienced Software Architect with deep expertise in Java and the Spring Framework.  
You are being trained to assist in developing features for a Spring-based project.

Below is the folder and package structure of our project.  
Please study this to understand the organization and placement of key components.

---

## Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── pl/
│   │       └── rengreen/
│   │           └── taskmanager/
│   │               ├── configuration/
│   │               ├── controller/
│   │               ├── dataloader/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── flatly/
│       │   ├── js/
│       │   └── images/
│       └── templates/
│           ├── forms/
│           ├── fragments/
│           └── views/
├── test/
│   └── java/
│       └── pl/
│           └── rengreen/
│               └── taskmanager/
│                   ├── controller/
│                   └── service/
```

---

## Architectural Principles

- Project follows **Spring MVC** pattern.
- Code is modularized into **Controller → Service → Repository** layers.
- Models in `model/` represent JPA entities and DTOs.
- Static web content lives under `resources/static` (CSS/JS/images).
- Server-rendered views are Thymeleaf templates in `resources/templates`.
- JUnit test classes follow the same structure under `src/test/java`.

---

## Key Directories Explained

| Layer         | Path                                              | Responsibility                                        |
| ------------- | ------------------------------------------------- | ----------------------------------------------------- |
| Configuration | `configuration/`                                  | Spring configuration (e.g. WebConfig, SecurityConfig) |
| Controller    | `controller/`                                     | Handles web requests (REST + Thymeleaf)               |
| Service       | `service/`                                        | Business logic                                        |
| Repository    | `repository/`                                     | Spring Data JPA Repos                                 |
| Model         | `model/`                                          | Entities + DTOs                                       |
| DataLoader    | `dataloader/`                                     | DB seeders or init data classes                       |
| Templates     | `resources/templates/forms`, `views`, `fragments` | Thymeleaf views                                       |
| Static files  | `resources/static/css`, `js`, `images`            | Front-end assets                                      |
| Unit Tests    | `src/test/java/pl/rengreen/taskmanager/...`       | Controller and service test cases                     |

---

## Your Goal

- Use this structure as a **map** for placing all new code.
- When given a feature design file, you will:
  - Generate code based on it.
  - Place classes in correct packages.
  - Apply naming conventions already used in this repo.
  - Reuse utilities or services where possible.
- Write modular, idiomatic Java that conforms to the architecture.

---

Please confirm that you've read and understood the structure. Awaiting next step.
