# Cost Split KMP

Compose Multiplatform client foundation for a cost-splitting backend. The project uses vertical feature modules and Clean Architecture inside each feature.

## Modules

- `build-logic`: focused Gradle convention plugins shared by all modules.
- `composeApp`: Android, iOS, and desktop entry points.
- `core:common`: shared MVI and error contracts.
- `core:network`: Ktor setup and platform HTTP engines.
- `feature:expenses`: expense data, domain, presentation, and empty Compose UI.

Each feature owns its layers instead of grouping all features into global `data`, `domain`, and `presentation` modules.

The presentation foundation uses `BaseMviViewModel<Intent, State, Effect>` with a delegated `MviDelegate`. Feature ViewModels handle intents and can update state or emit one-off effects without owning coroutine flow plumbing.

## Build conventions

The included `build-logic` build keeps module scripts declarative:

- `costsplit.kmp.library`: shared Android, desktop, and iOS library targets.
- `costsplit.kmp.application`: shared targets and Android application defaults.
- `costsplit.compose.multiplatform`: Compose Multiplatform and Compose compiler plugins.
- `costsplit.kotlin.serialization`: Kotlin serialization support.

SDK and toolchain policy is centralized, while namespaces, application identity, dependencies, and packaging remain explicit in the owning module.

## Backend configuration

Change `BASE_URL` in `composeApp/build.gradle.kts` for the Spring Boot server. Android emulator defaults to `http://10.0.2.2:8080/`; desktop and iOS default to `http://localhost:8080/`.

The client is wired to the Spring Boot backend contract in `cost-split-backend`:

- `POST /api/v1/users`
- `GET /api/v1/users`
- `GET /api/v1/users/{userId}`
- `POST /api/v1/groups`
- `GET /api/v1/groups/{groupId}`
- `POST /api/v1/groups/{groupId}/members`
- `POST /api/v1/groups/{groupId}/expenses`
- `GET /api/v1/groups/{groupId}/expenses`
- `GET /api/v1/expenses/{expenseId}`
- `GET /api/v1/groups/{groupId}/balances`

Backend UUID, instant, and decimal money values are represented as shared Kotlin strings in the KMP wire/domain models.

## Run

Generate or add a Gradle wrapper, then use:

```shell
./gradlew :composeApp:run
./gradlew :feature:expenses:allTests
```
