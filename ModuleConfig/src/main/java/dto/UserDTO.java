package dto;

import entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private Role role;
}
