# React App with a Spring Boot Backend

This project is a single-page application built with React and served by a Spring Boot backend. It allows users to select an option and retrieve the latest odds from Betfair.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.1.4
- **Frontend**: React, TypeScript
- **Build**: Maven, Node.js 18.12.1, npm 8.19.2

## How to Run

### Prerequisite 

a `lib` folder is required within the project root.  A jarfile is required in this folder created from the [PluckierOdds](https://github.com/TonyKennah/PluckierOdds) repo and will contain sensitive / secret data.  Without this the project will not even compile.

1.  **Build the application:**
    This command cleans the project, installs frontend dependencies, builds the React app, and packages everything into a single `.jar` file.
    ```bash
    mvn clean package
    ```

2.  **Run the application:**
    This starts the Spring Boot server.
    ```bash
    mvn spring-boot:run
    ```

The application will be available at http://localhost:8181.

## API Endpoints

- `GET /info`: Provides build and application information.
- `POST /config`: Retrieves the latest odds for the selected option. Odds are cached and only sourced directly from Betfair if the cached version is considered out of date.

## Project Structure

The project is organized into two main parts: a Spring Boot backend and a React frontend.

```
.
├── lib/tk-api-ng-1.0.jar  # Pluckier betafir wrapper
├── frontend/              # React frontend source code
│   ├── public/
│   ├── src/
│   └── package.json
├── src/main/java/         # Spring Boot backend source code
│   └── uk/co/kennah/choiceapp/
│       ├── AppInfoController.java
│       ├── ChoiceApplication.java
│       └── WebConfig.java
└── pom.xml                # Maven build config
```

-   **`pom.xml`**: The root Maven file that manages the build for the entire project, including building the frontend and packaging it into the final JAR.
-   **`src/main/java`**: Contains all the Java source code for the Spring Boot backend.
-   **`frontend`**: Contains the React/TypeScript single-page application. The `frontend-maven-plugin` in the `pom.xml` handles building this part of the application.

## Development
This project uses Spring Boot DevTools for a better development experience. Once you start the application with `mvn spring-boot:run`, you can:
- **Backend Changes**: Simply save a Java file, and the application will automatically restart.
- **Frontend Changes**: After saving a `.tsx` or `.css` file, run `mvn package` in a separate terminal. DevTools will detect the new static files and automatically reload your browser.

## Continuous Integration

-- turned off
