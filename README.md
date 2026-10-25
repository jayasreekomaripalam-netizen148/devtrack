# DevTrack — Issue Tracking MVP

DevTrack is a lightweight full-stack issue tracker built with Java and Spring Boot. It helps a developer create tasks, assign priorities, track statuses, and review project work from a responsive dashboard.

## Features
- Create and list issues
- Set issue priority: LOW, MEDIUM, HIGH, CRITICAL
- Track status: TODO, IN_PROGRESS, DONE
- Update issue status and delete issues from the dashboard
- REST API for create, read, update, and delete
- Filter API results by status or priority
- Request validation and structured error responses
- Persistent local H2 database; JPA model can be configured for PostgreSQL
- Responsive Bootstrap UI

## Tech stack
- Java 17
- Spring Boot 3
- Spring Web, Thymeleaf, Spring Data JPA, Jakarta Validation
- H2 database for zero-setup local demo
- Maven, JUnit 5, Bootstrap 5

## Run locally
Requirements: Java 17+.

```bash
mvn spring-boot:run
```

This starter archive does not include Maven Wrapper binaries. Use a cloud IDE with Maven installed, or generate the wrapper with `mvn -N wrapper:wrapper`.

Open http://localhost:8080.

The app uses a file-backed H2 database in `./data/`, so demo data persists across application restarts. H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:file:./data/devtrack`, user `sa`, empty password). Do not expose the H2 console publicly.

## REST API
Base URL: `/api/issues`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/issues` | List issues |
| GET | `/api/issues/{id}` | Get one issue |
| POST | `/api/issues` | Create issue |
| PUT | `/api/issues/{id}` | Update issue |
| DELETE | `/api/issues/{id}` | Delete issue |
| GET | `/api/issues?status=TODO` | Filter by status |
| GET | `/api/issues?priority=HIGH` | Filter by priority |

Example request:
```json
{
  "title": "Fix login validation",
  "description": "Reject invalid login input",
  "status": "TODO",
  "priority": "HIGH"
}
```

## Test
```bash
mvn test
```

## Architecture
Browser (Thymeleaf/Bootstrap) → Controller → Service → Repository → Database. REST API endpoints are exposed separately under `/api/issues`.

## Limitations and next steps
This is an MVP intended for learning and portfolio demonstration, not a production-ready multi-user system. Authentication, project ownership, comments, pagination, database migrations, CI, and deployment configuration are future improvements. Do not store real private data in this demo.

## License
MIT — see `LICENSE`.
