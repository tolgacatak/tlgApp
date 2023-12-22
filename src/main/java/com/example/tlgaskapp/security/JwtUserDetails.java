package com.example.tlgaskapp.security;

import com.example.tlgaskapp.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities; //kullanıcının sahip olduğu yetkileri temsil eden Collection döndürür. Collection ,nesne grubunu temsil eder.

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static JwtUserDetails create(UserEntity user){ //rol ve sahip olduğu parametre veya yetkiler
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList);
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
    } //come from jwt sec.

}
