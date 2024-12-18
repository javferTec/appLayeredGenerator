package local.javi.app.domain.util.build;

import local.javi.app.domain.model.project.spring.config.SpringProjectConfig;
import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;

import java.util.List;

public interface UrlBuilder {
    String buildUrl(String baseUrl, SpringProjectConfig config, List<SpringProjectDependency> dependencies);
}
