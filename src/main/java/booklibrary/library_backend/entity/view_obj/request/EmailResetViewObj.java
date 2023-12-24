package booklibrary.library_backend.entity.view_obj.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class EmailResetViewObj {
    @Email
    String email;
    @Length(min = 6,max = 6)
    String code;
    @Length(min = 6,max = 20)
    String password;
}
