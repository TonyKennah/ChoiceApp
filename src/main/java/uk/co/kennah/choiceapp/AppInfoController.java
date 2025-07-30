package uk.co.kennah.choiceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppInfoController {

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

    @PostMapping("/config")
     public Map<String, String> updateConfig(@RequestBody String request) {
        // You can populate this with application configuration details.
        // Be careful not to expose sensitive information here.

        System.out.println("Received config update. Selected option: " + request);
        

        return new HashMap<>(Map.of(
            "status", "success",
            "message", "Configuration updated successfully",
            "selectedOption", request
        ));
    }
}