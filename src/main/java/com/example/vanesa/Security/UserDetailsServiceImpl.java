package com.example.vanesa.Security;

import com.example.vanesa.Models.Dtos.SignupRequestDTO;
import com.example.vanesa.Models.Entities.UserEntity;
import com.example.vanesa.Models.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(SignupRequestDTO signupRequest) {
        if(usuarioRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("El email ya estÃ¡ en uso.");
        }

        UserEntity newUser = new UserEntity();
        newUser.setName(signupRequest.getName());
        newUser.setSurname(signupRequest.getSurname());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return usuarioRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity usuario =  usuarioRepository
                .findOneByEmail(email) //si no encuentro ningun usuario con este correo electronico
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con email " + email + " no existe."));

        return new UserDetailsImpl(usuario);
    }
}