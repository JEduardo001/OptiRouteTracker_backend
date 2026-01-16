package com.SwSoftware.OptiRouteTracker.services;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.*;
import com.SwSoftware.OptiRouteTracker.entities.RoleEntity;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionPasswordsDoNotMatch;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserEmailAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserUsernameAlreadyInUse;
import com.SwSoftware.OptiRouteTracker.exceptions.user.ExceptionUserNotFound;
import com.SwSoftware.OptiRouteTracker.repositories.UserRepository;
import com.SwSoftware.OptiRouteTracker.utils.mapper.RoleMapper;
import com.SwSoftware.OptiRouteTracker.utils.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService,RoleMapper roleMapper,
                       UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    public DtoPageableResponse<DtoUser> getAllUsers(Integer page, Integer size){
        Page<UserEntity> users = userRepository.findAll(PageRequest.of(page,size));
        List<DtoUser> dtoUsers = users.getContent().stream().map(userMapper::toDto).collect(Collectors.toList());
        return new DtoPageableResponse<DtoUser>(
                users.getTotalElements(),
                users.getTotalPages(),
                dtoUsers
        );
    }

    public UserEntity getUserById(Long idUser){
        return orThrow(userRepository.findById(idUser));
    }

    public DtoUser createUser(DtoCreateUser request){

        verifyPassword(request.getPassword(),request.getPasswordRepeat());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ExceptionUserUsernameAlreadyInUse();
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ExceptionUserEmailAlreadyInUse();
        }

        List<RoleEntity> roles = new LinkedList<>();
        if(request.getRoles() != null){
            roles = roleService.getRolesByIdsOrThrow(request.getRoles());
        }

        UserEntity user = userMapper.toEntity(request);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserEntity getUserByUsername(String username){
        return orThrow(userRepository.findByUsername(username));
    }

    public DtoUser getUser(Long idUser){
        return userMapper.toDto(orThrow(userRepository.findById(idUser)));
    }

    private UserEntity orThrow(Optional<UserEntity> user) {
        return user.orElseThrow(ExceptionUserNotFound::new);
    }

    public DtoUserLogIn getUserToLogin(String username){
        return userMapper.toUserDtoLogin(getUserByUsername(username));
    }

    public DtoUser updateUser(DtoUpdateUser request){
        UserEntity user = orThrow(userRepository.findById(request.getId()));

        if(userRepository.existsByUsernameAndIdNot(request.getName(),request.getId())){
            throw new ExceptionUserUsernameAlreadyInUse();
        }

        if(userRepository.existsByEmailAndIdNot(request.getName(),request.getId())){
            throw new ExceptionUserEmailAlreadyInUse();
        }


        List<RoleEntity> newRoles = roleService.getRolesByIdsOrThrow(request.getRoles());

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setLastname(request.getLastname());
        user.setActive(request.isActive());
        user.setRoles(newRoles);

        return userMapper.toDto(userRepository.save(user));
    }

    public void disableUser(Long idUser){
        UserEntity user = orThrow(userRepository.findById(idUser));
        user.setActive(false);
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(DtoResetPassword request){
        UserEntity user = getUserById(request.getId());

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword()
        )) {
            throw new ExceptionPasswordsDoNotMatch();
        }

        verifyPassword(request.getNewPassword(),request.getConfirmPassword());

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void verifyPassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new ExceptionPasswordsDoNotMatch();
        }
    }
}
