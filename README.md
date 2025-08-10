[![Build & Test Status](https://github.com/TonyKennah/ChoiceApp/actions/workflows/maven.yml/badge.svg)](https://github.com/TonyKennah/ChiceApp/actions/workflows/maven.yml)

# React App with a Spring Boot Backend

This project is a single-page application built with React and served by a Spring Boot backend. It allows users to select and retrieve the latest horse racing odds from Betfair.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.1.4
- **Frontend**: React, TypeScript
- **Build**: Maven, Node.js 18.12.1, npm 8.19.2

## How to Run

### 1. Prerequisite: Authenticate to GitHub Packages

**Important:** This project relies on a private library, `PluckierOdds`, which is hosted in a private GitHub Packages repository. The build will fail unless you have been 	⚠️<ins>**granted access**</ins>⚠️ to this repository.

For authorized users, you must configure Maven to authenticate with GitHub Packages:

1.  **Create a Personal Access Token (classic)** on GitHub with the `read:packages` scope.

2.  **Configure your Maven `settings.xml`** file (usually at `~/.m2/settings.xml`) with a `<server>` entry. The `<id>` (`github`) must match the repository ID in the `pom.xml`.

    ```xml
    <settings>
      ...
      <servers>
        <server>
          <id>github</id>
          <username>YOUR_GITHUB_USERNAME</username>
          <password>YOUR_PERSONAL_ACCESS_TOKEN</password>
        </server>
      </servers>
      ...
    </settings>
    ```
    Replace `YOUR_GITHUB_USERNAME` and `YOUR_PERSONAL_ACCESS_TOKEN` with your details.

### 2. Build the application

This command cleans the project, installs frontend dependencies, builds the React app, and packages everything into a single `.jar` file.
```bash
mvn clean package
```

### 3. Run the application

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
├── frontend/              # React frontend source code
│   ├── public/
│   ├── src/
│   └── ...
├── src/                   # Spring Boot backend source code
│   └── main/
├── pom.xml                # Maven project configuration
└── README.md              # This file
```
