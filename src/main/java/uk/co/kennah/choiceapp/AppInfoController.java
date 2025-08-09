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

import uk.co.kennah.tkapi.client.Session;
import uk.co.kennah.tkapi.model.MyRunner;
import uk.co.kennah.tkapi.process.DataFetcher;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
public class AppInfoController {

    private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);

    private final BuildProperties buildProperties;
    private Map<Long, MyRunner> horses = null;
    private LocalDateTime now;

    @Autowired
    public AppInfoController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo(
            @RequestParam(name = "includeTime", defaultValue = "false") boolean includeTime) {
        // Populated with actual application info from the build-info.properties file.
        Map<String, Object> appInfo = Map.of(
                "name", buildProperties.getName(),
                "version", buildProperties.getVersion());

        Map<String, Object> buildInfo = Map.of(
                "time", buildProperties.getTime(),
                "artifact", buildProperties.getArtifact(),
                "group", buildProperties.getGroup());

        Map<String, Object> response = new HashMap<>();
        response.put("app", appInfo);
        response.put("build", buildInfo);

        if (includeTime) {
            response.put("server", Map.of("time", Instant.now().toString()));
        }
        return response;
    }

    @PostMapping(path = "/config", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public ResponseEntity<Map<Long, MyRunner>> updateConfig(
            @RequestParam(name = "options", defaultValue = "UK") String selectedOption) {
        // You can populate this with application configuration details.
        // Be careful not to expose sensitive information here.
        List<String> validOptions = List.of("UK", "FR", "ZA", "AE");
        if (!validOptions.contains(selectedOption)) {
            Map<Long, MyRunner> errorResponse = new HashMap<>();
            errorResponse.put(999L, new MyRunner("Invalid option provided: " + selectedOption));
            // Return a 400 Bad Request status with the error message
            return ResponseEntity.badRequest().body(errorResponse);
        }

        logger.info("Received config update. Selected option: {}", selectedOption);

        Map<Long, MyRunner> response = new HashMap<>();
        //response.put("status", "success");
        //response.put("message", "Configuration updated successfully");
        //response.put("selectedOption", selectedOption);
        String dateToUse = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        response.putAll(getOdds(dateToUse));
        return ResponseEntity.ok(response);
    }

    public Map<Long, MyRunner> getOdds(String date) {

        if(now != null && now.isAfter(LocalDateTime.now().minusMinutes(5))) {
            // If the data was fetched within the last 5 minutes, return the cached data
            if(horses!=null && !horses.isEmpty()){
                return horses;
            }
        }

        horses = new HashMap<>();
        try {
            DataFetcher fetcher = new DataFetcher();
            Session session = fetcher.getSession();
            session.login();// Use the authenticator to log in
            if ("SUCCESS".equals(session.getStatus())) {
                horses = fetcher.getData(date);
                session.logout();
            } else {
                System.err.println("Login failed with status: " + session.getStatus() + ". Aborting operation.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred in the main process:");
            e.printStackTrace();
        } 
        now = LocalDateTime.now();
        return horses;
    }

}