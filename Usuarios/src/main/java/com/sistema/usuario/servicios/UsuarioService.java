
package com.sistema.usuario.servicios;

import com.sistema.usuarios.modelo.UsuarioRol;
import com.sistema.usuarios.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UsuarioService {
    
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    
    public Usuario obtenerUsuario(String userName);
    
    public Optional<Usuario> obtenerUsuariobyId(Long usuarioId);
    
    public void eliminarUsuario(Long usuarioId);
    
    public List<Usuario> obtenerTodos();
    
}
