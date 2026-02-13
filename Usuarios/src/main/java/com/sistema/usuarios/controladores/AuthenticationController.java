
package com.sistema.usuarios.controladores;

import com.sistema.usuarios.configuraciones.JwtUtil;
import com.sistema.usuarios.modelo.JwtRequest;
import com.sistema.usuarios.modelo.JwtResponse;
import com.sistema.usuarios.modelo.Usuario;
import com.sistema.usuarios.servicios.impl.UserDetailsServiceImpl;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    
    

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService,
                                    JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("Entra a generar token");

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Usuario no encontrado o credenciales inválidas");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @GetMapping("/actual-usuario")
    public Usuario ontenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}    



