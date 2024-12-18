package local.javi.app.controller;

import local.javi.app.domain.model.project.ProjectDetails;
import local.javi.app.domain.manager.impl.ProjectWorkflowManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.File;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppBuilderController.URL)
public class AppBuilderController {

    public static final String URL = "/api/v1/builder";

    private final ProjectWorkflowManagerImpl projectWorkflowManagerImpl;

    @GetMapping()
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> generateProject(@RequestBody ProjectDetails userInput) {
        projectWorkflowManagerImpl.generateProject(userInput);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/zip")
    public ResponseEntity<File> downloadZip() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
