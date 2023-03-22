package entity;


import javax.persistence.*;
import java.util.Objects;

@Entity

public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && roles == role.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roles);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roles=" + roles +
                '}';
    }
}
