package local.javi.app.domain.manager;

import local.javi.app.common.annotation.domain.Orchestrator;
import local.javi.app.domain.model.project.ProjectDetails;
import local.javi.app.domain.model.project.entity.Entity;
import local.javi.app.domain.model.project.entity.method.Method;
import local.javi.app.domain.model.project.entity.relation.Relation;
import local.javi.app.domain.usecase.SpringProjectCreateUseCase;
import lombok.RequiredArgsConstructor;

@Orchestrator
@RequiredArgsConstructor
public class ProjectWorkflowManager {

    private final SpringProjectCreateUseCase springProjectCreateUseCase;

    public void generateProject(ProjectDetails userInput) {
        springProjectCreateUseCase.createProject(userInput.getSpringProjectConfig());

        //printData(userInput);
    }

    private void printData(ProjectDetails userInput) {
        // Información general del usuario
        System.out.println("=========================================");
        System.out.println("El usuario que ha introducido los datos es:");
        System.out.println("  Usuario: " + userInput.getUser().getUsername());
        System.out.println("=========================================");

        // Configuración del proyecto de Spring
        System.out.println("Configuración del proyecto de Spring:");
        System.out.println("  " + userInput.getSpringProjectConfig());
        System.out.println("=========================================");

        // Listado de entidades
        System.out.println("Las entidades que se utilizarán son:");
        for (Entity entity : userInput.getEntities()) {
            System.out.println("  - Entidad: " + entity.getDomainName() + " (DB: " + entity.getDbName() + ")");
        }
        System.out.println("=========================================");

        // Relaciones entre entidades
        System.out.println("Relaciones entre las entidades:");
        for (Entity entity : userInput.getEntities()) {
            for (Relation relation : entity.getRelations()) {
                System.out.printf("  - La entidad '%s' se relaciona con '%s' de tipo '%s'.%n",
                        entity.getDomainName(),
                        relation.getRelatedEntity().getDomainName(),
                        relation.getType().getName());
            }
        }
        System.out.println("=========================================");

        // Métodos asociados a las entidades
        System.out.println("Métodos asociados a las entidades:");
        for (Entity entity : userInput.getEntities()) {
            System.out.println("  Métodos de la entidad '" + entity.getDomainName() + "':");
            for (Method method : entity.getMethods()) {
                System.out.println("    - " + method.getName());
            }
        }
        System.out.println("=========================================");

        // Opciones configuradas
        System.out.println("Opciones configuradas:");
        System.out.println("  - Utiliza DDL SQL: " + (userInput.isDdlSql() ? "Sí" : "No"));
        System.out.println("  - Usa JPA: " + (userInput.isJpa() ? "Sí" : "No"));
        System.out.println("  - Incluye control de roles: " + (userInput.isRoles() ? "Sí" : "No"));
        System.out.println("  - Incluye Thymeleaf: " + (userInput.isThymeleaf() ? "Sí" : "No"));
        System.out.println("=========================================");
    }


}

