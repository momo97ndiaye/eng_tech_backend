package com.engtechnologie.microcredit.features.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/users")
@CrossOrigin(maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
public interface UserApi {

    @GetMapping
    Collection<UtilisateurDto> getAll();

    @GetMapping("/{id}")
    UtilisateurDto getById(@PathVariable Long id);

    @GetMapping("/username/{username}")
    UtilisateurDto getByUsername(@PathVariable String username);

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody UtilisateurDto utilisateurDto);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UtilisateurDto utilisateurDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);

    @PutMapping("/{id}/desactivate")
    ResponseEntity<Void> desactivate(@PathVariable Long id);

    @PutMapping("/{id}/activate")
    ResponseEntity<Void> activate(@PathVariable Long id);

    // password reset
    @PutMapping("/{id}/reset-password")
    ResponseEntity<Void> resetPassword(@PathVariable String email);
}
