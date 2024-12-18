package local.javi.app.domain.validator.impl;

import local.javi.app.common.annotation.domain.Validator;
import local.javi.app.domain.exception.ValidationException;
import local.javi.app.domain.model.project.ProjectDetails;
import local.javi.app.domain.validator.ProjectValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validator
public class ProjectValidatorImpl implements ProjectValidator {

    @Override
    public void validate(ProjectDetails details) {
        if (details == null || details.getSpringProjectConfig() == null) {
            throw new ValidationException("Los detalles del proyecto son inválidos o están incompletos.");
        }

        if (details.getUser() == null || details.getUser().getUsername() == null) {
            throw new ValidationException("El usuario no está definido.");
        }

        if (details.getSpringProjectConfig().getName() == null || details.getSpringProjectConfig().getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede estar vacío.");
        }

        log.debug("Validación completada para el proyecto '{}'", details.getSpringProjectConfig().getName());
    }

}