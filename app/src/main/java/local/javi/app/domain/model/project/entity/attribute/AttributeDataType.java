package local.javi.app.domain.model.project.entity.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDataType {

    private Long id;           // ID para la base de datos
    private String javaType;  // Representación en Java
    private String dbType;   // Representación en bases de datos

    // Metodo estático para convertir dbType a un objeto AttributeDataType
    public static AttributeDataType fromDbType(String dbType, Iterable<AttributeDataType> attributeDataTypes) {
        for (AttributeDataType type : attributeDataTypes) {
            if (type.getDbType().equalsIgnoreCase(dbType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown DB type: " + dbType);
    }

    // Metodo estático para convertir javaType a un objeto AttributeDataType
    public static AttributeDataType fromJavaType(String javaType, Iterable<AttributeDataType> attributeDataTypes) {
        for (AttributeDataType type : attributeDataTypes) {
            if (type.getJavaType().equalsIgnoreCase(javaType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown Java type: " + javaType);
    }
}
