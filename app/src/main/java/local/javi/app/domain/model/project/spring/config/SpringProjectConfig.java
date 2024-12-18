package local.javi.app.domain.model.project.spring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringProjectConfig {
    private Long id;                                                               // ID para la base de datos
    private String groupId;                                                       // El ID de grupo (generalmente el dominio de la empresa o proyecto)
    private String artifactId;                                                   // El ID del artefacto (nombre único para el proyecto)
    private String name;                                                         // El nombre del proyecto
    private String description;                                                  // Una descripción del proyecto
    private String packageName;                                                  // El nombre del paquete base para el proyecto

    // Estas propiedades son constantes para todos los proyectos generados con Spring Boot.
    private final String bootVersion = "3.4.0";                                    // Versión de Spring Boot a usar por defecto
    private final String language = "java";                                        // Lenguaje de programación, en este caso Java
    private String javaVersion = "17";                                             // Versión de Java configurada (puede ser modificada)
    private String type = "maven-project";                                         // El tipo de proyecto, en este caso un proyecto Maven
    private String packaging = "jar";                                             // El tipo de empaquetado, por defecto "jar"

    // Lista mutable de dependencias que el proyecto va a usar. Inicialmente vacía.
    private List<SpringProjectDependency> dependencies = new ArrayList<>();        // Listado de dependencias del proyecto

    // Constructor adicional para agregar dependencias
    public void addDependency(SpringProjectDependency springProjectDependency) {
        dependencies.add(springProjectDependency); // Agrega una nueva dependencia al proyecto
    }

    public void removeDependency(SpringProjectDependency springProjectDependency) {
        dependencies.remove(springProjectDependency); // Elimina una dependencia del proyecto
    }

    public void addDependencies(List<SpringProjectDependency> springProjectDependencies) {
        dependencies.addAll(springProjectDependencies); // Agrega una lista de dependencias
    }
}
