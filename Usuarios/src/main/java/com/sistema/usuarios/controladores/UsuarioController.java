
package com.sistema.usuarios.controladores;

import com.sistema.usuarios.modelo.UsuarioRol;
import com.sistema.usuarios.modelo.Rol;
import com.sistema.usuarios.modelo.Usuario;
import com.sistema.usuarios.configuraciones.SecurityConfig;
import com.sistema.usuario.servicios.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
    
           usuario.setPerfil("defaul.png");
           Set<UsuarioRol> usuarioRoles = new HashSet<>();
           
           Rol rol = new Rol();
           rol.setRolId(2L);
           rol.setNombre("NORMAL");
           
           UsuarioRol usuarioRol = new UsuarioRol();
           usuarioRol.setRol(rol);
           usuarioRol.setUsuario(usuario);
           usuarioRoles.add(usuarioRol);
           usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
           return usuarioService.guardarUsuario(usuario, usuarioRoles);                      
    }
    
    @GetMapping("/{userName}")
    public Usuario obtenerUsuario(@PathVariable("userName") String userName ) {
        System.out.println(usuarioService.obtenerUsuario(userName));
        return usuarioService.obtenerUsuario(userName);
    }
    
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId ) {
        usuarioService.eliminarUsuario(usuarioId);
    }
    
}
