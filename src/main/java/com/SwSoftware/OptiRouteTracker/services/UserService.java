package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.role.DtoRole;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoCreateUser;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.DtoUser;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionPasswordsDoNotMatch;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserEmailAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserUsernameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.resource.ExceptionUserNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.UserRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.RoleMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService,RoleMapper roleMapper
                       ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    public void existUserById(Long idUser){
        if (!userRepository.existsById(idUser)) {
            throw new ExceptionUserNotFound();
        }
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

        List<DtoRole> rolesDto = roles.stream().map(r -> roleMapper.toDto(r)).collect(Collectors.toList());

        return DtoUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(data.getName())
                .email(data.getEmail())
                .birthday(data.getBirthday())
                .lastname(data.getLastname())
                .roles(rolesDto).build();
    }

    public UserEntity getUserByUsername(String username){
        return orThrow(userRepository.findByUsername(username));
    }

    public UserEntity getUserById(Long idUser){
        return orThrow(userRepository.findById(idUser));
    }

    private UserEntity orThrow(Optional<UserEntity> user) {
        return user.orElseThrow(ExceptionUserNotFound::new);
    }

}
