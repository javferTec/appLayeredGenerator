package local.javi.app.domain.model.project.entity;

import local.javi.app.domain.model.project.entity.attribute.Attribute;
import local.javi.app.domain.model.project.entity.method.Method;
import local.javi.app.domain.model.project.entity.relation.Relation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private Long id;                                // ID para la base de datos
    private String domainName;                     // Nombre de la entidad en el dominio (Java)
    private String dbName;                        // Nombre de la entidad en la base de datos
    private List<Attribute> attributes;          // Lista de atributos de la entidad
    private List<Relation> relations;   // Lista de relaciones con otras entidades
    private List<Method> methods;             // Métodos que se aplicarán a la entidad
    private boolean mainEntity;               // Si es entidad principal o no

    // Metodo para añadir relaciones, evitando relaciones con la misma entidad
    public void addRelationship(Relation relation) {
        // Verificamos si la entidad se está relacionando consigo misma
        if (this.domainName.equals(relation.getRelatedEntity().getDomainName()) ||
                this.dbName.equals(relation.getRelatedEntity().getDbName())) {
            throw new IllegalArgumentException("Una entidad no puede relacionarse consigo misma.");
        }
        relations.add(relation);
    }
}
