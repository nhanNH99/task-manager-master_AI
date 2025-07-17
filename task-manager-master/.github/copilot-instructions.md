# Copilot Instructions for Task Manager (Spring Boot)

## Project Architecture

- Follows Spring MVC: Controller → Service → Repository layers.
- Domain entities and DTOs are in `model/` and `dto/` respectively.
- REST APIs use DTOs for all external data; entities are not exposed directly.
- Thymeleaf templates for server-rendered views in `resources/templates`.
- Static assets (CSS/JS/images) in `resources/static`.
- JUnit tests mirror main code structure under `src/test/java`.

## Key Conventions

- **Naming:**
  - Classes: PascalCase (e.g., `TaskController`)
  - Methods/variables: camelCase
  - Constants: UPPER_SNAKE_CASE
  - Packages: all lowercase
- **Dependency Injection:** Use constructor injection, never field injection.
- **Annotations:** Use `@Service`, `@Repository`, `@RestController` as appropriate.
- **Validation:** Use `@Valid` and validation annotations for request payloads.
- **Error Handling:** Return `ResponseEntity<?>` with appropriate status codes (`200`, `201`, `400`, `404`).
- **Security:** Endpoints secured via Spring Security; use `@PreAuthorize` or config.

## Developer Workflows

- **Build:** `mvn clean install` (Maven)
- **Run:** `mvn spring-boot:run` or run the JAR in `target/`
- **Test:** `mvn test` (JUnit 5, Mockito for mocking)
- **Database:** Uses H2 in-memory DB for dev/testing.
- **Login:** Demo users in README for quick login.

## Patterns & Examples

- **No business logic in controllers.** Controllers delegate to services.
- **DTOs for all API input/output.** See `NotificationRequest`/`NotificationResponse` in `dto/`.
- **Pagination:** Use `Pageable` and Spring Data conventions for paginated endpoints.
- **Exception Handling:** Throw `EntityNotFoundException` for missing resources; consider a global handler.
- **Feature Placement:**
  - New features (e.g., Notification) require Entity, Repository, Service, Controller, DTOs in correct packages.
  - Follow existing structure for new code (see `.ai-workflow/templates/project_structure.md`).

## Integration Points

- **Spring Security:** All user/task/notification APIs are secured.
- **Thymeleaf:** Used for HTML views; REST APIs use JSON DTOs.
- **External:** No external APIs by default; see README for inspiration sources.

## Example: Notification Feature

- Entity: `model/Notification.java`
- DTOs: `dto/NotificationRequest.java`, `dto/NotificationResponse.java`
- Repository: `repository/NotificationRepository.java`
- Service: `service/NotificationService.java`, `service/NotificationServiceImpl.java`
- Controller: `controller/NotificationController.java`

## References

- See `.ai-workflow/rules/coding_rules.md` for full coding standards.
- See `.ai-workflow/templates/project_structure.md` for directory/package map.
- See `README.md` for app overview and demo users.

---

For questions or unclear patterns, review the above files or ask for clarification from maintainers.
