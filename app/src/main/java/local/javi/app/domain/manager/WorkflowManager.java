package local.javi.app.domain.manager;

import local.javi.app.domain.model.project.ProjectDetails;

public interface WorkflowManager {
    void generateProject(ProjectDetails userInput);
}
