package com.engtechnologie.microcredit.config;

import com.engtechnologie.microcredit.features.user.Utilisateur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
class PersistenceConfig {
    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(this::getUsernameFromPrincipal);
    }

    private String getUsernameFromPrincipal(Object principal) {
        if (principal instanceof Utilisateur) {
            return ((Utilisateur) principal).getUsername();
        } else if (principal instanceof Jwt) {
            return ((Jwt) principal).getSubject();
        } else {
            return null;
        }
    }
}
