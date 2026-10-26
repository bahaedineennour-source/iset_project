# University Courses

Simple web application for university students to browse subjects (matières), chapters and lessons. There is no login and no user account: anyone who opens the app can read all courses.

## Project structure

```
my-app/
├── frontend/     Angular (TypeScript) user interface
├── backend/      Spring Boot REST API
└── README.md
```

## Requirements

- Java 21
- Node.js 22 (or a recent Node 20+)
- PostgreSQL
- npm

The backend already includes Spring Data JPA, Hibernate and the PostgreSQL driver. The frontend is an existing Angular 22 project.

## 1. Configure PostgreSQL

Create a database named `university_courses`.

With `psql`:

```sql
CREATE DATABASE university_courses;
```

On Windows, you can also use pgAdmin.

Connection settings are read from environment variables, with these defaults:

| Variable        | Default value                                         |
|-----------------|-------------------------------------------------------|
| `DB_URL`        | `jdbc:postgresql://localhost:5432/university_courses` |
| `DB_USERNAME`   | `postgres`                                            |
| `DB_PASSWORD`   | `postgres`                                            |

Do not commit a real password. To store a local password, copy:

`backend/src/main/resources/application-local.properties.example`

to:

`backend/src/main/resources/application-local.properties`

Then set `spring.datasource.password`. That file is ignored by git.

Optional: if you use Docker, you can start PostgreSQL from `backend/` with:

```bash
docker compose up -d
```

This starts PostgreSQL 16 on port 5432 with user `postgres`, password `postgres`, and database `university_courses`.

On first startup, Spring Boot creates the tables and inserts sample subjects, chapters and lessons **only if the `subjects` table is empty**. Restarting the application does not duplicate the data.

## 2. Start the backend

From the `backend` folder:

```bash
./gradlew bootRun
```

On Windows:

```bat
gradlew.bat bootRun
```

If your PostgreSQL password is not `postgres`:

```bash
DB_PASSWORD=your_password ./gradlew bootRun
```

On Windows PowerShell:

```powershell
$env:DB_PASSWORD="your_password"
.\gradlew.bat bootRun
```

The API listens on [http://localhost:8080](http://localhost:8080).

Useful endpoints:

- `GET /api/subjects`
- `GET /api/subjects/{id}`
- `GET /api/subjects/{id}/chapters`
- `GET /api/chapters/{id}/lessons`
- `GET /api/lessons/{id}`

A missing subject, chapter or lesson returns HTTP 404.

## 3. Start the frontend

From the `frontend` folder:

```bash
npm install
npm start
```

The Angular dev server starts on [http://localhost:4200](http://localhost:4200) and forwards `/api` and `/files` to the Spring Boot server.

## 4. Use the application

1. Open the home page and choose a subject card.
2. Click a chapter to show its lessons.
3. Click a lesson to read the content.
4. Use **Accueil** or the back link to return.

One database lesson includes an optional PDF at `/files/intro-sgbd.pdf`.

## Notes for a supervisor

- No authentication, registration or user roles.
- Course data lives in PostgreSQL, not in the Angular code.
- Architecture is intentionally small: entities, repositories, one service, REST controllers, then Angular components and `CourseService`.
