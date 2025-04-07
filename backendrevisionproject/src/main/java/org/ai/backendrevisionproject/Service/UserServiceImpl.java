package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Config.JwtProvider;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailToken(jwt);
        System.out.println("The email is : "+email);
        User user=findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User>optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw  new Exception("User Not Found");
        }
        return optionalUser.get();
    }

    @Override
    public User updateUserProjectSize(User user,int number) throws Exception {
        user.setProjectSize(number);
        return userRepository.save(user);
    }
}
