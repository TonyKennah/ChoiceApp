package uk.co.kennah.choiceapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppInfoController.class)
public class AppInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildProperties buildProperties; // Mocked because AppInfoController depends on it

    @Test
    public void getInfo_shouldReturnBuildProperties() throws Exception {
        // Given: Mock the properties that will be returned
        when(buildProperties.getName()).thenReturn("choiceapp");
        when(buildProperties.getVersion()).thenReturn("0.0.1-SNAPSHOT");
        when(buildProperties.getTime()).thenReturn(Instant.now());
        when(buildProperties.getArtifact()).thenReturn("choiceapp");
        when(buildProperties.getGroup()).thenReturn("uk.co.kennah");

        // When & Then
        mockMvc.perform(get("/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.app.name").value("choiceapp"))
                .andExpect(jsonPath("$.app.version").value("0.0.1-SNAPSHOT"))
                .andExpect(jsonPath("$.build.group").value("uk.co.kennah"))
                .andExpect(jsonPath("$.server").doesNotExist());
    }

    @Test
    public void getInfo_shouldIncludeServerTime_whenParamIsTrue() throws Exception {
        // Given
        when(buildProperties.getName()).thenReturn("choiceapp");
        when(buildProperties.getVersion()).thenReturn("0.0.1-SNAPSHOT");
        when(buildProperties.getTime()).thenReturn(Instant.now());
        when(buildProperties.getArtifact()).thenReturn("choiceapp");
        when(buildProperties.getGroup()).thenReturn("uk.co.kennah");

        // When & Then
        mockMvc.perform(get("/info").param("includeTime", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.app.name").value("choiceapp"))
                .andExpect(jsonPath("$.server.time").exists());
    }

    @Test
    public void updateConfig_shouldReturnSuccess_whenOptionIsProvided() throws Exception {
        // Use a value that is valid according to the controller's validation logic
        String selectedValue = "option2";

        mockMvc.perform(post("/config")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("options", selectedValue))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Configuration updated successfully"))
                .andExpect(jsonPath("$.selectedOption").value(selectedValue));
    }

    @Test
    public void updateConfig_shouldUseDefaultValue_whenOptionIsMissing() throws Exception {
        mockMvc.perform(post("/config")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Configuration updated successfully"))
                .andExpect(jsonPath("$.selectedOption").value("N/A"));
    }

    @Test
    public void updateConfig_shouldReturnBadRequest_forInvalidOption() throws Exception {
        mockMvc.perform(post("/config")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("options", "invalid-option"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("Invalid option provided: invalid-option"));
    }
}
