package com.example.todo2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * loadUserByUsername() 결과 처리 DTO
 */
@Getter
@Setter
@ToString
public class APIUserDTO extends User {
    private String mid;
    private String mpw;

    public APIUserDTO(String username, String password, Collection<GrantedAuthority> authority) {
        super(username, password, authority);
        this.mid = username;
        this.mpw = password;
    }
}
