package local.javi.app.domain.manager;

import local.javi.app.common.annotation.domain.DomainService;
import local.javi.app.common.annotation.domain.Orchestrator;
import local.javi.app.domain.model.project.ProjectDetails;
import local.javi.app.domain.model.project.entity.Entity;
import local.javi.app.domain.model.project.entity.method.Method;
import local.javi.app.domain.model.project.entity.relation.Relation;
import lombok.RequiredArgsConstructor;

@Orchestrator
@RequiredArgsConstructor
public class ProjectWorkflowManager {

    public void generateProject(ProjectDetails userInput) {
        printData(userInput);
    }

    private void printData(ProjectDetails userInput) {
        System.out.println("El usuario que ha introducido los datos es: " +
                userInput.getUser().getUsername());

        System.out.println("El proyecto de Spring tiene la siguiente configuración:\n" +
                userInput.getSpringProjectConfig());

        System.out.println("Las entidades que he de usar son:");
        for (Entity entity : userInput.getEntities()) {
            System.out.println("Entidad: " + entity.getDomainName() + " (DB: " + entity.getDbName() + ")");
        }

        System.out.println("Las relaciones entre entidades son:");
        for (Entity entity : userInput.getEntities()) {
            for (Relation relation : entity.getRelations()) {
                System.out.println("Entidad: " + entity.getDomainName() +
                        " se relaciona con: " + relation.getRelatedEntity().getDomainName() +
                        " de tipo: " + relation.getType().getName());
            }
        }

        System.out.println("Los métodos asociados a las entidades son:");
        for (Entity entity : userInput.getEntities()) {
            System.out.println("Métodos de la entidad " + entity.getDomainName() + ":");
            for (Method method : entity.getMethods()) {
                System.out.println("- " + method.getName());
            }
        }

        System.out.println("Opciones configuradas:");
        System.out.println("Utiliza DDL SQL: " + userInput.isDdlSql());
        System.out.println("Usa JPA: " + userInput.isJpa());
        System.out.println("Incluye control de roles: " + userInput.isRoles());
        System.out.println("Incluye Thymeleaf: " + userInput.isThymeleaf());
    }

}

