package entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String patronymic;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",columnDefinition = "ENUM('ADMINISTRATOR','SALE_USER','CUSTOMER_USER','SECURE_API_USER')")
    private Role role;

}
