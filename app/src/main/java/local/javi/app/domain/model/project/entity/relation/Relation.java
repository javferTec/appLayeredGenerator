package local.javi.app.domain.model.project.entity.relation;

import local.javi.app.domain.model.project.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    private Long id;                    // ID para la base de datos
    private Entity relatedEntity;      // Entidad destino
    private RelationType type;        // Tipo de relación (OneToOne, OneToMany, etc.)
}
