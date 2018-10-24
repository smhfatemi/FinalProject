package ir.maktab.contactphone.model.role;


import ir.maktab.contactphone.model.permision.Permission;
import ir.maktab.contactphone.model.user.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String roleName;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roleObj", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<User> userSet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_permmission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    @ToString.Exclude
    private Set<Permission> permissionSet;


}
