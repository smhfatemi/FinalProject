package ir.maktab.contactphone.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRegisterDTO {

    private String username;
    private String password;
}
