package com.upc.TuCine.security.middleware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.TuCine.user.domain.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private Integer id;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) other;
        return Objects.equals(id, user.id);
    }

    public static UserDetailsImpl build(User user) {
        String authorityName = user.getTypeUser().getName().name();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authorityName));

        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }
}
