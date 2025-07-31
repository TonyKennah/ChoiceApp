# AI (gemini) created React App served via springboot
# React App with a Spring Boot Backend

This project is a single-page application built with React and served by a Spring Boot backend. The entire project is built and managed with Maven.

### `mvn clean package`
## Tech Stack
- **Backend**: Java 17, Spring Boot 3.1.4
- **Frontend**: React, TypeScript
- **Build**: Maven, Node.js 18.12.1, npm 8.19.2

### `mvn spring-boot:run`
## How to Run

Runs the application which listens on port 8181.
Open http://localhost:8181 to view it in the browser.
1.  **Build the application:**
    This command cleans the project, installs frontend dependencies, builds the React app, and packages everything into a single `.jar` file.
    ```bash
    mvn clean package
    ```

Other endpoints include /info and /config
2.  **Run the application:**
    This starts the Spring Boot server.
    ```bash
    mvn spring-boot:run
    ```

----
The application will be available at http://localhost:8181.

OLDER REACT STUFF
## Available Scripts
## API Endpoints

In the project directory, you can run:
- `GET /info`: Provides build and application information.
- `POST /config`: An endpoint for updating application configuration.

### `npm start`

Runs the app in the development mode.\
Open http://localhost:3000 to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about running tests for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about deployment for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

blah blah blah

## Learn More

You can learn more in the Create React App documentation.

To learn React, check out the React documentation.
## Development
This project uses Spring Boot DevTools for a better development experience. Once you start the application with `mvn spring-boot:run`, you can:
- **Backend Changes**: Simply save a Java file, and the application will automatically restart.
- **Frontend Changes**: After saving a `.tsx` or `.css` file, run `mvn package` in a separate terminal. DevTools will detect the new static files and automatically reload your browser.


