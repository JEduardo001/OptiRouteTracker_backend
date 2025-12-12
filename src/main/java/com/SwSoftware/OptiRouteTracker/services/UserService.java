package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosCreate.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.DtoUser;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionPasswordsDoNotMatch;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserEmailAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserUsernameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionUserNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService
                       ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public DtoUser createUser(DtoCreateUser data){

        if(!data.getPassword().equals(data.getPasswordRepeat())){
            throw new ExceptionPasswordsDoNotMatch();
        }

        if (userRepository.existsByUsername(data.getUsername())) {
            throw new ExceptionUserUsernameAlreadyInUse();
        }

        if (userRepository.existsByEmail(data.getEmail())) {
            throw new ExceptionUserEmailAlreadyInUse();
        }

        List<RoleEntity> roles = new ArrayList<>();
        if(data.getRolesId() != null){
            roles = data.getRolesId().stream().map(roleService::getRoleById).toList();
        }

        UserEntity user = UserEntity.builder()
                .username(data.getUsername())
                .name(data.getName())
                .email(data.getEmail())
                .birthday(data.getBirthday())
                .lastname(data.getLastname())
                .password(passwordEncoder.encode(data.getPassword()))
                .roles(roles).build();

        userRepository.save(user);

        return DtoUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(data.getName())
                .email(data.getEmail())
                .birthday(data.getBirthday())
                .lastname(data.getLastname())
                .roles(roles).build();
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(ExceptionUserNotFound::new);
    }
}
