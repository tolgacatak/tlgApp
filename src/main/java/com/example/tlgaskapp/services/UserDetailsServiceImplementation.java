package com.example.tlgaskapp.services;

import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.UserRepository;
import com.example.tlgaskapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private UserRepository userRepository;
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        return JwtUserDetails.create(user); //repo'dan gelen user'ı userDetails tipinde döndürür.
    }

    public UserDetails LoadUserById(Long id){
        UserEntity user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
