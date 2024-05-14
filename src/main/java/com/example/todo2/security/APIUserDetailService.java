package com.example.todo2.security;

import com.example.todo2.domain.APIUser;
import com.example.todo2.dto.APIUserDTO;
import com.example.todo2.repository.APIUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 스프링 시큐리티의 UserDetailsService 인터페이스 구현
 */

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserDetailService implements UserDetailsService {

    private final APIUserRepository apiUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //사용자가 존재할 때 APIUserDTO 반환
        Optional<APIUser> result = apiUserRepository.findById(username);

        APIUser apiUser = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid"));
        log.info("APIUserDetailService apiUser .....");

        //모든 사용자의 권한은 'ROLE_USER'
        APIUserDTO dto = new APIUserDTO(
                apiUser.getMid(),
                apiUser.getMpw(), ,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        log.info(dto);

        return dto;
    }
}
