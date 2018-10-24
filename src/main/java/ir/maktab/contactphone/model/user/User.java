package ir.maktab.contactphone.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.maktab.contactphone.model.role.Role;
import lombok.Data;
import lombok.ToString;

import javax.annotation.meta.Exclusive;
import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //field
    @Column(unique = true)
    private String username;
    private String password;

    @JoinColumn(name = "role_id")
    @ManyToOne
    @ToString.Exclude
    private Role roleObj;

}
