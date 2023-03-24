package entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import entity.mapper.CustomUserSerializer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonSerialize(using = CustomUserSerializer.class)

public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String patronymic;
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(patronymic, user.patronymic) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
