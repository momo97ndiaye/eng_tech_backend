package com.engtechnologie.microcredit.features.user;

import com.engtechnologie.microcredit.features.user.Role;
import lombok.Builder;

@Builder
public record UtilisateurDto(
         Integer id,
         String firstname,
         String lastname,
         String username,
         String phone,
         String address,
         Role role
) {

}
