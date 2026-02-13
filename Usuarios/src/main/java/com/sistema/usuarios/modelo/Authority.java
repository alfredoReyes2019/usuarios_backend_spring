
package com.sistema.usuarios.modelo;

import org.springframework.security.core.GrantedAuthority;


public class Authority implements GrantedAuthority {

    private String authority;
    
    public Authority(String authority){
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return this.authority;
    }
    
}
