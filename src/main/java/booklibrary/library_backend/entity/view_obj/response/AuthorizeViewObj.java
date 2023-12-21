package booklibrary.library_backend.entity.view_obj.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeViewObj {
    String username;
    String role;
    String token;
    Date expire;
}
