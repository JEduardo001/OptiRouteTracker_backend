package com.SwSoftware.OptiRouteTracker.security.JwtSecurity;

import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserDetailsSerivceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsSerivceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        UserEntity user = userService.getUserByUsername(username);

        List<String> rolesList = user.getRoles().stream()
                .map(RoleEntity::getName)
                .toList();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(rolesList.toArray(new String[0]))
                .build();

    }
}
