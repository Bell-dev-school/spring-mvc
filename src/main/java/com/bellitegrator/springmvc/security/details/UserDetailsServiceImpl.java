package com.bellitegrator.springmvc.security.details;

import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найлен"));
        return new UserDetailsImpl(user);
    }
}
