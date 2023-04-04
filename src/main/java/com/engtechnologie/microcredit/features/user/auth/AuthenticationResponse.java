package com.engtechnologie.microcredit.features.user.auth;


import com.engtechnologie.microcredit.features.user.UtilisateurDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private UtilisateurDto utilisateur;
}
