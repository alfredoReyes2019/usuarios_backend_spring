
package com.sistema.usuarios.repositorios;

import com.sistema.usuarios.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol,Long> {
    
}
