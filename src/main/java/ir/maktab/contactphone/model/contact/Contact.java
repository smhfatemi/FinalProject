package ir.maktab.contactphone.model.contact;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="phonebook")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //field
    @Column
    //@NotNull
    private String name;

    private String family;

    @Column(unique = true)
    //@NotNull
    private String mobile;

    @Column(unique = true)
    @Email
    private String email;

    private String tel;

}
