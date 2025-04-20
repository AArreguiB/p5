package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test void saveTest() {
        // Given ...
        AppUser user = new AppUser();
        user.setName("Juan");
        user.setEmail("juan@gmail.com");
        user.setPassword("Password1234");
        user.setRole(Role.USER);

        appUserRepository.save(user);
        Token token = new Token();
        token.setUser(user);
        tokenRepository.save(token);

        // When ...
        Optional<AppUser> foundUser = appUserRepository.findByEmail("juan@gmail.com");
        Optional<Token> foundToken=tokenRepository.findById(token.id);
        // Then ...
        assertTrue(foundUser.isPresent());
        assertEquals("juan@gmail.com", foundUser.get().getEmail());

        assertTrue(foundToken.isPresent());
        assertEquals(user.getId(), foundToken.get().getUser().getId());



    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        tokenRepository.deleteAll();
        appUserRepository.deleteAll();
        // Given ...
        AppUser user = new AppUser();
        user.setName("Pedro");
        user.setEmail("pedro@email.com");
        user.setPassword("Password123");
        user.setRole(Role.USER);
        appUserRepository.save(user);

        Token token = new Token();
        token.setUser(user);
        tokenRepository.save(token);

        // Verificamos que hay 1 usuario y 1 token
        assertEquals(1, appUserRepository.count());
        assertEquals(1, tokenRepository.count());

        // When ...
        appUserRepository.delete(user);

        // Then ...
        assertEquals(0, appUserRepository.count());
        assertEquals(0, tokenRepository.count());

    }
}