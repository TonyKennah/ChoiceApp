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
        Map<String, Object> info = new HashMap<>();
        info.put("app.name", buildProperties.getName());
        info.put("app.version", buildProperties.getVersion());
        info.put("build.time", buildProperties.getTime());
        info.put("build.artifact", buildProperties.getArtifact());
        info.put("build.group", buildProperties.getGroup());

        if (includeTime) {
            info.put("server.time", Instant.now().toString());
        }

        return info;
    }

    @PostMapping(path = "/config", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
     public ResponseEntity<Map<String, String>> updateConfig(@RequestParam(name = "options", defaultValue = "N/A") String selectedOption) {
        // You can populate this with application configuration details.
        // Be careful not to expose sensitive information here.
        logger.info("Received config update. Selected option: {}", selectedOption);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Configuration updated successfully");
        response.put("selectedOption", selectedOption);
        return ResponseEntity.ok(response);
    }
}