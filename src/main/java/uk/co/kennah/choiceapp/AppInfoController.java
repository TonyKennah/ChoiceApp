package uk.co.kennah.choiceapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
public class AppInfoController {

    private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);

    private final BuildProperties buildProperties;

    @Autowired
    public AppInfoController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo(@RequestParam(name = "includeTime", defaultValue = "false") boolean includeTime) {
        // Populated with actual application info from the build-info.properties file.
        Map<String, Object> appInfo = Map.of(
                "name", buildProperties.getName(),
                "version", buildProperties.getVersion()
        );

        Map<String, Object> buildInfo = Map.of(
                "time", buildProperties.getTime(),
                "artifact", buildProperties.getArtifact(),
                "group", buildProperties.getGroup()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("app", appInfo);
        response.put("build", buildInfo);

        if (includeTime) {
            response.put("server", Map.of("time", Instant.now().toString()));
        }
        return response;
    }

    @PostMapping(path = "/config", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
     public ResponseEntity<Map<String, String>> updateConfig(@RequestParam(name = "options", defaultValue = "N/A") String selectedOption) {
        // You can populate this with application configuration details.
        // Be careful not to expose sensitive information here.
        List<String> validOptions = List.of("option1", "option2", "option3", "option4", "N/A");
        if (!validOptions.contains(selectedOption)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Invalid option provided: " + selectedOption);
            // Return a 400 Bad Request status with the error message
            return ResponseEntity.badRequest().body(errorResponse);
        }

        logger.info("Received config update. Selected option: {}", selectedOption);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Configuration updated successfully");
        response.put("selectedOption", selectedOption);
        return ResponseEntity.ok(response);
    }
}