package com.sistema.usuarios.servicios.impl;

import com.sistema.usuarios.repositorios.RolRepository;
import com.sistema.usuarios.repositorios.UsuarioRepository;
import com.sistema.usuarios.modelo.Usuario;
import com.sistema.usuarios.modelo.UsuarioRol;
import com.sistema.usuario.servicios.UsuarioService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
//public class UsuarioServiceImpl  {
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUserName(usuario.getNombre());
        if (usuarioLocal != null) {
            System.out.println("El usaurio ya existe");
            throw new Exception("El usuario ya esta presente"); 
        }
        else {
            for (UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());                       
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);            
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
}
