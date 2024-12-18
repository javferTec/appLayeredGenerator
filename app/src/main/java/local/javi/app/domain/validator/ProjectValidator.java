package local.javi.app.domain.validator;

import local.javi.app.domain.model.project.ProjectDetails;

public interface ProjectValidator {
    void validate(ProjectDetails details);
}
