package br.com.dbc.vemser.alfabetizai.dto.login;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    @NotNull
    private String email;
    @NotNull
    private String senha;
}
