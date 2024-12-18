package local.javi.app.domain.model.project.entity.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Method {
    private Long id;      // ID para la base de datos
    private String name; // Nombre del metodo (GETALL, GETBYID, etc.)
}
