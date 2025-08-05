# React App with a Spring Boot Backend

[![Build & Test Status](https://github.com/TonyKennah/choiceapp/actions/workflows/maven.yml/badge.svg)](https://github.com/TonyKennah/choiceapp/actions/workflows/maven.yml)

This project is a single-page application built with React and served by a Spring Boot backend. The entire project is built and managed with Maven.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.1.4
- **Frontend**: React, TypeScript
- **Build**: Maven, Node.js 18.12.1, npm 8.19.2

## How to Run

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
- `POST /config`: An endpoint for updating application configuration.

## Development
This project uses Spring Boot DevTools for a better development experience. Once you start the application with `mvn spring-boot:run`, you can:
- **Backend Changes**: Simply save a Java file, and the application will automatically restart.
- **Frontend Changes**: After saving a `.tsx` or `.css` file, run `mvn package` in a separate terminal. DevTools will detect the new static files and automatically reload your browser.

## Continuous Integration

This project uses GitHub Actions to automate the build and test process. On every push and pull request to the `main` branch, the following actions are performed:

- The full Maven project is built using `mvn clean package`.
- Automated tests for the backend are executed to ensure code correctness and stability.
