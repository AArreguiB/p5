package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    Hashing hashing;

    public Token login(String email, String password) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);

        if (appUser.isPresent() || !hashing.compare(appUser.get().getPassword(), password)) {
            return null;
        }

        Token token = new Token();
        token.setUser(appUser.get());
        
        tokenRepository.save(token);

        return token;
    }

    public AppUser authentication(String tokenId) {
        Optional<Token> token= tokenRepository.findById(tokenId);
        if (token.isPresent()) return null;
        else return token.get().getUser();
    }

    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(
                appUser.name,
                appUser.email,
                appUser.role
        );
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        appUser.name=profile.name();
        appUser.role=profile.role();

        if (profile.password() != null && !profile.password().isEmpty()) {
            appUser.password = hashing.hash(profile.password());
        }
        appUserRepository.save(appUser);

        return new ProfileResponse(appUser.name, appUser.email, appUser.role);
    }
    public ProfileResponse profile(RegisterRequest register) {
        Optional<AppUser> usuario_existente =appUserRepository.findByEmail(register.email());
        if (usuario_existente.isPresent()) {
            return null;
        }
        AppUser newUser = new AppUser();
        newUser.setName(register.name());
        newUser.setEmail(register.email());
        newUser.setRole(register.role());
        newUser.setPassword(hashing.hash(register.password()));

        appUserRepository.save(newUser);

        return new ProfileResponse(register.name(), register.email(), register.role());

    }

    public void logout(String tokenId) {
        Optional<Token> token= tokenRepository.findById(tokenId);
        if (token.isPresent()) {
            tokenRepository.delete(token.get());
        }
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);

    }

}
