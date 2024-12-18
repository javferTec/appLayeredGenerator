package local.javi.app.domain.model.project;

import local.javi.app.domain.model.project.database.Database;
import local.javi.app.domain.model.project.entity.Entity;
import local.javi.app.domain.model.project.spring.config.SpringProjectConfig;
import local.javi.app.domain.model.project.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails {
    private Long id;                                     // ID para la base de datos
    private User user;                                  // Usuario que crea el proyecto
    private SpringProjectConfig springProjectConfig;   // Configuración inicial del proyecto Spring Boot
    private List<Entity> entities;                    // Lista de entidades definidas por el usuario
    private Database database;                        // Base de datos que el proyecto utilizará
    private boolean ddlSql;                          // Indica si se debe generar DDL SQL
    private boolean jpa;                            // Indica si se debe usar JPA
    private boolean roles;                         // Indica si se debe incluir control de roles
    private boolean thymeleaf;                    // Indica si se debe incluir Thymeleaf
}