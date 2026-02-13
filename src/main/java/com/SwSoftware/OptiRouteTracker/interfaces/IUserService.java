package com.SwSoftware.OptiRouteTracker.interfaces;

import com.SwSoftware.OptiRouteTracker.dtos.DtoPageableResponse;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.user.*;
import com.SwSoftware.OptiRouteTracker.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    DtoPageableResponse<DtoUser> getAllUsers(Integer page, Integer size);
    UserEntity getUserById(Long idUser);
    @Transactional
    DtoUser createUser(DtoCreateUser request);
    UserEntity getUserByUsername(String username);
    DtoUser getUser(Long idUser);
    DtoUserLogIn getUserToLogin(String username);
    @Transactional
    DtoUser updateUser(DtoUpdateUser request);
    void disableUser(Long idUser);
    @Transactional
    void resetPassword(DtoResetPassword request);
}
