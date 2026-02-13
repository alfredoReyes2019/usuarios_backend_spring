package com.sistema.usuarios;

import com.sistema.usuarios.modelo.Rol;
import com.sistema.usuarios.modelo.Usuario;
import com.sistema.usuarios.modelo.UsuarioRol;
import com.sistema.usuario.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SistemaExamenenesBackendApplication {

        @Autowired
        private UsuarioService usuarioService;
        
	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenenesBackendApplication.class, args);
	}

        
}
