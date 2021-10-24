package pl.mkrew.app.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.mkrew.app.repository.UserRepository;

@AllArgsConstructor
public class MkrewUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .map(MkrewUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
