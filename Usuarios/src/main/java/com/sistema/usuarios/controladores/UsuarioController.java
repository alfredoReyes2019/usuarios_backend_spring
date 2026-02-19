
package com.sistema.usuarios.controladores;

import com.sistema.usuarios.modelo.*;
import com.sistema.usuarios.configuraciones.SecurityConfig;
import com.sistema.usuario.servicios.UsuarioService;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
    public Usuario guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
    
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setUserName(usuarioDTO.getUserName());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setEnabled(usuarioDTO.isEnabled());
           Set<UsuarioRol> usuarioRoles = new HashSet<>();
          System.out.println("Antes de entrar a los roles");
          
           for(var rol : usuarioDTO.getRoles()){
               System.out.println("rol" + rol.getNombre() + "id: " + rol.getRolId());
           UsuarioRol usuarioRol = new UsuarioRol();
           usuarioRol.setRol(rol);
           usuarioRol.setUsuario(usuario);
           usuarioRoles.add(usuarioRol);
        }
           
           usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
           return usuarioService.guardarUsuario(usuario, usuarioRoles);                      
    }
    
    @GetMapping("/{userName}")
    public Usuario obtenerUsuario(@PathVariable("userName") String userName ) {
        System.out.println(usuarioService.obtenerUsuario(userName));
        return usuarioService.obtenerUsuario(userName);
    }
    
    @GetMapping("/UsuarioId/{usuarioId}")
    public Optional<Usuario> obtenerUsuarioById(@PathVariable("usuarioId") Long usuarioId ) {
        
        return usuarioService.obtenerUsuariobyId(usuarioId);
    }
    
    @DeleteMapping("/Eliminar/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId ) {
        usuarioService.eliminarUsuario(usuarioId);
    }
    
    @GetMapping("getAll")
    public List<Usuario> obtenerTodos() {
        
        return usuarioService.obtenerTodos();
    }
    
}
