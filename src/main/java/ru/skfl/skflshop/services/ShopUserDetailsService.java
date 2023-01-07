package ru.skfl.skflshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.dto.ShopUserPrincipal;
import ru.skfl.skflshop.repositories.UserRepository;

@Service
public class ShopUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new ShopUserPrincipal(userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        }));
    }
}