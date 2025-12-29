package com.tokio.demo.dto.api;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    @NotBlank(message = "{validate.username}")
    private String username;
    @NotBlank(message = "{validate.password}")
    private String password;
}
