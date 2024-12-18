package local.javi.app.domain.util.build;

import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;

import java.util.List;

public interface DependencyBuilder {
    List<SpringProjectDependency> buildDependencies();
}
