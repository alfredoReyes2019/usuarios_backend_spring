
package com.sistema.usuarios.servicios.impl;

import com.sistema.usuarios.modelo.Usuario;
import com.sistema.usuarios.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
       
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usuario usuario = this.usuarioRepository.findByUserName(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        System.out.println("Usuario encontrado: " +usuario.getPassword());
        return usuario;
        
    }
    
}
