package pl.mkrew.app.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mkrew.app.domain.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MkrewUserDetails implements UserDetails {

    private UserEntity userEntity;

    public Long getUserId() {
        return userEntity.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isConfirmationStatus();
    }
}
