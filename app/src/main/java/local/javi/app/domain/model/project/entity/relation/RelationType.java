package local.javi.app.domain.model.project.entity.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationType {
    private Long id;      // ID para la base de datos
    private String name; // Nombre del tipo de relación (OneToOne, OneToMany, etc.)
}
