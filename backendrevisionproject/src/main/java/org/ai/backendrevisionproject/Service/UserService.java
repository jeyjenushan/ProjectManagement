package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.User;

public interface UserService {

    User findByUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email)throws Exception;
    User findUserById(Long userId)throws Exception;
    User updateUserProjectSize(User user,int number)throws Exception;
}
