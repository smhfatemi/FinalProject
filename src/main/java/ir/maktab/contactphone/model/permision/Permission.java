package ir.maktab.contactphone.model.permision;


import ir.maktab.contactphone.model.role.Role;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @Column(name = "perm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String permissionName;

    @ManyToMany(mappedBy = "permissionSet")
    @ToString.Exclude
    private Set<Role> roleSet;
}
