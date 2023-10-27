package com.example.vanesa.Controllers;
import com.example.vanesa.Models.Dtos.SignupRequestDTO;
import com.example.vanesa.Security.AuthCredentials;
import com.example.vanesa.Security.TokenUtils;
import com.example.vanesa.Security.UserDetailsImpl;
import com.example.vanesa.Security.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailService;
    private final AuthenticationManager authenticationManager;


    public AuthController(UserDetailsServiceImpl userDetailService, AuthenticationManager authenticationManager) {
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDTO signUpRequest) {
        if(userDetailService.registerUser(signUpRequest) != null) {
            return ResponseEntity.ok("Usuario registrado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Error al registrar el usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentials loginRequest) {

        // Intento de autenticación usando el AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Si la autenticación es exitosa, generamos un token.
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());

        // En un caso real, puedes querer devolver el token dentro de un objeto para mayor flexibilidad.
        return ResponseEntity.ok("Bearer " + jwt);
    }

}
