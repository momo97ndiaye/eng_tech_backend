package com.engtechnologie.microcredit.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class UserControllerApi implements UserApi{

    private final UserService service;
    @Override
    public Collection<UtilisateurDto> getAll() {
        return service.getAll();
    }

    @Override
    public UtilisateurDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public UtilisateurDto getByUsername(String username) {
        return service.getByUsername(username);
    }

    @Override
    public ResponseEntity<Void> create(UtilisateurDto utilisateurDto) {
        return service.create(utilisateurDto);
    }

    @Override
    public ResponseEntity<Void> update(Long id, UtilisateurDto utilisateurDto) {
        return service.update(id, utilisateurDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        return service.deleteById(id);
    }

    @Override
    public ResponseEntity<Void> desactivate(Long id) {
        return service.desactivate(id);
    }

    @Override
    public ResponseEntity<Void> activate(Long id) {
        return service.activate(id);
    }

    @Override
    public ResponseEntity<Void> resetPassword(String email) {
        return service.resetPassword(email);
    }
}
