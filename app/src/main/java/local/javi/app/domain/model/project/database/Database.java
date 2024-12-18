package local.javi.app.domain.model.project.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Database {
    private String name; // Nombre de la base de datos
    private String url; // URL de conexión a la base de datos
    private String username; // Usuario de la base de datos
    private String password; // Contraseña de la base de datos
}
