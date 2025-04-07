package org.ai.backendrevisionproject.Controller;

import org.ai.backendrevisionproject.Config.JwtProvider;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.UserRepository;
import org.ai.backendrevisionproject.Request.LoginRequest;
import org.ai.backendrevisionproject.Response.AuthResponse;
import org.ai.backendrevisionproject.Response.MessageResponse;
import org.ai.backendrevisionproject.Service.CustomUserDetailServiceImpl;
import org.ai.backendrevisionproject.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist=userRepository.findByEmail(user.getEmail());
        if(isUserExist!=null){
            throw new Exception("Email already exist wih another account");
        }
        User createUser=new User();
        createUser.setEmail(user.getEmail());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setName(user.getName());

        User savedUser=userRepository.save(createUser);
        subscriptionService.createSubscription(savedUser);

        var authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= JwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Signup successfully");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse>signing(@RequestBody LoginRequest user){
        String username=user.getEmail();
        String password=user.getPassword();

        var authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=JwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(jwt,"Sign in successfully");
        return new ResponseEntity<>(authResponse,HttpStatus.CREATED);


    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=customUserDetailService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());



    }


}
