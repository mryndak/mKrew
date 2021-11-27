package pl.mkrew.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.repository.UserRepository;

import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        UserDetails userDetails = User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities("USER").build();
        return userDetails;
    }
}
