package com.spring.library.config;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("user not found");
        }
        return userOptional.map(MyUserDetails::new).get();
    }
}
