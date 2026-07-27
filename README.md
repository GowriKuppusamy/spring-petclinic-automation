# spring-petclinic-automation

UI and API automation framework for the **Spring PetClinic** app using:

- Java 17
- Playwright Java
- TestNG
- Page Object Model (POM)
- Allure Reporting

## Requirements
- Java 17+
- Maven 3.8+

> The Spring PetClinic application must be running before executing tests.

## Configuration
Default config file: `src/test/resources/config.properties`

- `baseUrl=http://localhost:8080` (default)
- `bhYXT=true`
- `browser=chromium`
- `timeoutMs=30000`

Override with either:

**Env vars** (prefix: `PETCLINIC_`)
- `PETCLINIC_BASEURL=http://localhost:8080`
- `PETCLINIC_HEADLESS=false`

**Java system properties**
- `-DbaseUrl=http://localhost:8080`

## Run tests
```bash
mvn clean test
```

Allure report:

```bash
mvn allure:report
mvn allure:serve
```

## Verified test coverage (from app code)
- UI: `/` Welcome page smoke
- UI: `/owners/find` search non-existing owner (shows error)
- UI: `/vets.html` VWs list smoke
- API: `GET /vets` returns 200
