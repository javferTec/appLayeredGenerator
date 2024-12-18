package local.javi.app.domain.model.project.spring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringProjectDependency {
    /*WEB("web"),
    DEVTOOLS("devtools"),
    MARIADB("mariadb"),
    LOMBOK("lombok"),
    TEST("test"),
    JDBC("jdbc"),
    MAPSTRUCT("mapstruct"),
    ACTUATOR("actuator"),
    MODELMAPPER("modelmapper");*/

    private Long id;        // ID para la base de datos
    private String value;  // Nombre de la dependencia (por ejemplo: "web", "mariadb", etc.)

    public SpringProjectDependency(String value) {
        this.value = value;
    }
}

