package com.tokio.demo.dto.web;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class UserRegisterDTO {

    @NotBlank(message = "{validate.username}")
    private String username;

    @Email(message = "{validate.invalidEmail}")
    @NotBlank (message="{validate.email}")
    private String email;

    @Size(min = 6, message = "{validate.passwordLength}")
    @NotBlank (message="{validate.password}")
    private String password;

    @NotBlank(message = "{validate.passwordConfirmation}")
    private String passwordBis;

    @AssertTrue (message="{validate.passwordMatch}")
    public boolean isPasswordMatch() {
        return password != null && password.equals(passwordBis);
    }
    @NotBlank(message = "{validate.name}")
    private String name;

    @NotBlank(message = "{validate.surname}")
    private String surname;


    private LocalDate dateOfBirth;
}
