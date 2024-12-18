package local.javi.app.domain.model.project.entity.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    private Long id;                                 // ID para la base de datos
    private String domainName;                      // Nombre del atributo en la entidad de dominio
    private String dbName;                         // Nombre del atributo en la base de datos
    private AttributeDataType dataType;               // Tipo del atributo (por ejemplo, String, Long, etc.)
    private boolean primaryKey;                  // Si es clave primaria o no
}
