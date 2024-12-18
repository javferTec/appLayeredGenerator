package local.javi.app.domain.configuration;

import local.javi.app.common.annotation.domain.Settings;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Settings
public class SpringProjectSettings {

    @Value("${spring.initializr.url}")
    private String apiBaseUrl;

    @Value("${project.generator.path}")
    private String outputDirectory;

}