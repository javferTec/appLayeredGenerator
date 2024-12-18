package local.javi.app.domain.model.project.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password_hash;
    private String email;
    private Date created_at;
    private Date updated_at;
}
