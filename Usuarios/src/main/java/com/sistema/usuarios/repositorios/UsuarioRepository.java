
package com.sistema.usuarios.repositorios;

import com.sistema.usuarios.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    public Usuario findByUserName (String userName);
    
}
