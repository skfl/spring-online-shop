package ru.skfl.skflshop.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skfl.skflshop.repositories.UserRepository;

@Service
public class ShopUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return new ShopUserPrincipal(userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        }));
    }
}