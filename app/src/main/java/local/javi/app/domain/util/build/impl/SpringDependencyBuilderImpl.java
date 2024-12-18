package local.javi.app.domain.util.build.impl;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;
import local.javi.app.domain.util.build.DependencyBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Utility
@RequiredArgsConstructor
public class SpringDependencyBuilderImpl implements DependencyBuilder {

    @Override
    public List<SpringProjectDependency> buildDependencies() {
        return List.of(
                new SpringProjectDependency("web"),
                new SpringProjectDependency("devtools"),
                new SpringProjectDependency("mariadb"),
                new SpringProjectDependency("lombok"),
                new SpringProjectDependency("jdbc"),
                new SpringProjectDependency("actuator")
        );
    }

}
