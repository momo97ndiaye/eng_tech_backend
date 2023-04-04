package com.engtechnologie.microcredit.features.user.auth;

import com.engtechnologie.microcredit.config.jwtConfig.JwtService;
import com.engtechnologie.microcredit.features.user.Role;
import com.engtechnologie.microcredit.features.user.Utilisateur;
import com.engtechnologie.microcredit.features.user.UtilisateurMapper;
import com.engtechnologie.microcredit.features.user.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UtilisateurMapper mapper;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Utilisateur.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.AGENT)
                .createdBy("ADMIN")
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .utilisateur(mapper.toDto(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .utilisateur(mapper.toDto(user))
                .build();
    }
}
