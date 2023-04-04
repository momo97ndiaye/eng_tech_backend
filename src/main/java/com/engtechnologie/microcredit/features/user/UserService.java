package com.engtechnologie.microcredit.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UtilisateurRepository userRepository;
    private final UtilisateurMapper utilisateurMapper;

    private final PasswordEncoder passwordEncoder;
    public UtilisateurDto getById(Long id) {
      return  userRepository.findById(id).map(utilisateurMapper::toDto).orElseThrow();
    }

    public UtilisateurDto getByUsername(String username) {
        return userRepository.findByUsername(username).map(utilisateurMapper::toDto).orElseThrow();
    }

    public ResponseEntity<Void> create(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);
        utilisateur.setStatus(UserStatusEnum.ACTIVE);
        utilisateur.setPassword(passwordEncoder.encode("Eng@2023"));
        //utilisateur.setPassword(passwordEncoder.encode(generatePassword()));
        userRepository.save(utilisateurMapper.toEntity(utilisateurDto));
        return ResponseEntity.ok().body(null);
    }

    public ResponseEntity<Void> update(Long id, UtilisateurDto utilisateurDto) {
        userRepository.findById(id);
        return null;
    }

    public ResponseEntity<Void> deleteById(Long id) {

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> desactivate(Long id) {
        // find user by id
        userRepository.findById(id).map(user -> {
            // set active to false
            user.setStatus(UserStatusEnum.DESACTIVE);
            return userRepository.save(user);
        }).orElseThrow();
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> activate(Long id) {

        // find user by id
        userRepository.findById(id).map(user -> {
            // set active to false
            user.setStatus(UserStatusEnum.ACTIVE);
            return userRepository.save(user);
        }).orElseThrow();
        return ResponseEntity.ok().build();
    }

    // generate random password for user
    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public ResponseEntity<Void> resetPassword(String email) {
        // find user by id
        userRepository.findByUsername(email).map(user -> {
            // set active to false
            user.setPassword(passwordEncoder.encode(generatePassword()));
            // send Password via email
            return userRepository.save(user);
        }).orElseThrow();
        return ResponseEntity.ok().build();
    }

    public Collection<UtilisateurDto> getAll() {
        return utilisateurMapper.toDto(userRepository.findAll());
    }
}
